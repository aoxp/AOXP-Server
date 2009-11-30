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
 * A lumberjack archetype.
 */
public class LumberjackArchetype extends DefaultArchetype {

	private static final int STAMINA_INCREMENT = 35;
	private static final int LUMBER_MIN_AMOUNT = 1;
	private static final int LUMBER_MAX_AMOUNT = 4;
	private static final int LUMBER_STAMINA_COST = 2;
	
	public LumberjackArchetype(float evasionModifier, float meleeAccuracyModifier,
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
	public int getLumberedMaxAmount() {
		return LUMBER_MAX_AMOUNT;
	}

	@Override
	public int getLumberedMinAmount() {
		return LUMBER_MIN_AMOUNT;
	}
	
	@Override
	public int getLumberjackingStaminaCost(){
		return LUMBER_STAMINA_COST;
	}
	
}
