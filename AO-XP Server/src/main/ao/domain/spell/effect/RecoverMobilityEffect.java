package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.worldobject.WorldObject;

public class RecoverMobilityEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		target.setImmobilized(false);
		target.setParalyzed(false);
	}
	
	@Override
	public boolean appliesTo(Character caster, Character target) {
		// TODO: Considerar casos de remo a npc cuando esté armado el sistema de mascotas.
		if (!target.isImmobilized() && !target.isParalyzed()) {
			return false;
		}
		
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
