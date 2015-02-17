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

/**
 * 
 */
package com.ao.model.character.archetype;

/**
 * A druid archetype.
 */
public class DruidArchetype extends DefaultArchetype {

	private static final float MANA_MODIFIER = 2.0f;
	private static final float TAMING_TRAINING_CHANCE = 1.0f;
	private static final int INITIAL_MANA = 50;
	
	public DruidArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return Math.round(intelligence * MANA_MODIFIER);
	}
	
	@Override
	public float getTamingTrainingChance() {
		return TAMING_TRAINING_CHANCE;
	}
	
	@Override
	public int getInitialMana(int intelligence){
		return INITIAL_MANA;
	}
	
}