package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;

public class RecoverMobilityEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		target.setImmobilized(false);
		target.setParalyzed(false);
	}

	@Override
	public boolean appliesTo(Character target) {
		// TODO: Considerar casos de remo a npc cuando esté armado el sistema de mascotas.
		if (!(target.isImmobilized() || target.isParalyzed())) {
			return false;
		}
		
		if (target instanceof UserCharacter && ((UserCharacter) target).isDead()) {
			return false;
		}
		
		return true;
	}

}
