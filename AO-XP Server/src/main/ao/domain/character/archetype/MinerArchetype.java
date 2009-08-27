package ao.domain.character.archetype;

/**
 * A miner archetype.
 * @author Brian Chaia
 */
public class MinerArchetype extends DefaultArchetype {

	private static final int STAMINA_INCREMENT = 40;
	private static final int MINED_MAX_AMOUNT = 6;
	private static final int MINED_MIN_AMOUNT = 1;
	private static final int MINING_STAMINA_COST = 2;
	
	public MinerArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getMiningMaxAmount() {
		return MINED_MAX_AMOUNT;
	}

	@Override
	public int getMiningMinAmount() {
		return MINED_MIN_AMOUNT;
	}

	@Override
	public int getMiningStaminaCost() {
		return MINING_STAMINA_COST;
	}
	
}
