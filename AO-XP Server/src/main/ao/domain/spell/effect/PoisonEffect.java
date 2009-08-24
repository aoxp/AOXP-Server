package ao.domain.spell.effect;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;

public class PoisonEffect implements Effect {

	@Override
	public void apply(Character caster, Character target) {
		target.setPoisoned(true);
	}

	@Override
	public boolean appliesTo(Character target) {
		if (target instanceof UserCharacter && ((UserCharacter) target).isDead()) {
			return false;
		}
		
		return true;
	}

}
