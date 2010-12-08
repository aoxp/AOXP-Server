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

package com.ao.model.character;

import com.ao.model.character.archetype.Archetype;

public interface UserCharacter extends Character {

	/**
	 * Retrieves the user's requested skill points.
	 * @param skill	The skill.
	 * @return	The amount of skill points.
	 */
	int getSkill(Skill skill);
	
	/**
	 * Adds points to the given user's skill. 
	 * @param skill		The skill to add points.
	 * @param points	The points to add.
	 */
	void addToSkill(Skill skill, byte points);
	
	/**
	 * Checks if the user is doing some work.
	 * @return True if the user is working, false other wise.
	 */
	boolean isWorking();
	
	/**
	 * Sets the user to work.
	 */
	void work();
	
	/**
	 * Retrieves the user's guild name.
	 * @return The user's guild name.
	 */
	String getGuildName();
	
	/**
	 * Sets the user's guild name.
	 * @param name The guild name to be setted.
	 */
	void setGuildName(String name);
	
	/**
	 * Retrieves the user's party id.
	 * @return The user's party id if the user belongs to one, -1 otherwise.
	 */
	int getPartyId();
	
	/**
	 * Sets the user's party id.
	 * @param id The party id to be setted.
	 */
	void setPartyId(int id);
	
	/**
	 * Retrieves the user's race.
	 * @return The user's race.
	 */
	Race getRace();
	
	/**
	 * Retrieves the user's gender.
	 * @return The user's gender.
	 */
	Gender getGender();
	
	/**
	 * Retrieves the user's archetype.
	 * @return The user's archetype.
	 */
	Archetype getArchetype();
}