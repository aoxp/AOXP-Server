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
import com.ao.model.worldobject.properties.TemporalStatModifyingItemProperties;

/**
 * A potion to increase strength.
 */
public class StrengthPotion extends ConsumableItem {
	
	// TODO : Add timing for effect!!
	
	/**
	 * Creates a new StrengthPotion instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public StrengthPotion(TemporalStatModifyingItemProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new StrengthPotion((TemporalStatModifyingItemProperties) properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.ConsumableItem#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		super.use(character);

		int minModifier = ((TemporalStatModifyingItemProperties) properties).getMinModifier();
		int maxModifier = ((TemporalStatModifyingItemProperties) properties).getMaxModifier();
		int time = ((TemporalStatModifyingItemProperties) properties).getEffectDuration();
		
		// increase strength!
		character.addToStrength((int) (Math.random() * (maxModifier - minModifier + 1)) + minModifier, time);
	}

	/**
	 * Retrieves the minimum modifier for this potion.
	 * @return The minimum modifier for this potion.
	 */
	public int getMinModifier() {
		return ((TemporalStatModifyingItemProperties) properties).getMinModifier();
	}

	/**
	 * Retrieves the maximim modifier for this potion.
	 * @return The maximim modifier for this potion.
	 */
	public int getMaxModifier() {
		return ((TemporalStatModifyingItemProperties) properties).getMaxModifier();
	}
	
	/**
	 * Retrieves the effect's duration.
	 * @return The effect's duration
	 */
	public int getEffectDuration() {
		return ((TemporalStatModifyingItemProperties) properties).getEffectDuration();
	}
}
