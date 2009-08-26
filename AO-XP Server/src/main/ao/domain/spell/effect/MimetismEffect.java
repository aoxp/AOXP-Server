package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;
import ao.domain.character.archetype.UserArchetype;
import ao.domain.worldobject.WorldObject;

public class MimetismEffect implements Effect {

	@Override
	public boolean appliesTo(Character caster, Character target) {
		if (((UserCharacter)caster).getArchetype() != UserArchetype.DRUID.getArchetype() && !(target instanceof UserCharacter)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean appliesTo(Character caster, WorldObject worldobject) {
		if (((UserCharacter)caster).getArchetype() != UserArchetype.DRUID.getArchetype()) {
			return false;
		}
		return true;
	}

	@Override
	public void apply(Character caster, Character target) {
		// TODO Auto-generated method stub

	}

	@Override
	public void apply(Character caster, WorldObject target) {
		// TODO Auto-generated method stub

	}

}
