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
