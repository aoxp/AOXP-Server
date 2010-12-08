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

package com.ao.model.worldobject.properties;

import java.util.List;

import com.ao.model.character.Race;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.worldobject.WorldObjectType;

/**
 * Defines a Boat's properties. Allows a lightweight pattern implementation.
 */
public class BoatProperties extends DefensiveItemProperties {

	protected int minHit;
	protected int maxHit;
	
	protected int usageDifficulty;
	
	/**
	 * Creates a new BoatProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param forbiddenRaces List of Races not allowed to use this item.
	 * @param newbie Whether the item is newbie or not.
	 * @param minDef The minimum defense granted by this item.
	 * @param maxDef The maximum defense granted by this item.
	 * @param minMagicDef The minimum magic defense granted by this item.
	 * @param maxMagicDef The maximum magic defense granted by this item.
	 * @param minHit The minimum hit granted by this boat.
	 * @param maxHit The maximum hit granted by this boat.
	 */
	public BoatProperties(WorldObjectType type, int id, String name, int graphic, int value, int usageDifficulty, int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, List<Race> forbiddenRaces, boolean newbie,
			boolean noLog, boolean falls, boolean respawnable,
			int equippedGraphic, int minDef, int maxDef, int minMagicDef,
			int maxMagicDef, int minHit, int maxHit) {
		super(type, id, name, graphic, value,
				manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, noLog, falls, respawnable, equippedGraphic,
				minDef, maxDef, minMagicDef, maxMagicDef);
		
		this.minHit = minHit;
		this.maxHit = maxHit;
		
		this.usageDifficulty = usageDifficulty;
	}

	/**
	 * @return the minHit
	 */
	public int getMinHit() {
		return minHit;
	}

	/**
	 * @return the maxHit
	 */
	public int getMaxHit() {
		return maxHit;
	}

	/**
	 * @return the usageDifficulty
	 */
	public int getUsageDifficulty() {
		return usageDifficulty;
	}
}
