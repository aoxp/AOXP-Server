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

package com.ao.model.worldobject;

import com.ao.model.character.Character;
import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.Reputation;
import com.ao.model.character.archetype.UserArchetype;

public interface Item extends WorldObject, Cloneable {
	
	/**
	 * Retrieves the item's amount.
	 * @return The amount of items put together.
	 */
	int getAmount();
	
	/**
	 * Attempts to use the item.
	 * @param character The character using this item.
	 */
	void use(Character character);
	
	/**
	 * Adds (or subtracts if the given amount is negative) an amount of the item
	 * @param amount	references to the amount to be added (negative to subtract)
	 * @return			the new amount of the item.
	 */
	int addAmount(int amount);
	
	/**
	 * Checks if the item can be used/equipped given a race, gender, archetype and reputation.
	 * @param race			The race attempting to use the item.
	 * @param gender		The gender of the character attempting to use the item.
	 * @param archetype		The archetype attempting to use the item.
	 * @param reputation	The reputation of the character attempting to use the item.
	 * @return True if the item can be used, false otherwise.
	 */
	boolean canBeUsedBy(Race race, Gender gender, UserArchetype archetype, Reputation reputation);
	
	/**
	 * Retrieves the manufacture difficulty of the item (how hard it's to make).
	 * @return The manufacture difficulty of the item (how hard it's to make).
	 */
	int getManufactureDifficulty(); // Nota, esto reemplaza a minSkill para carpinteria, fundicion y herreria
	
	/**
	 * Gets a deep clone of the item
	 * @return a copy of the item.
	 */
	Item clone();
	
	/**
	 * Retrieves the item's value
	 * @return The item's value.
	 */
	int getValue();
	
	/**
	 * Checks if the item is newbie.
	 * @return Whether the item is newbie or not.
	 */
	boolean isNewbie();
	
	/**
	 * Checks if the item can be stolen.
	 * @return True if the item can be stolen, false otherwise.
	 */
	boolean canBeStolen();
}
