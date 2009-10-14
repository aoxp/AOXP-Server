package ao.model.character.archetype;

/**
 * A blacksmith archetype.
 */
public class BlacksmithArchetype extends DefaultArchetype {
	
	private static final float BLACKSMITH_MODIFIER = 1.0f;
	private static final float IRON_WORKING_MODIFIER = 1.2f;
	private static final int BLACKSMITH_STAMINA_COST = 2;
	
	public BlacksmithArchetype(float evasionModifier,
			float meleeAccuracyModifier, float rangedAccuracyModifier,
			float meleeDamageModifier, float rangedDamageModifier,
			float wrestlingDamageModifier, float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}

	@Override
	public int getBlacksmithingStaminaCost() {
		return BLACKSMITH_STAMINA_COST;
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
