package ao.model.character.behavior;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.spell.Spell;
import ao.model.worldobject.WorldObject;

/**
 * Listens for actions from a Behavior and carries them on.
 */
public interface BehaviorActionListener {

	/**
	 * Performs an attack on the given character.
	 * @param target The target to attack.
	 */
	void attack(Character target);
	
	/**
	 * Casts a spell on the given character.
	 * @param spell The spell to cast.
	 * @param target The character on which to cast the spell.
	 */
	void cast(Spell spell, Character target);
	
	/**
	 * Casts a spell on the given world object.
	 * @param spell The spell to cast.
	 * @param object The object on which to cast the spell.
	 */
	void cast(Spell spell, WorldObject object);
	
	/**
	 * Moves the character on the given direction.
	 * @param heading The heading in which to move.
	 */
	void moveTo(Heading heading);
}
