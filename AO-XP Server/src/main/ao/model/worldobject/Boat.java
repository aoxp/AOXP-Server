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

package ao.model.worldobject;

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

/**
 * A boat to navigate accross the sea.
 */
public class Boat extends AbstractDefensiveItem {

	protected int minHit;
	protected int maxHit;
	
	/**
	 * Creates a new Boat instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param amount The item's amount.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param newbie Whether the item is newbie or nor.
	 * @param equippedGraphic The index of the graphic when equipped.
	 * @param minDef The minimum defense granted by this boat.
	 * @param maxDef The maximum defense granted by this boat.
	 * @param minMagicDef The minimum magic defense granted by this item.
	 * @param maxMagicDef The maximum magic defense granted by this item.
	 * @param minHit The minimum hit granted by this boat.
	 * @param maxHit The maximum hit granted by this boat.
	 */
	public Boat(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			boolean newbie, int equippedGraphic, int minDef, int maxDef, int minMagicDef, int maxMagicDef, int minHit, int maxHit) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic, minDef, maxDef, minMagicDef, maxMagicDef);
		
		this.minHit = minHit;
		this.maxHit = maxHit;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Boat(id, name, amount, tradeable, graphic, value, usageDifficulty, manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic, minDef, maxDef, minMagicDef, maxMagicDef, minHit, maxHit);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		// We do nothing to the character
	}
	
	/**
	 * Retrieves the minimum hit granted.
	 * @return The minimum hit granted.
	 */
	public int getMinHit() {
		return minHit;
	}
	
	/**
	 * Retrieves the maximum hit granted.
	 * @return The maximum hit granted.
	 */
	public int getMaxHit() {
		return maxHit;
	}
}
