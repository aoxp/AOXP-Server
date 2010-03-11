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

package ao.data.dao.ini;

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

import ao.data.dao.WorldObjectPropertiesDAO;
import ao.data.dao.exception.DAOException;
import ao.model.character.Race;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.WorldObjectType;
import ao.model.worldobject.properties.AmmunitionProperties;
import ao.model.worldobject.properties.BoatProperties;
import ao.model.worldobject.properties.DefensiveItemProperties;
import ao.model.worldobject.properties.ItemProperties;
import ao.model.worldobject.properties.MusicalInstrumentProperties;
import ao.model.worldobject.properties.RangedWeaponProperties;
import ao.model.worldobject.properties.StaffProperties;
import ao.model.worldobject.properties.StatModifyingItemProperties;
import ao.model.worldobject.properties.TeleportProperties;
import ao.model.worldobject.properties.TemporalStatModifyingItemProperties;
import ao.model.worldobject.properties.WeaponProperties;
import ao.model.worldobject.properties.WorldObjectProperties;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Ini-backed implementation of the World Object DAO.
 */
public class WorldObjectPropertiesDAOIni implements WorldObjectPropertiesDAO {

	private static final Logger logger = Logger.getLogger(WorldObjectPropertiesDAOIni.class);
	
	private static final int MAX_SOUNDS = 3;
	
	protected static final String INIT_HEADER = "INIT";
	private static final String NUM_OBJECTS_KEY = "NumOBJs";
	
	private static final String OBJECT_SECTION_PREFIX = "OBJ";
	private static final String NAME_KEY = "Name";
	private static final String GRAPHIC_KEY = "GrhIndex";
	private static final String OBJECT_TYPE_KEY = "ObjType";
	private static final String VALUE_KEY = "Valor";
	private static final String WOODWORKING_DIFFICULTY_KEY = "SkCarpinteria";
	private static final String IRONWORKING_DIFFICULTY_KEY = "SkHerreria";
	private static final String NEWBIE_KEY = "Newbie";
	private static final String EQUIPPED_GRAPHIC_KEY = "NumRopaje";
	private static final String MIN_DEF_KEY = "MinDef";
	private static final String MAX_DEF_KEY = "MaxDef";
	private static final String MIN_MAGIC_DEF_KEY = "DefensaMagicaMin";
	private static final String MAX_MAGIC_DEF_KEY = "DefensaMagicaMax";
	private static final String DWARF_KEY = "RazaEnana";
	private static final String DARK_ELF_KEY = "RazaDrow";
	private static final String ELF_KEY = "RazaElfa";
	private static final String GNOME_KEY = "RazaGnoma";
	private static final String HUMAN_KEY = "RazaHumana";
	private static final String FORBIDDEN_ARCHETYPE_PREFIX = "CP";
	private static final String MIN_HIT_KEY = "MinHit";
	private static final String MAX_HIT_KEY = "MaxHit";
	private static final String STABS_KEY = "Apuñala";
	private static final String PIERCING_DAMAGE_KEY = "Refuerzo";
	private static final String IS_RANGED_WEAPON_KEY = "Proyectil";
	private static final String NEEDS_AMMUNITION_KEY = "Municiones";
	private static final String MAGIC_POWER_KEY = "StaffPower";
	private static final String DAMAGE_BONUS_KEY = "StaffDamageBonus";
	private static final String USAGE_DIFFICULTY_KEY = "MinSkill";
	private static final String HUNGER_KEY = "MinHam";
	private static final String THIRST_KEY = "MinAgu";
	private static final String SOUND_PREFIX = "SND";
	private static final String RADIUS_KEY = "Radio";
	private static final String POTION_TYPE_KEY = "TipoPocion";
	private static final String MIN_MODIFIER = "MinModificador";
	private static final String MAX_MODIFIER = "MaxModificador";
	private static final String MODIFIER_TIME = "DuracionEfecto";
	private static final String EQUIPPED_WEAPON_GRAPHIC_KEY = "Anim";
	private static final String UNGRABABLE_KEY = "Agarrable";
	
	private static final Map<String, UserArchetype> archetypesByName;
	private static final Map<LegacyWorldObjectType, WorldObjectType> worldObjectTypeMapper;
	
	static {
		// Populate aliases from spanish config files to internal types
		archetypesByName = new HashMap<String, UserArchetype>();
		
		archetypesByName.put("MAGO", UserArchetype.MAGE);
		archetypesByName.put("CLERIGO", UserArchetype.CLERIC);
		archetypesByName.put("GUERRERO", UserArchetype.WARRIOR);
		archetypesByName.put("ASESINO", UserArchetype.ASSASIN);
		archetypesByName.put("LADRON", UserArchetype.THIEF);
		archetypesByName.put("BARDO", UserArchetype.BARD);
		archetypesByName.put("DRUIDA", UserArchetype.DRUID);
		archetypesByName.put("BANDIDO", UserArchetype.BANDIT);
		archetypesByName.put("PALADIN", UserArchetype.PALADIN);
		archetypesByName.put("CAZADOR", UserArchetype.HUNTER);
		archetypesByName.put("PESCADOR", UserArchetype.FISHER);
		archetypesByName.put("HERRERO", UserArchetype.BLACKSMITH);
		archetypesByName.put("LEÑADOR", UserArchetype.LUMBERJACK);
		archetypesByName.put("MINERO", UserArchetype.MINER);
		archetypesByName.put("CARPINTERO", UserArchetype.CARPENTER);
		archetypesByName.put("PIRATA", UserArchetype.PIRATE);
		
		
		// Populate mappings from old object types to new ones.
		worldObjectTypeMapper = new HashMap<LegacyWorldObjectType, WorldObjectType>();
		
		// TODO : Add more mappings as more objects are modeled and loaded
		// BEWARE : Some objects have no mapping since they need extra info to be mapped (potions and weapons for instance).
		worldObjectTypeMapper.put(LegacyWorldObjectType.ARMOR, WorldObjectType.ARMOR);
		worldObjectTypeMapper.put(LegacyWorldObjectType.ARROW, WorldObjectType.AMMUNITION);
		worldObjectTypeMapper.put(LegacyWorldObjectType.BOAT, WorldObjectType.BOAT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.DRINK, WorldObjectType.DRINK);
		worldObjectTypeMapper.put(LegacyWorldObjectType.EMPTY_BOTTLE, WorldObjectType.EMPTY_BOTTLE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.FILLED_BOTTLE, WorldObjectType.FILLED_BOTTLE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.HELMET, WorldObjectType.HELMET);
		worldObjectTypeMapper.put(LegacyWorldObjectType.MINERAL, WorldObjectType.MINERAL);
		worldObjectTypeMapper.put(LegacyWorldObjectType.MUSICAL_INSTRUMENT, WorldObjectType.MUSICAL_INSTRUMENT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.RING, WorldObjectType.ACCESSORY);
		worldObjectTypeMapper.put(LegacyWorldObjectType.SHIELD, WorldObjectType.SHIELD);
		worldObjectTypeMapper.put(LegacyWorldObjectType.TELEPORT, WorldObjectType.TELEPORT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.USE_ONCE, WorldObjectType.FOOD);
	}
	
	private String objectsFilePath;
	
	/**
	 * Creates a new WorldObjectDAOIni instance.
	 * @param objectsFilePath The path to the file with all objects definitions.
	 */
	@Inject
	public WorldObjectPropertiesDAOIni(@Named("objectsFilePath") String objectsFilePath) {
		this.objectsFilePath = objectsFilePath;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.data.dao.WorldObjectDAO#retrieveAll()
	 */
	@Override
	public WorldObjectProperties[] retrieveAll() throws DAOException {
		Ini iniFile;
		
		try {
			// Make sure the reader is closed, since Ini4J gives no guarantees.
			Reader reader = new BufferedReader(new FileReader(objectsFilePath));
			iniFile = new Ini(reader);
			reader.close();
		} catch (Exception e) {
			logger.error("World Object loading failed!", e);
			throw new DAOException();
		}
		
		int totalObjects = Integer.parseInt(iniFile.get(INIT_HEADER, NUM_OBJECTS_KEY));
		
		WorldObjectProperties[] objects = new WorldObjectProperties[totalObjects];
		
		for (int i = 1; i < totalObjects; i++) {
			objects[i - 1] = loadObject(i, iniFile.get(OBJECT_SECTION_PREFIX + i));
		}
		
		return objects;
	}

	/**
	 * Creates an object's properties from the given section.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadObject(int id, Section section) {
		// Make sure it's valid
		if (section == null) {
			return null;
		}
		
		WorldObjectProperties obj = null;
		String name = section.get(NAME_KEY);
		int graphic = Integer.parseInt(section.get(GRAPHIC_KEY));
		int objectType = Integer.parseInt(section.get(OBJECT_TYPE_KEY));
		LegacyWorldObjectType type = LegacyWorldObjectType.valueOf(objectType);
		
		switch (type) {
			case RING:
			case HELMET:
			case ARMOR:
			case SHIELD:
				obj = loadDefensiveItem(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case WEAPON:
				obj = loadWeapon(id, name, graphic, section);
				break;
				
			case ARROW:
				obj = loadAmmunition(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case BOAT:
				obj = loadBoat(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case DRINK:
			case FILLED_BOTTLE:
				obj = loadDrink(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case USE_ONCE:
				obj = loadFood(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case EMPTY_BOTTLE:
				obj = loadGenericItem(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case MUSICAL_INSTRUMENT:
				obj = loadMusicalInstrument(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case TELEPORT:
				obj = loadTeleport(worldObjectTypeMapper.get(type), id, name, graphic, section);
				break;
				
			case POTION:
				obj = loadPotion(id, name, graphic, section);
				break;
				
			case FLOWERS:
				obj = loadFlowers(id, name, graphic, section);
				break;
				
			default:
				logger.error("Unexpected object type found: " + objectType);
		}
		
		return obj;
	}
	
	/**
	 * Creates a defensive items's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadDefensiveItem(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int equippedGraphic = getEquippedGraphic(section);
		int minDef = getMinDef(section);
		int maxDef = getMaxDef(section);
		int minMagicDef = getMinMagicDef(section);
		int maxMagicDef = getMaxMagicDef(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		return new DefensiveItemProperties(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, minDef, maxDef, minMagicDef, maxMagicDef);
	}

	/**
	 * Creates a teleport's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadTeleport(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		int radius = getRadius(section);
		
		return new TeleportProperties(type, id, name, graphic, radius);
	}
	
	/**
	 * Creates a flower's properties from the given section.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadFlowers(int id, String name, int graphic,
			Section section) {
		
		// Is it grabable or is it just a prop?
		if (!isGrabable(section)) {
			// It's a prop
			return new WorldObjectProperties(WorldObjectType.PROP, id, name, graphic);
		}
		
		return loadGenericItem(WorldObjectType.GRABABLE_PROP, id, name, graphic, section);
	}
	
	/**
	 * Creates a generic item's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The generic item created.
	 */
	private WorldObjectProperties loadGenericItem(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		return new ItemProperties(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie);
	}
	
	/**
	 * Creates a potion's properties from the given section.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadPotion(int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		PotionType potionType = getPotionType(section);
		
		// Is it a death potion?
		if (potionType == PotionType.DEATH) {
			return new ItemProperties(WorldObjectType.DEATH_POTION, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie);
		} else if (potionType == PotionType.POISON) {	// Poison potion?
			return new ItemProperties(WorldObjectType.POISON_POTION, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie);
		}
		
		int minModifier = getMinModifier(section);
		int maxModifier = getMaxModifier(section);
		
		// Is it an HP potion
		if (potionType == PotionType.HP) {
			return new StatModifyingItemProperties(WorldObjectType.HP_POTION, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, minModifier, maxModifier);
		} else if (potionType == PotionType.MANA) {		// Mana potion?
			return new StatModifyingItemProperties(WorldObjectType.MANA_POTION, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, minModifier, maxModifier);
		}
		
		int effectTime = getEffectTime(section);
		
		// Strength potion?
		if (potionType == PotionType.STRENGTH) {
			return new TemporalStatModifyingItemProperties(WorldObjectType.STRENGTH_POTION, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, minModifier, maxModifier, effectTime);
		} else if (potionType == PotionType.AGILITY) { 	// Agility potion?
			return new TemporalStatModifyingItemProperties(WorldObjectType.AGILITY_POTION, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, minModifier, maxModifier, effectTime);
		}
		
		// This should never happen...
		logger.error("Parsed a potion with an unmatched potion type: " + potionType.name());
		return null;
	}
	
	/**
	 * Creates a musical instrument's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadMusicalInstrument(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int equippedGraphic = getEquippedGraphic(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		List<Integer> sounds = getSounds(section);
		
		return new MusicalInstrumentProperties(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, sounds);
	}
	
	/**
	 * Creates a boat items's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadBoat(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int equippedGraphic = getEquippedGraphic(section);
		int minDef = getMinDef(section);
		int maxDef = getMaxDef(section);
		int minMagicDef = getMinMagicDef(section);
		int maxMagicDef = getMaxMagicDef(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		int usageDifficulty = getUsageDifficulty(section);
		int minHit = getMinHit(section);
		int maxHit = getMaxHit(section);
		
		return new BoatProperties(type, id, name, graphic, tradeable, value, usageDifficulty, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, minDef, maxDef, minMagicDef, maxMagicDef, minHit, maxHit);
	}
	
	
	/**
	 * Creates a food items's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadFood(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int modifier = getHunger(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		return new StatModifyingItemProperties(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, modifier, modifier);
	}

	/**
	 * Creates a drink items's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadDrink(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int modifier = getThirst(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		return new StatModifyingItemProperties(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, modifier, modifier);
	}

	/**
	 * Creates a weapon's properties from the given section.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadWeapon(int id, String name, int graphic,
			Section section) {
		
		// Load basic data
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int equippedGraphic = getEquippedGraphic(section);
		int minHit = getMinHit(section);
		int maxHit = getMaxHit(section);
		boolean stabs = getStabs(section);
		int piercingDamage = getPiercingDamage(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		// Is it a ranged weapon?
		if (isRangedWeapon(section)) {
			boolean needsAmmunition = getNeedsAmmunition(section);
			
			return new RangedWeaponProperties(WorldObjectType.RANGED_WEAPON, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, stabs, piercingDamage, minHit, maxHit, needsAmmunition);
		} else if (isStaff(section)) {	// Is it a staff?
			int magicPower = getMagicPower(section);
			int damageBonus = getDamageBonus(section);
			
			return new StaffProperties(WorldObjectType.STAFF, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, stabs, piercingDamage, minHit, maxHit, magicPower, damageBonus);
		}
		
		// Just a normal weapon
		return new WeaponProperties(WorldObjectType.WEAPON, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, stabs, piercingDamage, minHit, maxHit);
	}

	/**
	 * Creates an ammunition items's properties from the given section.
	 * @param type The object's type.
	 * @param id The object's id.
	 * @param name The object's name.
	 * @param graphic The object's graphic.
	 * @param section The section of the ini file containing the world object to be loaded.
	 * @return The world object created.
	 */
	private WorldObjectProperties loadAmmunition(WorldObjectType type, int id, String name, int graphic,
			Section section) {
		
		boolean tradeable = getTradeable(section);
		int value = getValue(section);
		int manufactureDifficulty = getManufactureDifficulty(section);
		boolean newbie = getNewbie(section);
		int equippedGraphic = getEquippedGraphic(section);
		int minHit = getMinHit(section);
		int maxHit = getMaxHit(section);
		List<UserArchetype> forbiddenArchetypes = getForbiddenArchetypes(section);
		List<Race> forbiddenRaces = getForbiddenRaces(section);
		
		return new AmmunitionProperties(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic, minHit, maxHit);
	}
	
	/**
	 * Retrieves a list of all forbidden archetypes for this item, if any. Null if all are permited.
	 * @param section The section from which to parse the forbidden archetypes.
	 * @return The list of forbidden archetypes for the item, or null if none.
	 */
	private List<UserArchetype> getForbiddenArchetypes(Section section) {
		String data;
		List<UserArchetype> forbiddenArchetypes = new LinkedList<UserArchetype>();
		int total = UserArchetype.values().length;
		
		for (int i = 1; i <= total; i++) {
			data = section.get(FORBIDDEN_ARCHETYPE_PREFIX + i);
			if (data != null) {
				if (archetypesByName.containsKey(data)) {
					forbiddenArchetypes.add(archetypesByName.get(data));
				} else {
					// This shouldn't happen!!!
					logger.error("Unexpected forbidden class loading object: " + data);
				}
			}
		}
		
		if (forbiddenArchetypes.size() == 0) {
			return null;
		}
		
		return forbiddenArchetypes;
	}

	/**
	 * Retrieves a list of all forbidden races for this item, if any. Null if all are permited.
	 * @param section The section from which to parse the forbidden races.
	 * @return The list of forbidden races for the item, or null if none.
	 */
	private List<Race> getForbiddenRaces(Section section) {
		String data;
		List<Race> forbiddenRaces = new LinkedList<Race>();
		
		data = section.get(DWARF_KEY);
		if (data != null && !"0".equals(data)) {
			forbiddenRaces.add(Race.DWARF);
		}
		
		data = section.get(DARK_ELF_KEY);
		if (data != null && !"0".equals(data)) {
			forbiddenRaces.add(Race.DARK_ELF);
		}
		
		data = section.get(ELF_KEY);
		if (data != null && !"0".equals(data)) {
			forbiddenRaces.add(Race.ELF);
		}
		
		data = section.get(GNOME_KEY);
		if (data != null && !"0".equals(data)) {
			forbiddenRaces.add(Race.GNOME);
		}
		
		data = section.get(HUMAN_KEY);
		if (data != null && !"0".equals(data)) {
			forbiddenRaces.add(Race.HUMAN);
		}
		
		if (forbiddenRaces.size() == 0) {
			return null;
		}
		
		return forbiddenRaces;
	}

	/**
	 * Checks if an object is tradeable or not.
	 * @param section The section from which to read the object's value.
	 * @return True if the obejct is tradeable, false otherwise.
	 */
	private boolean getTradeable(Section section) {
		return true;	// TODO : Where did I take this from? Is it used at all?
	}
	
	/**
	 * Retrieves an object's value from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's value.
	 */
	private int getValue(Section section) {
		String data = section.get(VALUE_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's manufacture difficulty from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's manufacture difficulty.
	 */
	private int getManufactureDifficulty(Section section) {
		String value = section.get(WOODWORKING_DIFFICULTY_KEY);
		
		if (value == null) {
			value = section.get(IRONWORKING_DIFFICULTY_KEY);
			
			if (value == null) {
				return 0;
			}
		}
		
		return Integer.parseInt(value);
	}

	/**
	 * Checks if an object is for newbies from it's section.
	 * @param section The section from which to read the object's value.
	 * @return True if the object is for newbies, false otherwise.
	 */
	private boolean getNewbie(Section section) {
		String data = section.get(NEWBIE_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if an object stabs from it's section.
	 * @param section The section from which to read the object's value.
	 * @return True if the object stabs, false otherwise.
	 */
	private boolean getStabs(Section section) {
		String data = section.get(STABS_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Retrieves an object's equipped graphic from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's equipped graphic.
	 */
	private int getEquippedGraphic(Section section) {
		String data = section.get(EQUIPPED_GRAPHIC_KEY);
		
		if (data == null) {
			data = section.get(EQUIPPED_WEAPON_GRAPHIC_KEY);
			if (data == null) {
				return 0;
			}
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's minimum defense from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's minimum defense.
	 */
	private int getMinDef(Section section) {
		String data= section.get(MIN_DEF_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's maximum defense from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's maximum defense.
	 */
	private int getMaxDef(Section section) {
		String data = section.get(MAX_DEF_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's minimum magic defense from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's minimum magic defense.
	 */
	private int getMinMagicDef(Section section) {
		String data = section.get(MIN_MAGIC_DEF_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}

	/**
	 * Retrieves an object's maximum magic defense from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's maximum magic defense.
	 */
	private int getMaxMagicDef(Section section) {
		String data = section.get(MAX_MAGIC_DEF_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}

	/**
	 * Retrieves an object's minimum hit from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's minimum hit.
	 */
	private int getMinHit(Section section) {
		String data = section.get(MIN_HIT_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's maximum hit from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's maximum hit.
	 */
	private int getMaxHit(Section section) {
		String data = section.get(MAX_HIT_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's hunger restored from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's hunger restored.
	 */
	private int getHunger(Section section) {
		return Integer.parseInt(section.get(HUNGER_KEY));
	}
	
	/**
	 * Retrieves an object's thirst restored from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's thirst restored.
	 */
	private int getThirst(Section section) {
		return Integer.parseInt(section.get(THIRST_KEY));
	}
	
	/**
	 * Retrieves an object's piercing damage from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's piercing damage.
	 */
	private int getPiercingDamage(Section section) {
		String data = section.get(PIERCING_DAMAGE_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's magic power from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's magic power.
	 */
	private int getMagicPower(Section section) {
		return Integer.parseInt(section.get(MAGIC_POWER_KEY));
	}
	
	/**
	 * Retrieves an object's damage bonus from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's damage bonus.
	 */
	private int getDamageBonus(Section section) {
		return Integer.parseInt(section.get(DAMAGE_BONUS_KEY));
	}
	
	/**
	 * Retrieves an object's usage difficulty from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's usage difficulty.
	 */
	private int getUsageDifficulty(Section section) {
		String data = section.get(USAGE_DIFFICULTY_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Checks if the section corresponds to a ranged weapon.
	 * @param section The section for the item to check.
	 * @return True if the item is a ranged weapon, false otherwise.
	 */
	private boolean isRangedWeapon(Section section) {
		String data = section.get(IS_RANGED_WEAPON_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section corresponds to a staff.
	 * @param section The section for the item to check.
	 * @return True if the item is a staff, false otherwise.
	 */
	private boolean isStaff(Section section) {
		String data = section.get(MAGIC_POWER_KEY);
		return data != null && !"0".equals(data);
	}
	
	/**
	 * Checks if the section corresponds to a staff.
	 * @param section The section for the item to check.
	 * @return True if the item is a staff, false otherwise.
	 */
	private boolean isGrabable(Section section) {
		String data = section.get(UNGRABABLE_KEY);
		return data == null || !"0".equals(data);
	}
	
	/**
	 * Checks if the object requires ammunition.
	 * @param section The section for the item to check.
	 * @return True if the item requires ammunition, false otherwise.
	 */
	private boolean getNeedsAmmunition(Section section) {
		String data = section.get(NEEDS_AMMUNITION_KEY);
		return data != null && !"0".equals(data);
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
	
	/**
	 * Retrieves an object's radius from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's radius.
	 */
	private int getRadius(Section section) {
		String data = section.get(RADIUS_KEY);
		
		if (data == null) {
			return 0;
		}
		
		return Integer.parseInt(data);
	}
	
	/**
	 * Retrieves an object's potion type from it's section.
	 * @param section The section from which to read the object's value.
	 * @return The object's potion type.
	 */
	private PotionType getPotionType(Section section) {
		return PotionType.valueOf(Integer.parseInt(section.get(POTION_TYPE_KEY)));
	}
	
	/**
	 * Retrieves the object's minimum modifier.
	 * @param section The section from which to read the value.
	 * @return The object's minimum modifier.
	 */
	private int getMinModifier(Section section) {
		return Integer.parseInt(section.get(MIN_MODIFIER));
	}
	
	/**
	 * Retrieves the object's maximum modifier.
	 * @param section The section from which to read the value.
	 * @return The object's maximum modifier.
	 */
	private int getMaxModifier(Section section) {
		return Integer.parseInt(section.get(MAX_MODIFIER));
	}
	
	/**
	 * Retrieves the object's modifier time.
	 * @param section The section from which to read the value.
	 * @return The object's modifier time.
	 */
	private int getEffectTime(Section section) {
		return Integer.parseInt(section.get(MODIFIER_TIME));
	}
	
	
	
	/**
	 * World Object Type enumeration, as it was known in the old days of Visual Basic.
	 */
	private enum LegacyWorldObjectType {
		USE_ONCE(1),
		WEAPON(2),
		ARMOR(3),
		TREE(4),
		MONEY(5),
		DOOR(6),
		CONTAINER(7),
		SIGN(8),
		KEY(9),
		FORUM(10),
		POTION(11),
		BOOK(12),
		DRINK(13),
		WOOD(14),
		BONFIRE(15),
		SHIELD(16),
		HELMET(17),
		RING(18),
		TELEPORT(19),
		FURNITURE(20),
		JEWELRY(21),
		MINE(22),
		MINERAL(23),
		PARCHMENT(24),
		MUSICAL_INSTRUMENT(26),
		ANVIL(27),
		FORGE(28),
		GEMS(29),
		FLOWERS(30),
		BOAT(31),
		ARROW(32),
		EMPTY_BOTTLE(33),
		FILLED_BOTTLE(34),
		STAIN(35),
		ELVEN_TREE(36),
		BACKPACK(37);
		
		protected int value;
		
		/**
		 * Creates a new LegacyWorldObjectType.
		 * @param value The value corresponding to the object type. Should be unique.
		 */
		private LegacyWorldObjectType(int value) {
			this.value = value;
		}
		
		/**
		 * Retrieves the LegacyWorldObjectType associated with the given value.
		 * @param value The value for which to search for a LegacyWorldObjectType.
		 * @return The matched LegacyWorldObjectType, if any.
		 */
		public static LegacyWorldObjectType valueOf(int value) {
			for (LegacyWorldObjectType type : LegacyWorldObjectType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			
			return null;
		}
	}
	
	/**
	 * Legacy potion types. They are separate object types nowadays.
	 */
	private enum PotionType {
		AGILITY(1),
		STRENGTH(2),
		HP(3),
		MANA(4),
		POISON(5),
		DEATH(6);
		
		protected int value;
		
		/**
		 * Creates a new PotionType.
		 * @param value The value corresponding to the potion type. Should be unique.
		 */
		private PotionType(int value) {
			this.value = value;
		}
		
		/**
		 * Retrieves the PotionType associated with the given value.
		 * @param value The value for which to search for a PotionType.
		 * @return The matched PotionType, if any.
		 */
		public static PotionType valueOf(int value) {
			for (PotionType type : PotionType.values()) {
				if (type.value == value) {
					return type;
				}
			}
			
			return null;
		}
	}
}
