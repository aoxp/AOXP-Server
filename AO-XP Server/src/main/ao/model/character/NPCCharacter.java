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

package ao.model.character;


public interface NPCCharacter extends Character {
	/**
	 * Retrieves the NPC's id.
	 * @return The NPC's id.
	 */
	int getId();
	
	/**
	 * Retrieves the NPC's experience.
	 * @return The NPC's experience.
	 */
	int getExperience();
	
	/**
	 * Retrieves the NPC's gold.
	 * @return The NPC's gold.
	 */
	int getGold();
	
	/**
	 * Retrieves the NPC's master if it has one.
	 * @return The NPC's master if it has one.
	 */
	Character getMaster();
	
	/**
	 * Retrieves the NPC's Type.
	 * @return The NPC's Type.
	 */
	NPCType getNPCType();
	
	/**
	 * Checks if the NPC is tameable or not.
	 * @return True if the NPC is tameable, false otherwise.
	 */
	boolean isTameable();
	
	/**
	 * Checks if the NPC can trade or not.
	 * @return True if the NPC can trade, false otherwise.
	 */
	boolean canTrade();
	
	/**
	 * Checks if the NPC is hostile or not.
	 * @return True if the NPC is hostile, false otherwise.
	 */
	boolean isHostile();
	
	/**
	 * Checks if the NPC has a master.
	 * @return True if has a master, false otherwise.
	 */
	boolean hasMaster();
	
}