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

package com.ao.model.character.archetype;

/**
 * A thief archetype.
 */
public class ThiefArchetype extends DefaultArchetype {

	private static final int HIT_INCREMENT = 1;
	private static final int STAMINA_INCREMENT = 18;
	private static final int STOLEN_MAX_AMOUNT = 800;
	private static final int STOLEN_MIN_AMOUNT = 80;
	
	public ThiefArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public boolean canDisarm() {
		return true;
	}

	@Override
	public boolean canImmobilize() {
		return true;
	}
	
	@Override
	public boolean canWalkHidden() {
		return true;
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getStolenMaxAmount() {
		return STOLEN_MAX_AMOUNT;
	}

	@Override
	public int getStolenMinAmount() {
		return STOLEN_MIN_AMOUNT;
	}

}
