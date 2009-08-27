package ao.domain.character.archetype;

/**
 * A pirate archetype.
 * @author jsotuyod
 */
public class PirateArchetype extends DefaultArchetype {

	PirateArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
	}

	private static final int HIT_INCREMENT = 3;
	private static final int SAILING_MIN_LEVEL = 20;
	private static final float SAILING_MODIFIER = 1.0f;

	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getSailingMinLevel() {
		return SAILING_MIN_LEVEL;
	}
	
	/**
	 * Retrieves the sailing modifier for the archetype.
	 * @return The sailing modifier for the archetype.
	 */
	protected float getSailingModifier() {
		return SAILING_MODIFIER;
	}
}
