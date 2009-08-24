package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;

public class InvisibilityEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		((UserCharacter) target).setInvisible(true);
	}

	@Override
	public boolean appliesTo(Character target) {
		if (target instanceof UserCharacter && ((UserCharacter) target).isDead()) {
			return false;
		}
		
		return true;
	}
	
}
