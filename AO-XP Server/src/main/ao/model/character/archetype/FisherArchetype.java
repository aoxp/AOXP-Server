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

package ao.model.character.archetype;

/**
 * A fisher archetype.
 */
public class FisherArchetype extends DefaultArchetype {

	private static final int HIT_INCREMENT = 1;
	private static final int SAILING_MIN_LEVEL = 20;
	private static final float SAILING_MODIFIER = 1.2f;
	private static final int STAMINA_INCREMENT = 35;
	private static final int FISHED_MIN_AMOUNT = 1;
	private static final int FISHED_MAX_AMOUNT = 4;
	private static final int FISHING_STAMINA_COST = 1;
	
	public FisherArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}

	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getSailingMinLevel() {
		return SAILING_MIN_LEVEL;
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getFishedMaxAmount() {
		return FISHED_MAX_AMOUNT;
	}

	@Override
	public int getFishedMinAmount() {
		return FISHED_MIN_AMOUNT;
	}

	@Override
	public int getFishingStaminaCost() {
		return FISHING_STAMINA_COST;
	}
	
	@Override
	protected float getSailingModifier() {
		return SAILING_MODIFIER;
	}
	
}
