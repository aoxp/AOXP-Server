package ao.model.spell.effect;

import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

/**
 * An effects that recovers a character's mobility (counters palaysis and immobility).
 */
public class RecoverMobilityEffect implements Effect {

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public void apply(Character caster, Character target) {
		target.setImmobilized(false);
		target.setParalyzed(false);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.character.Character)
	 */
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
