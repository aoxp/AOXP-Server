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
import com.ao.model.worldobject.properties.RefillableStatModifyingItemProperties;
import com.ao.model.worldobject.properties.StatModifyingItemProperties;

/**
 * A bottle filled with water to be consumed by characters.
 */
public class FilledBottle extends ConsumableItem {

	/**
	 * Creates a new filled bottle instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public FilledBottle(RefillableStatModifyingItemProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new FilledBottle((RefillableStatModifyingItemProperties) properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.ConsumableItem#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		super.use(character);

		RefillableStatModifyingItemProperties itemProperties = (RefillableStatModifyingItemProperties) properties;

		character.addToThirstiness(itemProperties.getMaxModifier());

		EmptyBottle emptyBottle = new EmptyBottle(itemProperties.getOtherStateProperties(), 1);
		character.getInventory().addItem(emptyBottle);
	}

	/**
	 * Retrieves the thirst restored by the drink.
	 * @return The thirst restored by the drink.
	 */
	public int getThirst() {
		return ((StatModifyingItemProperties) properties).getMaxModifier();
	}
}
