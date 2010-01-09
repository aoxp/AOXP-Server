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
 * Food to be consumed by characters.
 */
public class Food extends ConsumableItem {

	protected int minHun;
	protected int maxHun;

	/**
	 * Creates a new food instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param amount The item's amount.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param minHun The minimum hunger replenished by the food.
	 * @param maxHun The maximum hunger replenished by the food.
	 */
	public Food(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, boolean smallRace,
			List<UserArchetype> forbiddenArchetypes, int minHun, int maxHun) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, smallRace, forbiddenArchetypes);

		this.minHun = minHun;
		this.maxHun = maxHun;
	}

	@Override
	public Item clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void use(Character character) {
		super.use(character);
		// TODO Auto-generated method stub
	}

}
