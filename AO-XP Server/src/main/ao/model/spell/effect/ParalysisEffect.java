package ao.model.spell.effect;

import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

public class ParalysisEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		target.setParalyzed(true);
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
