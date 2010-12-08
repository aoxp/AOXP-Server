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

package com.ao.model.character.behavior;

import com.ao.model.character.AIType;
import com.ao.model.character.Character;

/**
 * Defines a character's behavior (AI)
 */
public interface Behavior {

	/**
	 * Tells the behavior that the character was attacked by another.
	 * @param character The character that attacked.
	 */
	void attackedBy(Character character);
	
	/**
	 * Retrieves the NPC's AI type.
	 * @return The NPC's AI type.
	 */
	AIType getAIType();
	
	/**
	 * Performs an action according to the behavior.
	 */
	void takeAction();
	
}
