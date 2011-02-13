/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package com.ao.data.dao.ini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import com.ao.data.dao.NPCCharacterPropertiesDAO;
import com.ao.data.dao.ini.LegacyWorldObjectType;
import com.ao.data.dao.exception.DAOException;
import com.ao.model.character.AIType;
import com.ao.model.character.Alignment;
import com.ao.model.character.npc.properties.CreatureNPCProperties;
import com.ao.model.character.npc.properties.GovernorNPCProperties;
import com.ao.model.character.npc.properties.GuardNPCProperties;
import com.ao.model.character.npc.properties.NPCProperties;
import com.ao.model.character.npc.properties.ResucitatorNPCProperties;
import com.ao.model.character.npc.properties.TraderNPCProperties;
import com.ao.model.character.npc.properties.TrainerNPCProperties;
import com.ao.model.spell.Spell;
import com.ao.model.worldobject.WorldObjectType;
import com.ao.model.character.NPCType;
import com.ao.model.inventory.Inventory;
import com.ao.model.map.City;
import com.ao.model.map.Heading;
import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Ini-backed implementation of the World Object DAO.
 */
public class NPCPropertiesDAOIni implements NPCCharacterPropertiesDAO {

	private static final Logger logger = Logger.getLogger(NPCPropertiesDAOIni.class);
	
	private static final int MAX_SOUNDS = 3;
	
	private static final String INIT_HEADER = "INIT";
	private static final String NUM_NPCS_KEY = "NumNPCs";
	
	private static final String NPC_SECTION_PREFIX = "NPC";
	private static final String NAME_KEY = "Name";
	private static final String NPC_TYPE_KEY = "NpcType";
	private static final String DESCRIPTION_KEY = "Desc";
	private static final String HEAD_KEY = "Head";
	private static final String BODY_KEY = "Body";
	private static final String HEADING_KEY = "Heading";
	private static final String MOVEMENT_KEY = "Movement";
	private static final String ATTACKABLE_KEY = "Attackable";
	private static final String RESPAWNABLE_KEY = "Respawn";
	private static final String HOSTILE_KEY = "Hostile";
	private static final String TAMEABLE_KEY = "Domable";
	private static final String ALIGNMENT_KEY = "Alineacion";
	private static final String COMMERCIABLE_KEY = "Comercia";
	private static final String ITEMS_TYPE_KEY = "TipoItems";
	private static final String ITEMS_AMOUNT_KEY = "NROITEMS";
	private static final String OBJECT_INVENTORY_PREFIX = "Obj";
	private static final String DROP_PREFIX = "Drop";
	private static final String BACKUPABLE_KEY = "Backup"; // TODO : No entiendo que NPCs lo usan! Implementar.
	private static final String CITY_KEY = "Ciudad";
	private static final String EXPERIENCE_KEY = "GiveEXP";
	private static final String GOLD_KEY = "GiveGLD";
	private static final String INVENTORY_RESPAWN_KEY = "InvReSpawn";
	private static final String MAX_HP_KEY = "MaxHp";
	private static final String MIN_HP_KEY = "MinHp";
	private static final String MAX_HIT_KEY = "MaxHIT";
	private static final String MIN_HIT_KEY = "MinHIT";
	private static final String DEFENSE_KEY = "def";
	private static final String SPELLS_AMOUNT_KEY = "LanzaSpells";
	private static final String SPELL_PREFIX = "SP";
	private static final String ATTACK_POWER_KEY = "PoderAtaque";
	private static final String DODGE_POWER_KEY = "PoderEvasion";
	private static final String CAN_WATER_KEY = "AguaValida";
	private static final String CAN_EARTH_KEY = "TierraInvalida";
  	private static final String CAN_POISON_KEY = "Veneno";
	private static final String PARALYZABLE_KEY = "AfectaParalisis";
	private static final String SOUND_PREFIX = "SND";
	// TODO : Use this!
	private static final String NPC_ATTACK_SOUND_KEY = "SND1";
	private static final String NPC_RECEIVE_SOUND_KEY = "SND2";
	private static final String NPC_DIE_SOUND_KEY = "SND3";
	private static final String MAGIC_DEFENSE_KEY = "DefM";
	private static final String CREATURES_AMOUNT_KEY = "NroCriaturas";
	private static final String CREATURE_ID_PREFIX = "CI";
	private static final String CREATURE_NAME_PREFIX = "CN";
	private static final String ORIGINAL_POSITION_KEY = "PosOrig";


// TODO : Use this!
	// Horrible, but it's completely hardwired in old VB version, and can't be induced from the dat
	private static final int NEWBIE_RESUCITATOR_INDEX = 119;
	
	private static final Map<LegacyNPCType, NPCType> npcTypeMapper;
	
	static {
		// Populate mappings from old object types to new ones.
		npcTypeMapper = new HashMap<LegacyNPCType, NPCType>();
		
		// FIXME : This two lines are contradictory!!
		npcTypeMapper.put(LegacyNPCType.COMMON, NPCType.TRADER);
		npcTypeMapper.put(LegacyNPCType.COMMON, NPCType.HOSTILE);
		npcTypeMapper.put(LegacyNPCType.DRAGON, NPCType.HOSTILE);
		npcTypeMapper.put(LegacyNPCType.TRAINER, NPCType.TRAINER);
	}
	
	private String npcsFilePath;

	/**
	 * Creates a new NPCDAOIni instance.
	 * @param npcsFilePath The path to the file with all objects definitions.
	 */
	@Inject
	public NPCPropertiesDAOIni(@Named("npcsFilePath") String npcsFilePath) {
		this.npcsFilePath = npcsFilePath;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ao.data.dao.NPCDAO#retrieveAll()
	 */
	@Override
	public NPCProperties[] retrieveAll() throws DAOException {
		Ini iniFile;
		
		try {
			// Make sure the reader is closed, since Ini4J gives no guarantees.
			Reader reader = new BufferedReader(new FileReader(npcsFilePath));
			iniFile = new Ini(reader);
			reader.close();
		} catch (Exception e) {
			logger.error("NPC loading failed!", e);
			throw new DAOException(e);
		}
		
		int totalNpcs = Integer.parseInt(iniFile.get(INIT_HEADER, NUM_NPCS_KEY));
		
		NPCProperties[] npcs = new NPCProperties[totalNpcs];
		
		for (int i = 1; i < totalNpcs; i++) {
			npcs[i - 1] = loadNpc(i, iniFile);
		}
		
		return npcs;
	}

	/**
	 * Creates an object's properties from the given section. 
	 * @param iniFile The ini file that contains all world object to be loaded.
	 * @return The world object created.
	 */
	private NPCProperties loadNpc(int id, Ini iniFile) {
		
		//The section of the ini file containing the world object to be loaded.
		Section section = iniFile.get(NPC_SECTION_PREFIX + id);
		
		// Make sure it's valid
		if (section == null) {
			return null;
		}
		
		NPCProperties npc = null;
		String name = section.get(NAME_KEY);
		int npcType = Integer.parseInt(section.get(NPC_TYPE_KEY));
		short body = Short.parseShort(section.get(BODY_KEY));
		short head = Short.parseShort(section.get(HEAD_KEY));
		Heading heading = Heading.valueOf(section.get(HEADING_KEY));
		boolean respawn = hasRespawn(section);
		
		LegacyNPCType type = LegacyNPCType.valueOf(npcType);
		
		switch (type) {
			case COMMON:
			case DRAGON:
				if (isCommerciable(section)) {
					npc = loadTrader(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				} else {
					npc = loadCreature(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				}
				break;
			
			case TRAINER:
				npc = loadTrainer(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				break;
			
			case GOVERNOR:
				npc = loadGovernor(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				break;
			
			case ROYAL_GUARD:
			case CHAOS_GUARD:
				npc = loadGuard(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				break;
				
			case NEWBIE_RESUCITATOR:
			case RESUCITATOR:
				npc = loadResucitator(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				break;

			case GAMBLER:
			case BANKER:
			case NOBLE:
				npc = loadNPC(npcTypeMapper.get(type), id, name, body, head, heading, respawn, section);
				break;
				
			default:
				logger.error("Unexpected object type found: " + npcType);
		}
		
		return npc;
	}

	/**
	 * Creates a NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadNPC(NPCType type, int id, String name,
			short body, short head, Heading heading, boolean respawn,
			Section section) {
		return new NPCProperties(type, id, name, body, head, heading, respawn);
	}

	/**
	 * Creates a Resucitator NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadResucitator(NPCType type, int id, String name,
			short body, short head, Heading heading, boolean respawn, Section section) {
		boolean isNewbie = type != NPCType.RESUCITATOR;
		
		return new ResucitatorNPCProperties(type, id, name, body, head, heading, respawn, isNewbie);
	}
	
	/**
	 * Creates a Governor NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadGovernor(NPCType type, int id, String name,
			short body, short head, Heading heading, boolean respawn, Section section) {
		
		String description = getDescription(section);
		AIType aiType = getAIType(section);
		Alignment alignment = getAlignment(section);
		City city = getCity(section);
		
		return new GovernorNPCProperties(type, id, name, body, head, heading, respawn,
			description, aiType, alignment, city
		);
	}
	
	/**
	 * Creates a Trader NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadTrader(NPCType type, int id, String name,
			short body, short head, Heading heading, boolean respawn, Section section) {
		
		AIType aiType = getAIType(section);
		String description = getDescription(section);
		Alignment alignment = getAlignment(section);
		Inventory inventory = null;
		boolean respawnInventory = hasInventoryRespawn(section);
		WorldObjectType itemsType = getItemsType(section);
		
		return new TraderNPCProperties(type, id, name, body, head, heading, respawn,
			aiType, description, alignment, inventory, respawnInventory, itemsType
		);
	}

	/**
	 * Creates a Trainer NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadTrainer(NPCType type, int id, String name, short body, 
		short head, Heading heading, boolean respawn, Section section) {
		
		String description = getDescription(section);
		AIType aiType = getAIType(section);
		Alignment alignment = getAlignment(section);
		Map<Integer, String> creatures = getCreatures(section);
		
		return new TrainerNPCProperties(type, id, name, body, head, heading, respawn, 
			description, aiType, alignment, creatures);
	}
	
	/**
	 * Creates a Bicho NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadCreature(NPCType type, int id, String name, short body, 
		short head, Heading heading, boolean respawn, Section section) {
		
		String description = getDescription(section);
		AIType aiType = getAIType(section);
		Alignment alignment = getAlignment(section);
		int experience = getExperience(section);
		int gold = getGold(section);
		int minHP = getMinHP(section);
		int maxHP = getMaxHP(section);
		int minDamage = getMinDamage(section);
		int maxDamage = getMaxDamage(section);
		short defense = getDefense(section);
		short magicDefense = getMagicDefense(section);
		short accuracy = getAccuracy(section);
		short dodge = getDodge(section);
		List<Spell> spells = getSpells(section);
		boolean canSwim = canSwim(section);
		boolean canWalk = canWalk(section);
		boolean attackable = isAttackable(section);
		boolean poison = canPoison(section);
		boolean paralyzable = isParalyzable(section);
		boolean hostile = isHostile(section);
		boolean tameable = isTameable(section);
		
		return new CreatureNPCProperties(type, id, name, body, head, heading, respawn, description, 
			aiType, alignment, experience, gold, minHP, maxHP, minDamage, maxDamage, defense, 
			magicDefense, accuracy, dodge, spells, canSwim, canWalk, attackable, 
			poison, paralyzable, hostile, tameable);
	}
	
	/**
	 * Creates a Bicho NPC's properties from the given section.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn 
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The NPC created.
	 */
	private NPCProperties loadGuard(NPCType type, int id, String name, short body, 
		short head, Heading heading, boolean respawn, Section section) {
		
		String description = getDescription(section);
		AIType aiType = getAIType(section);
		Alignment alignment = getAlignment(section);
		int experience = getExperience(section);
		int gold = getGold(section);
		int minHP = getMinHP(section);
		int maxHP = getMaxHP(section);
		int minDamage = getMinDamage(section);
		int maxDamage = getMaxDamage(section);
		short defense = getDefense(section);
		short magicDefense = getMagicDefense(section);
		short accuracy = getAccuracy(section);
		short dodge = getDodge(section);
		List<Spell> spells = getSpells(section);
		boolean canSwim = canSwim(section);
		boolean canWalk = canWalk(section);
		boolean attackable = isAttackable(section);
		boolean poison = canPoison(section);
		boolean paralyzable = isParalyzable(section);
		boolean hostile = isHostile(section);
		boolean tameable = isTameable(section);
		boolean originalPosition = hasOriginalPosition(section);
		
		return new GuardNPCProperties(type, id, name, body, head, heading, respawn, description, 
			aiType, alignment, experience, gold, minHP, maxHP, minDamage, maxDamage, defense, 
			magicDefense, accuracy, dodge, spells, canSwim, canWalk, attackable, 
			poison, paralyzable, hostile, tameable, originalPosition);
	}
	
	/**
	 * Retrieves an object's experience from it's section.
	 * @param section The section from which to read the npc experience.
	 * @return The npc's experience.
	 */
	private int getExperience(Section section) {
		String data = section.get(EXPERIENCE_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an npc's gold from it's section.
	 * @param section The section from which to read the npc's gold.
	 * @return The npc's gold.
	 */
	private int getGold(Section section) {
		String data = section.get(GOLD_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an npc's min hp from it's section.
	 * @param section The section from which to read the npc's min hp.
	 * @return The npc's min hp.
	 */
	private int getMinHP(Section section) {
		String data = section.get(MIN_HP_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an npc's max hp from it's section.
	 * @param section The section from which to read the npc's max hp.
	 * @return The npc's max hp.
	 */
	private int getMaxHP(Section section) {
		String data = section.get(MAX_HP_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an npc's min damage from it's section.
	 * @param section The section from which to read the npc's min damage.
	 * @return The npc's min damage.
	 */
	private int getMinDamage(Section section) {
		String data = section.get(MIN_HIT_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an npc's max damage from it's section.
	 * @param section The section from which to read the npc's max damage.
	 * @return The npc's max damage.
	 */
	private int getMaxDamage(Section section) {
		String data = section.get(MAX_HIT_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an npc's defense from it's section.
	 * @param section The section from which to read the npc's defense.
	 * @return The npc's defense.
	 */
	private short getDefense(Section section) {
		String data = section.get(DEFENSE_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Short.parseShort(data);
	}
	
	/**
	 * Retrieves an npc's magic defense from it's section.
	 * @param section The section from which to read the npc's magic defense.
	 * @return The npc's magic defense.
	 */
	private short getMagicDefense(Section section) {
		String data = section.get(MAGIC_DEFENSE_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Short.parseShort(data);
	}
	
	/**
	 * Retrieves an npc's acc from it's section.
	 * @param section The section from which to read the npc's acc.
	 * @return The npc's acc.
	 */
	private short getAccuracy(Section section) {
		String data = section.get(ATTACK_POWER_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Short.parseShort(data);
	}
	
	/**
	 * Retrieves an npc's dodge from it's section.
	 * @param section The section from which to read the npc's dodge.
	 * @return The npc's dodge.
	 */
	private short getDodge(Section section) {
		String data = section.get(DODGE_POWER_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Short.parseShort(data);
	}
	
	/**
	 * Retrieves an npc's spell amount from it's section.
	 * @param section The section from which to read the npc's spell amount.
	 * @return The npc's spell amount.
	 */
	private byte getSpellAmount(Section section) {
		String data = section.get(SPELLS_AMOUNT_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Byte.parseByte(data);
	}
	
	/**
	 * Retrieves a list with the spells from it's section
	 * @param section The section for the npc.
	 * @return The npc's spells
	 */
	private List<Spell> getSpells(Section section) {
		byte amount = getSpellAmount(section);
		if (amount <= 0) {
			return null;
		}
		List<Spell> spells = null;
		String spell;
		for (byte i = 1; i <= amount; i++) {
			spell = section.get(SPELL_PREFIX + i);
			if (spell != null) {
				// TODO : Implementar Spells!
			}
		}
		
		return spells;
	}
	
	/**
	 * Checks if the npc .
	 * @param section The section for the item to check.
	 * @return True if the item is a ranged weapon, false otherwise.
	 */
	private boolean canSwim(Section section) {
		String data = section.get(CAN_WATER_KEY);
		return data != null && !"0".equals(data);
	}

	/**
	 * Checks if the npc .
	 * @param section The section for the item to check.
	 * @return True if the item is a ranged weapon, false otherwise.
	 */
	private boolean canWalk(Section section) {
		String data = section.get(CAN_EARTH_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the npc is attackable.
	 * @param section The section for the npc to check.
	 * @return True if the npc is attackable, false otherwise.
	 */
	private boolean isAttackable(Section section) {
		String data = section.get(ATTACKABLE_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section corresponds to a hostile npc.
	 * @param section The section for the npc.
	 * @return True if the npc is hostile, false otherwise.
	 */
	private boolean isHostile(Section section) {
		String data = section.get(HOSTILE_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section corresponds to a tameable npc.
	 * @param section The section for the item to check.
	 * @return True if the item is tameable, false otherwise.
	 */
	private boolean isTameable(Section section) {
		String data = section.get(TAMEABLE_KEY);
		return data != null && !"0".equals(data);
	}
	
	
	/**
	 * Checks if the npc can poison.
	 * @param section The section for the npc.
	 * @return True if the npc can poison, false otherwise.
	 */
	private boolean canPoison(Section section) {
		String data = section.get(CAN_POISON_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section has a original position.
	 * @param section The section for the npc.
	 * @return True if the npc has original position, false otherwise.
	 */
	private boolean hasOriginalPosition(Section section) {
		String data = section.get(ORIGINAL_POSITION_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section is paralyzable.
	 * @param section The section for the npc.
	 * @return True if the npc is paralyzable, false otherwise.
	 */
	private boolean isParalyzable(Section section) {
		String data = section.get(PARALYZABLE_KEY);
		return data != null && !"0".equals(data);
	}
	
	// TODO : Documentar!
	public Map<Integer, Integer> getDrops(Section section) {
		String data = section.get(ITEMS_AMOUNT_KEY);
		if (data == null) {
			return null;
		}
		
		Map<Integer, Integer> drops = null;
		String slot;
		for (byte i = 1; i <= Byte.parseByte(data); i++) {
			slot = section.get(DROP_PREFIX + i);
			if (slot != null) {
				String[] slotInfo = slot.split("-");
				drops.put(Integer.parseInt(slotInfo[0]), Integer.parseInt(slotInfo[1]));
			}
		}
		
		return drops;
	}
	
	// TODO : Documentar!
	public Map<Integer, Integer> getInventory(Section section) {
		String data = section.get(ITEMS_AMOUNT_KEY);
		if (data == null) {
			return null;
		}
		
		String slot;
		Map<Integer, Integer> inventory = null;
		for (byte i = 1; i <= Byte.parseByte(data); i++) {
			slot = section.get(OBJECT_INVENTORY_PREFIX + i);
			if (slot != null) {
				if (null == inventory) {
					inventory = new HashMap<Integer, Integer>();
				}
				String[] slotInfo = slot.split("-");
				inventory.put(Integer.parseInt(slotInfo[0]), Integer.parseInt(slotInfo[1]));
			}
		}
		
		return inventory;
	}

	// TODO : Documentar!
	public Map<Integer, String> getCreatures(Section section) {
		String data = section.get(CREATURES_AMOUNT_KEY);
		if (data == null) {
			return null;
		}
		
		String creatureId;
		String creatureName;
		Map<Integer, String> creatures = null;
		for (byte i = 1; i <= Byte.parseByte(data); i++) {
			creatureId = section.get(CREATURE_ID_PREFIX+ i);
			creatureName = section.get(CREATURE_NAME_PREFIX+ i);
			if (creatureId != null && creatureName != null) {
				if (null == creatures) {
					creatures = new HashMap<Integer, String>();
				}
				creatures.put(Integer.parseInt(creatureId), creatureName);
			}
		}
		
		return creatures;
	}
	
	/**
	 * Retrieves all sounds an item may reproduce.
	 * @param section The section from which to retrieve values.
	 * @return The list of sounds the item may reproduce.
	 */
	private List<Integer> getSounds(Section section) {
		List<Integer> sounds = new LinkedList<Integer>();
		String data;
		
		for (int i = 1; i <= MAX_SOUNDS; i++ ) {
			data = section.get(SOUND_PREFIX + i);
			if (data != null) {
				sounds.add(Integer.parseInt(data));
			}
		}
		
		return sounds;
	}
	
	// TODO : Documentar!
	private Alignment getAlignment(Section section) {
		String data = section.get(ALIGNMENT_KEY);
		
		if (data == null) {
			return null;
		}
		
		return Alignment.valueOf(data);
	}
	
	// TODO : Documentar!
	private WorldObjectType getItemsType(Section section) {
		String data = section.get(ITEMS_TYPE_KEY);
		
		if (data == null) {
			return null;
		}
		
		LegacyWorldObjectType objectType = LegacyWorldObjectType.valueOf(data);
		
		// FIXME : This is not taking into account that not all LegacyWorldObjectTypes are mapped directly
		return objectType.getCurrentType();
	}

	/**
	 * Checks if the npc has a inventory respawnable.
	 * @param section The section for the npc to check.
	 * @return True if the npc has a inventory respawneable, false otherwise.
	 */
	private boolean hasInventoryRespawn(Section section) {
		String data = section.get(INVENTORY_RESPAWN_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section corresponds has respawn.
	 * @param section The section for the npc to check.
	 * @return True if the npc has respawn, false otherwise.
	 */
	private boolean hasRespawn(Section section) {
		String data = section.get(RESPAWNABLE_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Retrieves an npc's city.
	 * @param section The section from which to read the npc's city.
	 * @return The npc's city.
	 */
	private City getCity(Section section) {
		String data = section.get(CITY_KEY);
		
		if (data == null) {
			return null;
		}
		
		// TODO : Implementar Cities :d
		return null;
		
	}
	
	/**
	 * Checks if the section corresponds to a commerciable npc.
	 * @param section The section for the npc to check.
	 * @return True if the npc is commerciable, false otherwise.
	 */
	private boolean isCommerciable(Section section) {
		String data = section.get(COMMERCIABLE_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Retrieves an npc's description.
	 * @param section The section from which to read the npc's description.
	 * @return The npc's description.
	 */
	private String getDescription(Section section) {
		String data = section.get(DESCRIPTION_KEY);
		
		if (data == null) {
			return null;
		}
		
		return data;
	}
	
	/**
	 * Retrieves an object's value from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's value.
	 */
	private AIType getAIType(Section section) {
		// FIXME : We should actually not use the old AITypes, but use them to map to movement strategy + attack strategy
		String data = section.get(MOVEMENT_KEY);
		
		if (data == null) {
			return null;
		}
		
		return AIType.valueOf(data);
	}
	
	/**
	 * NPC Type enumeration, as it was known in the old days of Visual Basic.
	 */
	private enum LegacyNPCType {
		COMMON(0),
		RESUCITATOR(1),
		ROYAL_GUARD(2),
		TRAINER(3),
		BANKER(4),
		NOBLE(5),
		DRAGON(6),
		GAMBLER(7),
		CHAOS_GUARD(8),
		NEWBIE_RESUCITATOR(9),
		PRETORIAN(10),
		GOVERNOR(11);
		
		protected int value;
		
		/**
		 * Creates a new LegacyWorldObjectType.
		 * @param value The value corresponding to the object type. Should be unique.
		 */
		private LegacyNPCType(int value) {
			this.value = value;
		}
		
		/**
		 * Retrieves the LegacyWorldObjectType associated with the given value.
		 * @param value The value for which to search for a LegacyWorldObjectType.
		 * @return The matched LegacyWorldObjectType, if any.
		 */
		public static LegacyNPCType valueOf(int value) {
			for (LegacyNPCType type : LegacyNPCType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			
			return null;
		}
	}
	
}
