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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.ini4j.Ini;

import com.ao.data.dao.AccountDAO;
import com.ao.data.dao.UserCharacterDAO;
import com.ao.data.dao.exception.DAOException;
import com.ao.data.dao.exception.NameAlreadyTakenException;
import com.ao.model.character.Attribute;
import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.Reputation;
import com.ao.model.character.ReputationImpl;
import com.ao.model.character.Skill;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.archetype.Archetype;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.map.Heading;
import com.ao.model.map.City;
import com.ao.model.map.Position;
import com.ao.model.user.Account;
import com.ao.model.user.AccountImpl;
import com.ao.model.user.LoggedUser;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class UserDAOIni implements AccountDAO, UserCharacterDAO {
	
	public static final String FILE_EXTENSION = ".chr";
	
	protected static final String INIT_HEADER 		= "INIT";
	protected static final String PASSWORD_KEY 		= "Password";
	protected static final String GENDER_KEY		= "Genero";
	protected static final String RACE_KEY			= "Raza";
	protected static final String HOMELAND_KEY		= "Hogar";
	protected static final String ARCHETYPE_KEY		= "Clase";
	protected static final String HEADING_KEY		= "Heading";
	protected static final String HEAD_KEY			= "Head";
	protected static final String BODY_KEY			= "Body";
	protected static final String WEAPON_KEY		= "Arma";
	protected static final String SHIELD_KEY		= "Escudo";
	protected static final String HELMET_KEY		= "Casco";
	protected static final String UPTIME_KEY		= "UpTime";
	protected static final String POSITION_KEY		= "Position";
	protected static final String LOGGED_KEY		= "Logged";
	protected static final String LAST_IP_FORMAT 	= "LastIP%d";
	protected static final byte LOGGED_IPS_AMOUNT 	= 5;
	
	protected static final String FLAGS_HEADER	= "FLAGS";
	protected static final String BANNED_KEY 	= "Ban"; 
	protected static final String DEAD_KEY 		= "Muerto";
	protected static final String HIDDEN_KEY	= "Escondido";
	protected static final String THIRSTY_KEY 	= "Sed";
	protected static final String HUNGRY_KEY	= "Hambre";
	protected static final String SAILING_KEY	= "Navegando";
	protected static final String POISONED_KEY	= "Envenenado";
	protected static final String PARALYZED_KEY = "Paralizado";
	
	protected static final String COUNCIL_HEADER 				= "CONSEJO";
	protected static final String BELONGS_KEY 					= "PERTENECE";
	protected static final String BELONGS_TO_CHAOS_COUNCIL_KEY	= "PERTENECECAOS";
	
	protected static final String COUNTERS_HEADER 		= "COUNTERS";
	protected static final String LEFT_TIME_IN_JAIL_KEY 	= "Pena";
	
	protected static final String FACTIONS_HEADER 				= "FACCIONES";
	protected static final String BELONGS_TO_ARMY_KEY 			= "EjercitoReal";
	protected static final String BELONGS_TO_CHAOS_KEY 			= "EjercitoCaos";
	protected static final String CITIZENS_KILLED_KEY 			= "CiudMatados";
	protected static final String CRIMINALS_KILLED_KEY 			= "CrimMatados";
	protected static final String CHAOS_ARMOR_RECEIVED_KEY 		= "rArCaos";
	protected static final String ARMY_ARMOR_RECEIVED_KEY 		= "rArReal";
	protected static final String CHAOS_EXPERIENCE_RECEIVED_KEY = "rExCaos";
	protected static final String ARMY_EXPERIENCE_RECEIVED_KEY 	= "rExReal";
	protected static final String CHAOS_GRADE_KEY 				= "recCaos";
	protected static final String ARMY_GRADE_KEY 				= "recReal";
	protected static final String REENLISTMENTS_KEY 			= "Reenlistadas";
	protected static final String ENLISTMENT_LEVEL_KEY 			= "NivelIngreso";
	protected static final String ENLISTMENT_DATE_KEY 			= "FechaIngreso";
	protected static final String ENLISTMENT_KILLS_KEY 			= "MatadosIngreso";
	protected static final String NEXT_REWARD_KEY 				= "NextRecompensa";
	
	protected static final String ATTRIBUTES_HEADER = "ATRIBUTOS";
	protected static final String ATTRIBUTE_FORMAT_KEY = "AT%d";

	protected static final String SKILLS_HEADER = "SKILLS";
	protected static final String SKILL_KEY_FORMAT = "SK%d";
	
	protected static final String CONTACT_HEADER	= "CONTACTO";
	protected static final String MAIL_KEY 		= "Email";
	
	protected static final String STATS_HEADER 					= "STATS";
	protected static final String GOLD_KEY						= "GLD";
	protected static final String DEPOSITED_GOLD_KEY 			= "BANCO";
	protected static final String MAX_HP_KEY					= "MaxHP";
	protected static final String MIN_HP_KEY					= "MinHP";
	protected static final String MAX_STAMINA_KEY				= "MaxSTA";
	protected static final String MIN_STAMINA_KEY				= "MinSTA";
	protected static final String MAX_MANA_KEY					= "MaxMAN";
	protected static final String MIN_MANA_KEY					= "MinMAN";
	protected static final String MAX_HIT_KEY					= "MaxHIT";
	protected static final String MIN_HIT_KEY					= "MinHIT";
	protected static final String MAX_THIRSTINESS_KEY			= "MaxAGU";
	protected static final String MIN_THIRSTINESS_KEY			= "MinAGU";
	protected static final String MAX_HUNGER_KEY				= "MaxHAM";
	protected static final String MIN_HUNGER_KEY				= "MinHAM";
	protected static final String FREE_SKILL_POINTS_KEY 		= "SkillPtsLibres";
	protected static final String EXPERIENCE_KEY				= "EXP";
	protected static final String LEVEL_KEY						= "ELV";
	protected static final String EXPERIENCE_TO_LEVEL_UP_KEY 	= "ELU";
	
	protected static final String KILLS_HEADER 		= "MUERTES";
	protected static final String KILLED_USERS_KEY 	= "UserMuertes";
	protected static final String KILLED_NPCS_KEY	= "NpcsMuertes";
	
	protected static final String BANK_INVENTORY_HEADER = "BancoInventory";
	protected static final String ITEMS_AMOUNT_KEY 		= "CantidadItems";
	protected static final String ITEM_KEY_FORMAT		= "Obj%d";
	
	protected static final String INVENTORY_HEADER 			= "Inventory";
	protected static final String EQUIPPED_WEAPON_SLOT_KEY 	= "WeaponEqpSlot";
	protected static final String EQUIPPED_ARMOUR_SLOT_KEY 	= "ArmourEqpSlot";
	protected static final String EQUIPPED_HELMET_SLOT_KEY 	= "CascoEqpSlot";
	protected static final String EQUIPPED_BOAT_SLOT_KEY 	= "BarconEqpSlot";
	protected static final String MUNITION_SLOT_KEY			= "MunicionSlot";
	protected static final String RING_SLOT_KEY				= "AnilloSlot";
	
	protected static final String REPUTATION_HEADER 	= "REP";
	protected static final String ASSASSIN_POINTS_KEY 	= "Asesino";
	protected static final String BANDIT_POINTS_KEY		= "Bandido";
	protected static final String BOURGEOIS_POINTS_KEY 	= "Burguesia";
	protected static final String THIEF_POINTS_KEY		= "Ladrones";
	protected static final String NOBLE_POINTS_KEY		= "Nobles";
	
	protected static final String SPELLS_HEADER 	= "HECHIZOS";
	protected static final String SPELL_KEY_FORMAT 	= "H%d";
	// TODO: This shouldn't be here
	protected static final byte MAX_SPELLS_AMOUNT 	= 35;
	
	protected static final String PETS_HEADER 		= "MASCOTAS";
	protected static final String PET_KEY_FORMAT	= "MAS%d";
	// TODO: This shouldn't be here
	protected static final byte MAX_PETS_AMOUNT 	= 3;
	
	protected static final String RESEARCH_HEADER 	= "RESEARCH";
	protected static final String TRAINING_TIME_KEY = "TrainingTime";
	
	protected static final String GUILD_HEADER 		= "GUILD";
	protected static final String GUILD_INDEX_KEY 	= "GUILDINDEX";
	protected static final String APPLICANT_TO_KEY 	= "AspiranteA";
	
	protected static final String CRIMINAL_RECORD_HEADER 	= "PENAS";
	protected static final String RECORDS_AMOUNT_KEY 		= "Cant";
	protected static final String RECORD_KEY_FORMAT 		= "P%d";
	
	protected static final byte NO_SHIELD = 2;
	protected static final byte NO_WEAPON = 2;
	protected static final byte NO_HELMET = 2;
	// TODO: This shouldn't be here.
	protected static final String NO_ENLISTMENT_KEY_MESSAGE = "No ingresó a ninguna facción";
	protected static final int INITIAL_NOBLE_POINTS = 1000;
	
	private static final Logger logger = Logger.getLogger(UserDAOIni.class);
	
	private String charfilesPath;
	
	@Inject
	public UserDAOIni(@Named("CharfilesPath") String charfilesPath) {
		this.charfilesPath = charfilesPath;
	}
	
	@Override
	public Account retrieve(String username) throws DAOException {
		Ini chara = readCharFile(username);
		
		if (null == chara) {
			return null;
		}
		
		// Add the single character's name.
		Set<String> characters = new HashSet<String>();
		characters.add(username);
		
		return new AccountImpl(
				username,
				chara.get(INIT_HEADER, PASSWORD_KEY),
				chara.get(CONTACT_HEADER, MAIL_KEY),
				characters,
				chara.get(FLAGS_HEADER, BANNED_KEY).equals("1")
		);
	}
	
	@Override
	public Account create(String name, String password, String mail)
			throws DAOException, NameAlreadyTakenException {
		
		if (exists(name)) {
			throw new NameAlreadyTakenException();
		}
		
		Ini acc = new Ini();
		
		acc.put(INIT_HEADER, PASSWORD_KEY, password);
		acc.put(CONTACT_HEADER, MAIL_KEY, mail);
		acc.put(FLAGS_HEADER, BANNED_KEY, "0");
		
		try {
			Writer writer = new BufferedWriter(new FileWriter(getCharFilePath(name)));
			acc.store(writer);
			
			// Make sure the writer is closed, since Ini4j gives no guarantees.
			writer.close();
		} catch (IOException e) {
			logger.error("Charfile (account data) creation failed!", e);
			throw new DAOException();
		}
		
		return new AccountImpl(name, password, mail, new HashSet<String>(), false);
	}

	@Override
	public void delete(String name) {
		File charfile = new File(getCharFilePath(name));
		
		if (!charfile.exists()) {
			return;
		}
	
		boolean success = charfile.delete();
		
		if (!success) {
			logger.error(String.format("Character (%s) deletion failed", name));
		}
	}

	@Override
	public UserCharacter create(String name, Race race, Gender gender,
			UserArchetype archetype, int head, City homeland, byte strength,
			byte agility, byte intelligence, byte charisma, byte constitution, 
			int initialAvailableSkills, int body)
			throws DAOException, NameAlreadyTakenException {
		Ini chara = new Ini();
		
		chara.put(INIT_HEADER, GENDER_KEY, gender.ordinal());
		chara.put(INIT_HEADER, RACE_KEY, race.ordinal());
		chara.put(INIT_HEADER, HOMELAND_KEY, homeland);
		chara.put(INIT_HEADER, ARCHETYPE_KEY, archetype.ordinal());
		chara.put(INIT_HEADER, HEADING_KEY, Heading.SOUTH.ordinal());
		chara.put(INIT_HEADER, WEAPON_KEY, NO_WEAPON);
		chara.put(INIT_HEADER, SHIELD_KEY, NO_SHIELD);
		chara.put(INIT_HEADER, HELMET_KEY, NO_HELMET);
		chara.put(INIT_HEADER, UPTIME_KEY, 0);
		chara.put(INIT_HEADER, HEAD_KEY, head);
		chara.put(INIT_HEADER, BODY_KEY, body);
		
		String positionKey = homeland.getMap() + "-" + homeland.getX() + "-" + homeland.getY();
		chara.put(INIT_HEADER, POSITION_KEY, positionKey );
		// TODO: Save last ip?
		
		chara.put(FLAGS_HEADER, BANNED_KEY, 0);
		chara.put(FLAGS_HEADER, DEAD_KEY, 0);
		chara.put(FLAGS_HEADER, HIDDEN_KEY, 0);
		chara.put(FLAGS_HEADER, THIRSTY_KEY, 0);
		chara.put(FLAGS_HEADER, SAILING_KEY, 0);
		chara.put(FLAGS_HEADER, POISONED_KEY, 0);
		chara.put(FLAGS_HEADER, PARALYZED_KEY, 0);
		
		chara.put(COUNCIL_HEADER, BELONGS_KEY, 0);
		chara.put(COUNCIL_HEADER, BELONGS_TO_CHAOS_COUNCIL_KEY, 0);
		
		chara.put(COUNTERS_HEADER, LEFT_TIME_IN_JAIL_KEY, 0);
		
		chara.put(FACTIONS_HEADER, BELONGS_TO_ARMY_KEY, 0);
		chara.put(FACTIONS_HEADER, BELONGS_TO_CHAOS_KEY, 0);
		chara.put(FACTIONS_HEADER, BELONGS_TO_CHAOS_KEY, 0);
		chara.put(FACTIONS_HEADER, CRIMINALS_KILLED_KEY, 0);
		chara.put(FACTIONS_HEADER, CHAOS_ARMOR_RECEIVED_KEY, 0);
		chara.put(FACTIONS_HEADER, ARMY_ARMOR_RECEIVED_KEY, 0);
		chara.put(FACTIONS_HEADER, CHAOS_ARMOR_RECEIVED_KEY, 0);
		chara.put(FACTIONS_HEADER, ARMY_EXPERIENCE_RECEIVED_KEY, 0);
		chara.put(FACTIONS_HEADER, CHAOS_GRADE_KEY, 0);
		chara.put(FACTIONS_HEADER, ARMY_GRADE_KEY, 0);
		chara.put(FACTIONS_HEADER, REENLISTMENTS_KEY, 0);
		chara.put(FACTIONS_HEADER, ENLISTMENT_LEVEL_KEY, 0);
		chara.put(FACTIONS_HEADER, ENLISTMENT_DATE_KEY, NO_ENLISTMENT_KEY_MESSAGE);
		chara.put(FACTIONS_HEADER, ENLISTMENT_KILLS_KEY, 0);
		chara.put(FACTIONS_HEADER, NEXT_REWARD_KEY, 0);

		chara.put(ATTRIBUTES_HEADER, String.format(ATTRIBUTE_FORMAT_KEY,Attribute.STRENGTH.ordinal() + 1), strength);
		chara.put(ATTRIBUTES_HEADER, String.format(ATTRIBUTE_FORMAT_KEY, Attribute.AGILITY.ordinal() + 1), agility);
		chara.put(ATTRIBUTES_HEADER, String.format(ATTRIBUTE_FORMAT_KEY, Attribute.CHARISMA.ordinal() + 1), charisma);
		chara.put(ATTRIBUTES_HEADER, String.format(ATTRIBUTE_FORMAT_KEY, Attribute.CONSTITUTION.ordinal() + 1), constitution);
		chara.put(ATTRIBUTES_HEADER, String.format(ATTRIBUTE_FORMAT_KEY, Attribute.INTELLIGENCE.ordinal() + 1), intelligence);
		
		for (byte i = 1; i < Skill.AMOUNT; i++) {
			chara.put(SKILLS_HEADER, String.format(SKILL_KEY_FORMAT, i + 1), 0);
		}
		
		chara.put(STATS_HEADER, FREE_SKILL_POINTS_KEY, initialAvailableSkills);
		
		chara.put(STATS_HEADER, GOLD_KEY, 0);
		chara.put(STATS_HEADER, DEPOSITED_GOLD_KEY, 0);
		chara.put(STATS_HEADER, LEVEL_KEY, 1);
		chara.put(STATS_HEADER, EXPERIENCE_KEY, 0);
		// TODO: Assign HP, mana, stamina, and free skill points.
		// TODO: Assign experience to level up.

		chara.put(KILLS_HEADER, KILLED_USERS_KEY, 0);
		chara.put(KILLS_HEADER, KILLED_NPCS_KEY, 0);

		chara.put(BANK_INVENTORY_HEADER, ITEMS_AMOUNT_KEY, 0);
		
		// TODO: Put initial items.
		chara.put(INVENTORY_HEADER, EQUIPPED_WEAPON_SLOT_KEY, 0);
		chara.put(INVENTORY_HEADER, EQUIPPED_ARMOUR_SLOT_KEY, 0);
		chara.put(INVENTORY_HEADER, EQUIPPED_HELMET_SLOT_KEY, 0);
		chara.put(INVENTORY_HEADER, EQUIPPED_BOAT_SLOT_KEY, 0);
		chara.put(INVENTORY_HEADER, MUNITION_SLOT_KEY, 0);
		chara.put(INVENTORY_HEADER, RING_SLOT_KEY, 0);
		
		chara.put(REPUTATION_HEADER, ASSASSIN_POINTS_KEY, 0);
		chara.put(REPUTATION_HEADER, BANDIT_POINTS_KEY, 0);
		chara.put(REPUTATION_HEADER, BOURGEOIS_POINTS_KEY, 0);
		chara.put(REPUTATION_HEADER, THIEF_POINTS_KEY, 0);
		chara.put(REPUTATION_HEADER, NOBLE_POINTS_KEY, INITIAL_NOBLE_POINTS);
		// TODO: Assign initial spells.

		for (byte i = 1; i < MAX_PETS_AMOUNT + 1; i++) {
			chara.put(PETS_HEADER, String.format(PET_KEY_FORMAT, i), 0);
		}
		
		chara.put(RESEARCH_HEADER, TRAINING_TIME_KEY, 0);
		
		chara.put(GUILD_HEADER, GUILD_INDEX_KEY, 0);
		chara.put(GUILD_HEADER, APPLICANT_TO_KEY, 0);
		
		chara.put(CRIMINAL_RECORD_HEADER, RECORDS_AMOUNT_KEY, 0);
		
		Reputation rep = new ReputationImpl(0, 0, 0, 0, INITIAL_NOBLE_POINTS, false);
	
		try {
			Writer writer = new BufferedWriter(new FileWriter(getCharFilePath(name)));
			chara.store(writer);
			
			// Make sure the stream is closed, since Ini4J gives no guarantees.
			writer.close();
		} catch (IOException e) {
			logger.error("Charfile (full charfile) creation failed!", e);
			throw new DAOException();
		}
		
		// TODO: Update this when hp, mana and hit points get updated!
		return new LoggedUser(rep, race, gender, archetype.getArchetype(),
				false, false, false, false, false, false, false, 0, 0, 0, 0,
				100, 100, (byte) 1, name, "");
	}
	
	@Override
	public boolean exists(String name) {
		return (new File(getCharFilePath(name))).exists();
	}
	
	/**
	 * Retrieves the path to the given character's file.
	 * @param name The character's name.
	 * @return 		The path to the character's file.
	 */
	protected String getCharFilePath(String name) {
		return charfilesPath + name.toUpperCase() + FILE_EXTENSION;
	}

	@Override
	public UserCharacter load(String username) throws DAOException {
		Ini chara = readCharFile(username);
		
		if (null == chara) {
			return null;
		}
		
		int assassinPoints = Integer.parseInt(chara.get(REPUTATION_HEADER, ASSASSIN_POINTS_KEY));
		int banditPoints = Integer.parseInt(chara.get(REPUTATION_HEADER, BANDIT_POINTS_KEY));
		int bourgeoisPoints = Integer.parseInt(chara.get(REPUTATION_HEADER, BOURGEOIS_POINTS_KEY));
		int thiefPoints = Integer.parseInt(chara.get(REPUTATION_HEADER, THIEF_POINTS_KEY));
		int noblePoints = Integer.parseInt(chara.get(REPUTATION_HEADER, NOBLE_POINTS_KEY));
		boolean belongsToFaction = chara.get(REPUTATION_HEADER, BELONGS_TO_CHAOS_KEY).equals("1") || chara.get(REPUTATION_HEADER, BELONGS_TO_ARMY_KEY).equals("1");
		
		Reputation reputation = new ReputationImpl(assassinPoints, banditPoints, bourgeoisPoints, thiefPoints, noblePoints, 
				belongsToFaction);
		
		Race race = Race.get(Byte.parseByte(chara.get(INIT_HEADER, RACE_KEY)));
		
		Gender gender = Gender.get(Byte.parseByte(chara.get(INIT_HEADER, GENDER_KEY)));
		
		Archetype archetype = UserArchetype.get(Byte.parseByte(chara.get(INIT_HEADER, ARCHETYPE_KEY))).getArchetype();
		
		boolean poisoned = chara.get(FLAGS_HEADER, POISONED_KEY).equals("1");
		
		boolean paralyzed = chara.get(FLAGS_HEADER, PARALYZED_KEY).equals("1");
		
		// TODO : check what to do, immobilized state isn't saved in charfile
		boolean immobilized = false;
		
		boolean invisible = false;
		
		boolean mimetized = false;
		
		boolean dumbed = false;
		
		boolean hidden = chara.get(FLAGS_HEADER, HIDDEN_KEY).equals("1");
		
		int maxMana = Integer.parseInt(chara.get(STATS_HEADER, MAX_MANA_KEY)); 
		int maxHitPoints = Integer.parseInt(chara.get(STATS_HEADER, MAX_HIT_KEY));
		int mana = Integer.parseInt(chara.get(STATS_HEADER, MIN_MANA_KEY));
		int hitpoints = Integer.parseInt(chara.get(STATS_HEADER, MIN_HIT_KEY));
		int thirstiness = Integer.parseInt(chara.get(STATS_HEADER, MIN_THIRSTINESS_KEY));
		int hunger = Integer.parseInt(chara.get(STATS_HEADER, MIN_THIRSTINESS_KEY));
		byte lvl = Byte.parseByte(chara.get(STATS_HEADER, LEVEL_KEY));
		
		// TODO : Complete description
		String description = "";
		
		// TODO : Validate character
		UserCharacter userCharacter = new LoggedUser(reputation, race, gender, archetype, poisoned, paralyzed, immobilized, invisible, mimetized, dumbed, hidden, maxMana, maxHitPoints, mana, hitpoints, thirstiness, hunger, lvl, username, description);
		
		return userCharacter;
	}
	
	private Ini readCharFile(String username) throws DAOException { 
		Ini chara;
		
		try {
			// Make sure the reader is closed, since Ini4J gives no guarantees.
			Reader reader = new BufferedReader(new FileReader(getCharFilePath(username)));
			chara = new Ini(reader);
			reader.close();
		} catch (FileNotFoundException e) {
			// The account doesn't exists.
			return null;
			
		} catch (IOException e) {
			logger.error("Charfile loading failed!", e);
			throw new DAOException();
		}
		
		return chara;
	}
}
