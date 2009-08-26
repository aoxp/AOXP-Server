package ao.domain.character.archetype;

import ao.domain.spell.Spell;
import ao.domain.worldobject.Boat;
import ao.domain.worldobject.EquipableItem;
import ao.domain.worldobject.Item;
import ao.domain.worldobject.Weapon;

/**
 * Implements Archetype setting default values.
 * @author jsotuyod
 */
public abstract class DefaultArchetype implements Archetype {

	private static final float DEFAULT_SAILING_MODIFIER = 2.3f;
	private static final float DEFAULT_STABBING_DAMAGE_MODIFIER = 1.5f;
	private static final int DEFAULT_STAMINA_INCREMENT = 15;
	private static final float DEFAULT_TAMING_TRAINING_CHANCE = 1.3f;
	private static final int DEFAULT_WOOD_WORK_STAMINA_COST = 6;
	private static final float DEFAULT_BLACKSMITH_MODIFIER = 4.0f;
	private static final float DEFAULT_IRON_WORKING_MODIFIER = 3.0f;
	private static final int DEFAULT_MIN_STABBING_SKILL = 10;
	private static final float DEFAULT_WOOD_WORKING_MODIFIER = 3.0f;
	private static final int DEFAULT_BLACKSMITH_STAMINA_COST = 6;
	private static final int DEFAULT_FISHING_STAMINA_COST = 3;
	private static final int DEFAULT_FISHED_MAX_AMOUNT = 1;
	private static final int DEFAULT_FISHED_MIN_AMOUNT = 1;
	private static final int DEFAULT_LUMBERED_MAX_AMOUNT = 1;
	private static final int DEFAULT_LUMBERED_MIN_AMOUNT = 1;
	private static final int DEFAULT_LUMBERJACKING_STAMINA_COST = 4;
	private static final int DEFAULT_MINED_MAX_AMOUNT = 1;
	private static final int DEFAULT_MINED_MIN_AMOUNT = 1;
	private static final int DEFAULT_MINING_STAMINA_COST = 5;
	private static final int DEFAULT_STOLEN_MAX_AMOUNT = 100;
	private static final int DEFAULT_STOLEN_MIN_AMOUNT = 1;
	private static final int DEFAULT_SAILING_MIN_LEVEL = 25;
	private static final float DEFAULT_MIN_STABBING_CHANCE = 0.0439f;
	private static final float DEFAULT_STABBING_CHANCE_SKILL_MULTIPLIER = 0.000361f;
	private static final int DEFAULT_MANA_INCREMENT = 0;
	private static final int DEFAULT_HIT_INCREMENT = 2;

	@Override
	public int getHitIncrement(int level) {
		return DEFAULT_HIT_INCREMENT;
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
		return stabbingSkill >= DEFAULT_MIN_STABBING_SKILL;
	}

	@Override
	public boolean canWalkHidden() {
		return false;
	}

	@Override
	public boolean canWoodWork(int woodWorkSkill, Item item) {
		return woodWorkSkill / getWoodWorkingModifier() >= item.getUsageDifficulty();
	}

	@Override
	public int getBlacksmithingStaminaCost() {
		return DEFAULT_BLACKSMITH_STAMINA_COST;
	}

	@Override
	public int getFishedMaxAmount() {
		return DEFAULT_FISHED_MAX_AMOUNT;
	}

	@Override
	public int getFishedMinAmount() {
		return DEFAULT_FISHED_MIN_AMOUNT;
	}

	@Override
	public int getFishingStaminaCost() {
		return DEFAULT_FISHING_STAMINA_COST;
	}

	@Override
	public int getLumberedMaxAmount() {
		return DEFAULT_LUMBERED_MAX_AMOUNT;
	}

	@Override
	public int getLumberedMinAmount() {
		return DEFAULT_LUMBERED_MIN_AMOUNT;
	}

	@Override
	public int getLumberjackingStaminaCost() {
		return DEFAULT_LUMBERJACKING_STAMINA_COST;
	}

	@Override
	public int getMiningMaxAmount() {
		return DEFAULT_MINED_MAX_AMOUNT;
	}

	@Override
	public int getMiningMinAmount() {
		return DEFAULT_MINED_MIN_AMOUNT;
	}

	@Override
	public int getMiningStaminaCost() {
		return DEFAULT_MINING_STAMINA_COST;
	}

	@Override
	public int getSailingMinLevel() {
		return DEFAULT_SAILING_MIN_LEVEL;
	}

	@Override
	public float getStabbingChance(int stabSkill) {
		return DEFAULT_STABBING_CHANCE_SKILL_MULTIPLIER * stabSkill + DEFAULT_MIN_STABBING_CHANCE;
	}

	@Override
	public float getStabbingDamageModifier() {
		return DEFAULT_STABBING_DAMAGE_MODIFIER;
	}

	@Override
	public int getStaminaIncrement() {
		return DEFAULT_STAMINA_INCREMENT;
	}
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return DEFAULT_MANA_INCREMENT;
	}

	@Override
	public int getStolenMaxAmount() {
		return DEFAULT_STOLEN_MAX_AMOUNT;
	}

	@Override
	public int getStolenMinAmount() {
		return DEFAULT_STOLEN_MIN_AMOUNT;
	}

	@Override
	public float getTamingTrainingChance() {
		return DEFAULT_TAMING_TRAINING_CHANCE;
	}

	@Override
	public int getWoodWorkStaminaCost() {
		return DEFAULT_WOOD_WORK_STAMINA_COST;
	}

	/**
	 * Retrieves the sailing modifier for the archetype.
	 * @return The sailing modifier for the archetype.
	 */
	protected float getSailingModifier() {
		return DEFAULT_SAILING_MODIFIER;
	}
	
	/**
	 * Retrieves the blacksmith modifier for the archetype.
	 * @return The blacksmith modifier for the archetype.
	 */
	protected float getBlacksmithModifier() {
		return DEFAULT_BLACKSMITH_MODIFIER;
	}
	
	/**
	 * Retrieves the iron working modifier for the archetype.
	 * @return The iron working modifier for the archetype.
	 */
	protected float getIronWorkingModifier() {
		return DEFAULT_IRON_WORKING_MODIFIER;
	}
	
	/**
	 * Retrieves the wood working modifier for the archetype.
	 * @return The wood working modifier for the archetype.
	 */
	protected float getWoodWorkingModifier() {
		return DEFAULT_WOOD_WORKING_MODIFIER;
	}
}
