package ao.domain.character.archetype;

public class CarpenterArchetype extends DefaultArchetype {

	private static final float CARPENTER_WOOD_WORKING_MODIFIER = 1.0f;
	private static final int CARPENTER_WOOD_WORK_STAMINA_COST = 2;
	
	public CarpenterArchetype(float evasionModifier,
			float meleeAccuracyModifier, float rangedAccuracyModifier,
			float meleeDamageModifier, float rangedDamageModifier,
			float wrestlingDamageModifier, float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public int getWoodWorkStaminaCost() {
		return CARPENTER_WOOD_WORK_STAMINA_COST;
	}
	
	/**
	 * Retrieves the wood working modifier for the archetype.
	 * @return The wood working modifier for the archetype.
	 */
	protected float getWoodWorkingModifier() {
		return CARPENTER_WOOD_WORKING_MODIFIER;
	}

}
