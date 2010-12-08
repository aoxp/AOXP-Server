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
import com.ao.model.worldobject.Boat;
import com.ao.model.worldobject.EquipableItem;
import com.ao.model.worldobject.Item;
import com.ao.model.worldobject.Weapon;

/**
 * Implements Archetype setting default values.
 */
public abstract class DefaultArchetype implements Archetype {

	private static final float SAILING_MODIFIER = 2.3f;
	private static final float STABBING_DAMAGE_MODIFIER = 1.5f;
	private static final int STAMINA_INCREMENT = 15;
	private static final float TAMING_TRAINING_CHANCE = 0.3f;
	private static final int WOOD_WORK_STAMINA_COST = 6;
	private static final float BLACKSMITH_MODIFIER = 4.0f;
	private static final float IRON_WORKING_MODIFIER = 3.0f;
	private static final int MIN_STABBING_SKILL = 10;
	private static final float WOOD_WORKING_MODIFIER = 3.0f;
	private static final int BLACKSMITH_STAMINA_COST = 6;
	private static final int FISHING_STAMINA_COST = 3;
	private static final int FISHED_MAX_AMOUNT = 1;
	private static final int FISHED_MIN_AMOUNT = 1;
	private static final int LUMBERED_MAX_AMOUNT = 1;
	private static final int LUMBERED_MIN_AMOUNT = 1;
	private static final int LUMBERJACKING_STAMINA_COST = 4;
	private static final int MINED_MAX_AMOUNT = 1;
	private static final int MINED_MIN_AMOUNT = 1;
	private static final int MINING_STAMINA_COST = 5;
	private static final int STOLEN_MAX_AMOUNT = 100;
	private static final int STOLEN_MIN_AMOUNT = 1;
	private static final int SAILING_MIN_LEVEL = 25;
	private static final float MIN_STABBING_CHANCE = 0.0439f;
	private static final float STABBING_CHANCE_SKILL_MULTIPLIER = 0.000361f;
	private static final int MANA_INCREMENT = 0;
	private static final int HIT_INCREMENT = 2;
	private static final int INITIAL_MANA = 0;
	
	protected float evasionModifier;
	protected float meleeAccuracyModifier;
	protected float rangedAccuracyModifier;
	protected float meleeDamageModifier;
	protected float rangedDamageModifier;
	protected float wrestlingDamageModifier;
	protected float blockPowerModifier;
	
	/**
	 * Creates a new default archetype.
	 * @param evasionModifier
	 * @param meleeAccuracyModifier
	 * @param rangedAccuracyModifier
	 * @param meleeDamageModifier
	 * @param rangedDamageModifier
	 * @param wrestlingDamageModifier
	 * @param blockPowerModifier
	 */
	public DefaultArchetype(float evasionModifier, float meleeAccuracyModifier, float rangedAccuracyModifier, 
			float meleeDamageModifier, float rangedDamageModifier, float wrestlingDamageModifier, 
			float blockPowerModifier){
		this.evasionModifier = evasionModifier;
		this.meleeAccuracyModifier = meleeAccuracyModifier;
		this.rangedAccuracyModifier = rangedAccuracyModifier;
		this.meleeDamageModifier = meleeDamageModifier;
		this.rangedDamageModifier = rangedDamageModifier;
		this.wrestlingDamageModifier = wrestlingDamageModifier;
		this.blockPowerModifier = blockPowerModifier;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}
	
	@Override
	public boolean canBlacksmith(int blacksmithSkill, Item item) {
		return blacksmithSkill / getBlacksmithModifier() >= item.getManufactureDifficulty();
	}

	@Override
	public boolean canCast(Spell spell, Weapon weapon, EquipableItem ring) {
		return true;
	}

	@Override
	public boolean canCriticalBlow() {
		return false;
	}

	@Override
	public boolean canDisarm() {
		return false;
	}

	@Override
	public boolean canImmobilize() {
		return false;
	}

	@Override
	public boolean canIronWork(int ironWorkingSkill, Item item) {
		return ironWorkingSkill / getIronWorkingModifier() >= item.getManufactureDifficulty();
	}

	@Override
	public boolean canPickPocket() {
		return false;
	}

	@Override
	public boolean canSail(int sailingSkill, Boat boat) {
		return sailingSkill / getSailingModifier() >= boat.getUsageDifficulty();
	}

	@Override
	public boolean canStab(int stabbingSkill) {
		return stabbingSkill >= MIN_STABBING_SKILL;
	}

	@Override
	public boolean canWalkHidden() {
		return false;
	}

	@Override
	public boolean canWoodWork(int woodWorkSkill, Item item) {
		return woodWorkSkill / getWoodWorkingModifier() >= item.getManufactureDifficulty();
	}

	@Override
	public int getBlacksmithingStaminaCost() {
		return BLACKSMITH_STAMINA_COST;
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
	public int getLumberedMaxAmount() {
		return LUMBERED_MAX_AMOUNT;
	}

	@Override
	public int getLumberedMinAmount() {
		return LUMBERED_MIN_AMOUNT;
	}

	@Override
	public int getLumberjackingStaminaCost() {
		return LUMBERJACKING_STAMINA_COST;
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
	public int getSailingMinLevel() {
		return SAILING_MIN_LEVEL;
	}

	@Override
	public float getStabbingChance(int stabSkill) {
		return STABBING_CHANCE_SKILL_MULTIPLIER * stabSkill + MIN_STABBING_CHANCE;
	}

	@Override
	public float getStabbingDamageModifier() {
		return STABBING_DAMAGE_MODIFIER;
	}

	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return MANA_INCREMENT;
	}

	@Override
	public int getStolenMaxAmount() {
		return STOLEN_MAX_AMOUNT;
	}

	@Override
	public int getStolenMinAmount() {
		return STOLEN_MIN_AMOUNT;
	}

	@Override
	public float getTamingTrainingChance() {
		return TAMING_TRAINING_CHANCE;
	}

	@Override
	public int getWoodWorkStaminaCost() {
		return WOOD_WORK_STAMINA_COST;
	}
	
	@Override
	public float getEvasionModifier() {
		return evasionModifier;
	}

	@Override
	public float getMeleeAccuracyModifier() {
		return meleeAccuracyModifier;
	}

	@Override
	public float getRangedAccuracyModifier() {
		return rangedAccuracyModifier;
	}

	@Override
	public float getMeleeDamageModifier() {
		return meleeDamageModifier;
	}

	@Override
	public float getRangedDamageModifier() {
		return rangedDamageModifier;
	}

	@Override
	public float getWrestlingDamageModifier() {
		return wrestlingDamageModifier;
	}

	@Override
	public float getBlockPowerModifier() {
		return blockPowerModifier;
	}

	@Override
	public int getInitialMana(int intelligence){
		return INITIAL_MANA;
	}
	
	/**
	 * Retrieves the sailing modifier for the archetype.
	 * @return The sailing modifier for the archetype.
	 */
	protected float getSailingModifier() {
		return SAILING_MODIFIER;
	}
	
	/**
	 * Retrieves the blacksmith modifier for the archetype.
	 * @return The blacksmith modifier for the archetype.
	 */
	protected float getBlacksmithModifier() {
		return BLACKSMITH_MODIFIER;
	}
	
	/**
	 * Retrieves the iron working modifier for the archetype.
	 * @return The iron working modifier for the archetype.
	 */
	protected float getIronWorkingModifier() {
		return IRON_WORKING_MODIFIER;
	}
	
	/**
	 * Retrieves the wood working modifier for the archetype.
	 * @return The wood working modifier for the archetype.
	 */
	protected float getWoodWorkingModifier() {
		return WOOD_WORKING_MODIFIER;
	}
	
}
