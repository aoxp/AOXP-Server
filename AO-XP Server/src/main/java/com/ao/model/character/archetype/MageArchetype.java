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

import com.ao.model.spell.Spell;
import com.ao.model.worldobject.EquipableItem;
import com.ao.model.worldobject.Staff;
import com.ao.model.worldobject.Weapon;

/**
 * A mage archetype.
 */
public class MageArchetype extends DefaultArchetype {
	
	private static final float MANA_MODIFIER = 2.8f;
	private static final int HIT_INCREMENT = 1;
	private static final int STAMINA_INCREMENT = 14;
	private static final int INITIAL_MANA_MODIFIER = 3;

	public MageArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public boolean canCast(Spell spell, Weapon weapon, EquipableItem ring) {
		return (spell.requiresStaff() && weapon instanceof Staff && spell.getRequiredStaffPower() < ((Staff) weapon).getMagicPower());
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
	public int getManaIncrement(int intelligence, int mana) {
		return Math.round(intelligence * MANA_MODIFIER);
	}
	
	@Override
	public int getInitialMana(int intelligence){
		return intelligence * INITIAL_MANA_MODIFIER;
	}
	
}
