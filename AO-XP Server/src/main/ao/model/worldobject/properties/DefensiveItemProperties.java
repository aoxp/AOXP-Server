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
 * Defines a Defensive Item's properties. Allows a lightweight pattern implementation.
 */
public class DefensiveItemProperties extends EquippableItemProperties {

	protected int minDef;
	protected int maxDef;

	protected int minMagicDef;
	protected int maxMagicDef;
	
	/**
	 * Creates a new DefensiveItemProperties instance.
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
	 */
	public DefensiveItemProperties(int id, String name, int graphic,
			boolean tradeable, int value, int usageDifficulty,
			int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, boolean newbie,
			int equippedGraphic, int minDef, int maxDef, int minMagicDef, int maxMagicDef) {
		super(id, name, graphic, tradeable, value, usageDifficulty, manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic);

		this.minDef = minDef;
		this.maxDef = maxDef;
		
		this.minMagicDef = minMagicDef;
		this.maxMagicDef = maxMagicDef;
	}

	/**
	 * @return the minDef
	 */
	public int getMinDef() {
		return minDef;
	}

	/**
	 * @return the maxDef
	 */
	public int getMaxDef() {
		return maxDef;
	}

	/**
	 * @return the minMagicDef
	 */
	public int getMinMagicDef() {
		return minMagicDef;
	}

	/**
	 * @return the maxMagicDef
	 */
	public int getMaxMagicDef() {
		return maxMagicDef;
	}
}
