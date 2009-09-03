package ao.model.character.archetype;

/**
 * A bard archetype.
 */
public class BardArchetype extends DefaultArchetype {

	private static final float MANA_MODIFIER = 2.0f;
	private static final int BARD_INITIAL_MANA = 50;
	
	public BardArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
	
	@Override
	public int getInitialMana(int intelligence){
		return BARD_INITIAL_MANA;
	}
}
