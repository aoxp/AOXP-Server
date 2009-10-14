package ao.model.character.archetype;

/**
 * A carpenter archetype.
 */
public class CarpenterArchetype extends DefaultArchetype {

	private static final float WOOD_WORKING_MODIFIER = 1.0f;
	private static final int WOOD_WORK_STAMINA_COST = 2;
	
	public CarpenterArchetype(float evasionModifier,
			float meleeAccuracyModifier, float rangedAccuracyModifier,
			float meleeDamageModifier, float rangedDamageModifier,
			float wrestlingDamageModifier, float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getWoodWorkStaminaCost() {
		return WOOD_WORK_STAMINA_COST;
	}
	
	@Override
	protected float getWoodWorkingModifier() {
		return WOOD_WORKING_MODIFIER;
	}

}
