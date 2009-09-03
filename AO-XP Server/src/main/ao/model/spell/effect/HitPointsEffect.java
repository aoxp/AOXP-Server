package ao.model.spell.effect;

import java.util.Random;

import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

public class HitPointsEffect implements Effect {

	private int minPoints;
	private int deltaPoints;
	private static final Random randomGenerator = new Random();
	
 	public HitPointsEffect(int minPoints, int maxPoints) {
		this.minPoints = minPoints;
		this.deltaPoints = maxPoints - minPoints;
	}

	@Override
	public void apply(Character caster, Character target) {
		int points = minPoints + randomGenerator.nextInt(deltaPoints);
		points += (int) Math.round(points * 0.03f * caster.getLevel());
	    
		target.addToHitPoints(points);
	}

	@Override
	public boolean appliesTo(Character caster, Character target) {
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
