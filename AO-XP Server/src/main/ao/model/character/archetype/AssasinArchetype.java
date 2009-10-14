package ao.model.character.archetype;

/**
 * An assasin archetype.
 */
public class AssasinArchetype extends DefaultArchetype {
	
	private static final int LEVEL_MAX_HIT_INCREMENT = 35;
	private static final int MAX_HIT_INCREMENT = 3;
	private static final int MIN_HIT_INCREMENT = 1;
	private static final float STABBING_DAMAGE_MODIFIER = 1.4f;
	private static final int INITIAL_MANA = 50;
	
	public AssasinArchetype(float evasionModifier,
			float meleeAccuracyModifier, float rangedAccuracyModifier,
			float meleeDamageModifier, float rangedDamageModifier,
			float wrestlingDamageModifier, float blockPowerModifier) {
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
	public boolean canStab(int stabbingSkill) {
		return true;
	}
	
	@Override
	public float getStabbingDamageModifier() {
		return STABBING_DAMAGE_MODIFIER;
	}
	
	@Override
	public int getInitialMana(int intelligence){
		return INITIAL_MANA;
	}
	
}
