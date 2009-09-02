package ao.domain.character.archetype;

public class AssasinArchetype extends DefaultArchetype {
	
	private static final int LEVEL_MAX_HIT_INCREMENT = 35;
	private static final int MAX_HIT_INCREMENT = 3;
	private static final int MIN_HIT_INCREMENT = 1;
	private static final float ASSASIN_STABBING_DAMAGE_MODIFIER = 1.4f;
	private static final int ASSASIN_INITIAL_MANA = 50;
	
	public AssasinArchetype(float evasionModifier,
			float meleeAccuracyModifier, float rangedAccuracyModifier,
			float meleeDamageModifier, float rangedDamageModifier,
			float wrestlingDamageModifier, float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
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
		return ASSASIN_STABBING_DAMAGE_MODIFIER;
	}
	
	public int getInitialMana(int intelligence){
		return ASSASIN_INITIAL_MANA;
	}
	
}
