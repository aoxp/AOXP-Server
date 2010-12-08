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
 * A potion to recover mana.
 */
public class ManaPotion extends ConsumableItem {
	
	/**
	 * Creates a new ManaPotion instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public ManaPotion(StatModifyingItemProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new ManaPotion((StatModifyingItemProperties) properties, amount);
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
		
		character.addToMana((int) (Math.random() * (maxModifier - minModifier + 1)) + minModifier);
	}

	/**
	 * Retrieves the minimum man restored by the potion.
	 * @return The minimum mana restored by the potion.
	 */
	public int getMinMana() {
		return ((StatModifyingItemProperties) properties).getMinModifier();
	}

	/**
	 * Retrieves the maximum mana restored by the potion.
	 * @return The maximum mana restored by the potion.
	 */
	public int getMaxMana() {
		return ((StatModifyingItemProperties) properties).getMaxModifier();
	}
}
