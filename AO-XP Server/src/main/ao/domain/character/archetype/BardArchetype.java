package ao.domain.character.archetype;

/**
 * A bard archetype.
 * @author Brian Chaia
 */
public class BardArchetype extends DefaultArchetype {

	private static final float MANA_MODIFIER = 2.0f;
	
	@Override
	public int getManaIncrement(int intelligence, int mana) {
		return (int) Math.round(intelligence * MANA_MODIFIER);
	}
}
