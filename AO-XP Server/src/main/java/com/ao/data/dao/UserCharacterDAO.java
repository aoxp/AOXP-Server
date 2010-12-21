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

package com.ao.data.dao;

import com.ao.data.dao.exception.DAOException;
import com.ao.data.dao.exception.NameAlreadyTakenException;
import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.archetype.UserArchetype;

public interface UserCharacterDAO {
	
	/**
	 * Return the character with the given name
	 * @param name Character's name
	 * @return
	 * @throws DAOException 
	 */
	UserCharacter load(String name) throws DAOException;

	/**
	 * Creates and persists a new user character.
	 * @param race 						The character's race.
	 * @param gender 					The character's gender.
	 * @param archetype 				The character's archetype.
	 * @param head	 					The character's head.
	 * @param homeland 					The character's homeland.
	 * @param strength					The character's strength attribute.
	 * @param agility					The character's agility attribute.
	 * @param intelligence				The character's intelligence attribute.
	 * @param charisma					The character's charisma attribute.
	 * @param constitution				The character's constitution attribute.
	 * @param initialAvailableSkills 	The character's initial available skills
	 * @param body					 	The character's body
	 * @return 							The created user character.
	 */
	UserCharacter create(String name, Race race, Gender gender,
			UserArchetype archetype, int head, byte homeland, byte strength,
			byte agility, byte intelligence, byte charisma, byte constitution, 
			int initialAvailableSkills, int body)
			throws DAOException, NameAlreadyTakenException;

	/**
	 * Checks if the character with the given name exists.
	 * @param name The character name.
	 * @return True if the character exists, false otherwise.
	 */
	boolean exists(String name);
}
