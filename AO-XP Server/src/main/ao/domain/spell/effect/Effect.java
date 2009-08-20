package ao.domain.spell.effect;

import ao.domain.character.Character;

public interface Effect {

	/**
	 * Applies the effect in the given character
	 * @param character The character to apply the effect.
	 */
	void apply(Character character);

}
