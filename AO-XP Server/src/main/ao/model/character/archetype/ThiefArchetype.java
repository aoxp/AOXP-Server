package ao.model.character.archetype;

/**
 * A thief archetype.
 */
public class ThiefArchetype extends DefaultArchetype {

	private static final int HIT_INCREMENT = 1;
	private static final int STAMINA_INCREMENT = 18;
	private static final int STOLEN_MAX_AMOUNT = 800;
	private static final int STOLEN_MIN_AMOUNT = 80;
	
	public ThiefArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
	}
	
	@Override
	public boolean canDisarm() {
		return true;
	}

	@Override
	public boolean canImmobilize() {
		return true;
	}
	
	@Override
	public boolean canWalkHidden() {
		return true;
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getStolenMaxAmount() {
		return STOLEN_MAX_AMOUNT;
	}

	@Override
	public int getStolenMinAmount() {
		return STOLEN_MIN_AMOUNT;
	}

}
