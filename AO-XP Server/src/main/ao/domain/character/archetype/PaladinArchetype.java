package ao.domain.character.archetype;

/**
 * A paladin archetype.
 */
public class PaladinArchetype extends DefaultArchetype {

	private static final int LEVEL_MAX_HIT_INCREMENT = 35;
	private static final int MAX_HIT_INCREMENT = 3;
	private static final int MIN_HIT_INCREMENT = 1;
	
	public PaladinArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}

	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return intelligence;
	}

	@Override
	public int getHitIncrement(int level) {
		if (level > LEVEL_MAX_HIT_INCREMENT){
			return MIN_HIT_INCREMENT;
		}
		return MAX_HIT_INCREMENT;
	}
	
	@Override
	public int getInitialMana(int intelligence) {
		return intelligence;
	}
	
}
