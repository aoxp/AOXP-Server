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

package ao.model.worldobject.properties;

import java.util.List;

import ao.model.character.archetype.UserArchetype;

/**
 * Defines a Boat's properties. Allows a lightweight pattern implementation.
 */
public class BoatProperties extends DefensiveItemProperties {

	protected int minHit;
	protected int maxHit;
	
	/**
	 * Creates a new BoatProperties instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param newbie Whether the item is newbie or not.
	 * @param minDef The minimum defense granted by this item.
	 * @param maxDef The maximum defense granted by this item.
	 * @param minMagicDef The minimum magic defense granted by this item.
	 * @param maxMagicDef The maximum magic defense granted by this item.
	 * @param minHit The minimum hit granted by this boat.
	 * @param maxHit The maximum hit granted by this boat.
	 */
	public BoatProperties(int id, String name, int graphic, boolean tradeable,
			int value, int usageDifficulty, int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, boolean newbie,
			int equippedGraphic, int minDef, int maxDef, int minMagicDef,
			int maxMagicDef, int minHit, int maxHit) {
		super(id, name, graphic, tradeable, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic,
				minDef, maxDef, minMagicDef, maxMagicDef);
		
		this.minHit = minHit;
		this.maxHit = maxHit;
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
}
