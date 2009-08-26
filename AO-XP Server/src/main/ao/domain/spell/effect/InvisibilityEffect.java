package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.worldobject.WorldObject;

public class InvisibilityEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		target.setInvisible(true);
	}


	@Override
	public boolean appliesTo(Character caster, Character target) {
		if (target.isDead()) {
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
