package ao.model.spell.effect;

import ao.model.character.Character;
import ao.model.character.UserCharacter;
import ao.model.worldobject.WorldObject;

public class DumbEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		target.setDumb(true);
	}

	@Override
	public boolean appliesTo(Character caster, Character target) {
		if (!(target instanceof UserCharacter) || target.isDead()) {
			return false;
		}
		
		return true;
	}

	@Override
	public boolean appliesTo(Character caster, WorldObject worldobject) {
		return false;
	}

	@Override
	public void apply(Character caster, WorldObject target) {
		
	}

}
