package ao.domain.spell.effect;

import ao.domain.character.Character;

public interface Effect {

	/**
	 * Applies the effect in the given character.
	 * @param caster The character casting the spell with the effect.
	 * @param target The character where to apply the effect.
	 */
	void apply(Character caster, Character target);

	/**
	 * Checks whether the effect can be applied to the given character, or not.
	 * @param target The target character.
	 * @return True if the effect can be applied, false otherwise.
	 */
	boolean appliesTo(Character target);
}
