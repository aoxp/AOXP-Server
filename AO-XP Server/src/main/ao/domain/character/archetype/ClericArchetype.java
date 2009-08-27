package ao.domain.character.archetype;

/**
 * A cleric archetype.
 * @author Brian Chaia
 */
public class ClericArchetype extends DefaultArchetype {

	private static final float MANA_MODIFIER = 2.0f;
	
	public ClericArchetype(float evasionModifier, float meleeAccuracyModifier,
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
}
