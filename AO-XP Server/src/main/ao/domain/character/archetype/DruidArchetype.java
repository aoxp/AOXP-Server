/**
 * 
 */
package ao.domain.character.archetype;


/**
 * A druid archetype.
 * @author Marco
 */
public class DruidArchetype extends DefaultArchetype {
	
	private static final float MANA_MODIFIER = 2.0f;
	private static final int HIT_INCREMENT = 2;
	private static final int STAMINA_INCREMENT = 15;
	private static final float TAMING_TRAINING_CHANCE = 1.0f;
	@Override
	public int getStaminaIncrement() {
		return STAMINA_INCREMENT;
	}
	
	@Override
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
	
	@Override
	public float getTamingTrainingChance() {
		return TAMING_TRAINING_CHANCE;
	}
}
