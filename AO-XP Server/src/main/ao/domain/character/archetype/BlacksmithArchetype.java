package ao.domain.character.archetype;

public class BlacksmithArchetype extends DefaultArchetype {
	
	private static final float BLACKSMITH_BLACKSMITH_MODIFIER = 1.0f;
	private static final float BLACKSMITH_IRON_WORKING_MODIFIER = 1.2f;
	private static final int BLACKSMITH_BLACKSMITH_STAMINA_COST = 2;
	
	public BlacksmithArchetype(float evasionModifier,
			float meleeAccuracyModifier, float rangedAccuracyModifier,
			float meleeDamageModifier, float rangedDamageModifier,
			float wrestlingDamageModifier, float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getBlacksmithingStaminaCost() {
		return BLACKSMITH_BLACKSMITH_STAMINA_COST;
	}
	
	/**
	 * Retrieves the iron working modifier for the archetype.
	 * @return The iron working modifier for the archetype.
	 */
	protected float getIronWorkingModifier() {
		return BLACKSMITH_IRON_WORKING_MODIFIER;
	}
	
	/**
	 * Retrieves the blacksmith modifier for the archetype.
	 * @return The blacksmith modifier for the archetype.
	 */
	protected float getBlacksmithModifier() {
		return BLACKSMITH_BLACKSMITH_MODIFIER;
	}
	
}
