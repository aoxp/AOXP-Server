package ao.domain.spell.effect;

import java.util.Random;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;

public class HitPointsEffect implements Effect {

	private int minPoints;
	private int maxPoints;
	private static final Random randomGenerator = new Random();
	
 	public HitPointsEffect(int minPoints, int maxPoints) {
		this.minPoints = minPoints;
		this.maxPoints = maxPoints;
	}

	@Override
	public void apply(Character caster, Character target) {
		int points = minPoints + randomGenerator.nextInt(maxPoints);
		points += points / (3 * caster.getLevel()) * 100;
	    
		target.addToHitPoints(points);
	}

	@Override
	public boolean appliesTo(Character target) {
		if (target instanceof UserCharacter && ((UserCharacter) target).isDead()) {
			return false;
		}
		
		return true;
	}

}
