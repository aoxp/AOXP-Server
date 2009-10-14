package ao.model.character.archetype;

/**
 * A bandit archetype.
 */
public class BanditArchetype extends DefaultArchetype {
	
	private static final int LEVEL_MAX_HIT_INCREMENT = 35;
	private static final int MAX_HIT_INCREMENT = 3;
	private static final int MIN_HIT_INCREMENT = 1;
	private static final int STAMINA_INCREMENT = 35;
	private static final int INITIAL_MANA = 150;
	private static final int MANA_INCREMENT_MODIFIER = 10;
	private static final int MIN_MANA_INCREMENT = 4;
	
	public BanditArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		if (mana >= 300){
			return 0; 
		}
		
		int rest = intelligence - MANA_INCREMENT_MODIFIER;
		
		return rest < MIN_MANA_INCREMENT ? MIN_MANA_INCREMENT : rest;
	}

	@Override
	public int getHitIncrement(int level) {
		if (level > LEVEL_MAX_HIT_INCREMENT){
			return MIN_HIT_INCREMENT;
		}
		
		return MAX_HIT_INCREMENT;
	}

	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public boolean canCriticalBlow() {
		return true;
	}
	
	@Override
	public int getInitialMana(int intelligence){
		return INITIAL_MANA;
	}
	
}
