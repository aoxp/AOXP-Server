package ao.domain.character.archetype;

/**
 * A bard archetype.
 * @author Brian Chaia
 */
public class BardArchetype extends DefaultArchetype {

	private static final float MANA_MODIFIER = 2.0f;
	private static final int HIT_INCREMENT = 2;
	private static final int STAMINA_INCREMENT = 15;
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}
	
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
}
