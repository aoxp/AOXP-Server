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

/**
 * List of available NPC Types.
 */
public enum NPCType {	
	TRADER,
	HOSTILE,
	RESUCITATOR,
	ROYAL_GUARD,
	TRAINER,
	BANKER,
	NOBLE,
	GAMBLER,
	CHAOS_GUARD,
	NEWBIE_RESUCITATOR,
	GOVERNOR;
	
	/**
	 * Enum values.
	 */
	private static NPCType[] values = NPCType.values();
	
	/**
	 * Retrieves the npc's type for the given index.
	 * @param index The npc's type index.
	 * @return The npc's type .
	 */
	public static NPCType get(byte index) {
		return values[index];
	}
}
