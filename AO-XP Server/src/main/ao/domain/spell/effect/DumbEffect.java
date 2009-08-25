package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;

public class DumbEffect implements Effect {

	@Override
	public boolean appliesTo(Character target) {
		if (target instanceof UserCharacter && ((UserCharacter) target).isDead()) {
			return false;
		}
		
		return true;
	}

	@Override
	public void apply(Character caster, Character target) {
		((UserCharacter) target).setDumb(true);
	}

}
