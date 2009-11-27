package ao.model.spell.effect;

import ao.model.character.Character;
import ao.model.character.UserCharacter;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.WorldObject;

/**
 * An effect to mimetise characters.
 */
public class MimetismEffect implements Effect {

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public boolean appliesTo(Character caster, Character target) {
		// Only druids (or maybe npcs, not yet defined) can mimetize themselves with NPC's
		if (!(target instanceof UserCharacter) 
				&& (caster instanceof UserCharacter)
				&& ((UserCharacter)caster).getArchetype() != UserArchetype.DRUID.getArchetype()) {
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
		// Only druids (or maybe npcs, not yet defined) can mimetize themselves with world objects
		if (caster instanceof UserCharacter && ((UserCharacter)caster).getArchetype() != UserArchetype.DRUID.getArchetype()) {
			return false;
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public void apply(Character caster, Character target) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.worldobject.WorldObject)
	 */
	@Override
	public void apply(Character caster, WorldObject target) {
		// TODO Auto-generated method stub

	}

}
