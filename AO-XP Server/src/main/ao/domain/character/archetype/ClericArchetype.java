package ao.domain.character.archetype;

/**
 * A cleric archetype.
 * @author Brian Chaia
 */
public class ClericArchetype extends DefaultArchetype {
	
	private static final float MANA_MODIFIER = 2.0f;
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
}
