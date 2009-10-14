package ao.model.character.archetype;

/**
 * A warrior archetype.
 */
public class WarriorArchetype extends DefaultArchetype {

	private static final int LEVEL_MAX_HIT_INCREMENT = 35;
	private static final int MAX_HIT_INCREMENT = 3;
	private static final int MIN_HIT_INCREMENT = 2;
	
	public WarriorArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}

	@Override
	public int getHitIncrement(int level) {
		if (level > LEVEL_MAX_HIT_INCREMENT){
			return MIN_HIT_INCREMENT;
		}
		
		return MAX_HIT_INCREMENT;
	}
	
}
