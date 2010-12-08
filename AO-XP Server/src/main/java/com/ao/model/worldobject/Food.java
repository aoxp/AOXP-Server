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
import com.ao.model.worldobject.properties.StatModifyingItemProperties;

/**
 * Food to be consumed by characters.
 */
public class Food extends ConsumableItem {

	/**
	 * Creates a new food instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public Food(StatModifyingItemProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Food((StatModifyingItemProperties) properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.ConsumableItem#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		super.use(character);
		
		int minModifier = ((StatModifyingItemProperties) properties).getMinModifier();
		int maxModifier = ((StatModifyingItemProperties) properties).getMaxModifier();
		
		character.addToHunger((int) (Math.random() * (maxModifier - minModifier + 1)) + minModifier);
	}

	/**
	 * Retrieves the minimum hunger restored by the food.
	 * @return The minimum hunger restored by the food.
	 */
	public int getMinHun() {
		return ((StatModifyingItemProperties) properties).getMinModifier();
	}

	/**
	 * Retrieves the maximum hunger restored by the food.
	 * @return The maximum hunger restored by the food.
	 */
	public int getMaxHun() {
		return ((StatModifyingItemProperties) properties).getMaxModifier();
	}

}
