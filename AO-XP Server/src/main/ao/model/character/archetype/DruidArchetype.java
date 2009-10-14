/**
 * 
 */
package ao.model.character.archetype;

/**
 * A druid archetype.
 */
public class DruidArchetype extends DefaultArchetype {

	private static final float MANA_MODIFIER = 2.0f;
	private static final float TAMING_TRAINING_CHANCE = 1.0f;
	private static final int INITIAL_MANA = 50;
	
	public DruidArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return Math.round(intelligence * MANA_MODIFIER);
	}
	
	@Override
	public float getTamingTrainingChance() {
		return TAMING_TRAINING_CHANCE;
	}
	
	@Override
	public int getInitialMana(int intelligence){
		return INITIAL_MANA;
	}
	
}
