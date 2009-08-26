package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.worldobject.WorldObject;

public interface Effect {

	/**
	 * Applies the effect in the given character.
	 * @param caster The character casting the spell with the effect.
	 * @param target The character where to apply the effect.
	 */
	void apply(Character caster, Character target);
	
	/**
	 * Applies the effect in the given object
	 * @param caster The character casting the spell with the effect
	 * @param target The world object on which to apply the effect.
	 */
	void apply(Character caster, WorldObject target);

	/**
	 * Checks whether the effect can be applied to the given character, or not.
	 * @param caster The caster character.
	 * @param target The target character.
	 * @return True if the effect can be applied, false otherwise.
	 */
	boolean appliesTo(Character caster, Character target);
	
	/**
	 * Checks whether the effect can be applied to the given world object, or not.
	 * @param caster The caster of the spell.
	 * @param worldobject The target object.
	 * @return True if the effect can be applied, false otherwise.
	 */
	boolean appliesTo(Character caster, WorldObject worldobject);
}
