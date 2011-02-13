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
 * A miner archetype.
 */
public class MinerArchetype extends DefaultArchetype {

	private static final int STAMINA_INCREMENT = 40;
	private static final int MINED_MAX_AMOUNT = 6;
	private static final int MINED_MIN_AMOUNT = 1;
	private static final int MINING_STAMINA_COST = 2;
	private static final float BLACKSMITH_MODIFIER = 1.2f;
	private static final float IRON_WORKING_MODIFIER = 1.0f;
	
	public MinerArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getMiningMaxAmount() {
		return MINED_MAX_AMOUNT;
	}

	@Override
	public int getMiningMinAmount() {
		return MINED_MIN_AMOUNT;
	}

	@Override
	public int getMiningStaminaCost() {
		return MINING_STAMINA_COST;
	}
	
	@Override
	protected float getIronWorkingModifier() {
		return IRON_WORKING_MODIFIER;
	}
	
	@Override
	protected float getBlacksmithModifier() {
		return BLACKSMITH_MODIFIER;
	}
	
}
