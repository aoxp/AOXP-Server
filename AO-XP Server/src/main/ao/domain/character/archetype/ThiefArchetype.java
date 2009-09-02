package ao.domain.character.archetype;

public class ThiefArchetype extends DefaultArchetype {

	private static final int THIEF_HIT_INCREMENT = 1;
	private static final int THIEF_STAMINA_INCREMENT = 18;
	private static final int THIEF_STOLEN_MAX_AMOUNT = 800;
	private static final int THIEF_STOLEN_MIN_AMOUNT = 80;
	
	public ThiefArchetype(float evasionModifier, float meleeAccuracyModifier,
			float rangedAccuracyModifier, float meleeDamageModifier,
			float rangedDamageModifier, float wrestlingDamageModifier,
			float blockPowerModifier) {
		super(evasionModifier, meleeAccuracyModifier, rangedAccuracyModifier,
				meleeDamageModifier, rangedDamageModifier, wrestlingDamageModifier,
				blockPowerModifier);
		// TODO Auto-generated constructor stub
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
		return THIEF_STAMINA_INCREMENT;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return THIEF_HIT_INCREMENT;
	}

	@Override
	public int getStolenMaxAmount() {
		return THIEF_STOLEN_MAX_AMOUNT;
	}

	@Override
	public int getStolenMinAmount() {
		return THIEF_STOLEN_MIN_AMOUNT;
	}

}
