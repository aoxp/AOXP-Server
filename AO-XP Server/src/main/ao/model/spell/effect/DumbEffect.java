package ao.model.spell.effect;

import ao.model.character.Character;
import ao.model.character.UserCharacter;
import ao.model.worldobject.WorldObject;

/**
 * A dumb effect.
 */
public class DumbEffect implements Effect {

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public void apply(Character caster, Character target) {
		target.setDumb(true);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public boolean appliesTo(Character caster, Character target) {
		if (!(target instanceof UserCharacter) || target.isDead()) {
			return false;
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.worldobject.WorldObject)
	 */
	@Override
	public boolean appliesTo(Character caster, WorldObject worldobject) {
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.worldobject.WorldObject)
	 */
	@Override
	public void apply(Character caster, WorldObject target) {
		
	}

}
