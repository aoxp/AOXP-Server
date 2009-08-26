package ao.domain.spell.effect;

import java.util.Random;

import ao.domain.character.Character;
import ao.domain.character.UserCharacter;
import ao.domain.worldobject.WorldObject;

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
	public boolean appliesTo(Character caster, Character target) {
		if (target instanceof UserCharacter && !target.isDead()) {
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
