package ao.model.spell.effect;

import java.util.Random;

import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

/**
 * An effect that alters HP (either increases or decreases)
 */
public class HitPointsEffect implements Effect {

	private int minPoints;
	private int deltaPoints;
	private static final Random randomGenerator = new Random();
	
	/**
	 * Creates a new HitPointsEffect instance.
	 * @param minPoints The minimum points to be added / substracted (if negative)
	 * @param maxPoints The maximum points to be added / substracted (if negative)
	 */
 	public HitPointsEffect(int minPoints, int maxPoints) {
		this.minPoints = minPoints;
		this.deltaPoints = maxPoints - minPoints;
	}

 	/*
 	 * (non-Javadoc)
 	 * @see ao.model.spell.effect.Effect#apply(ao.model.character.Character, ao.model.character.Character)
 	 */
	@Override
	public void apply(Character caster, Character target) {
		int points = minPoints + randomGenerator.nextInt(deltaPoints);
		points += (int) Math.round(points * 0.03f * caster.getLevel());
	    
		target.addToHitPoints(points);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.spell.effect.Effect#appliesTo(ao.model.character.Character, ao.model.character.Character)
	 */
	@Override
	public boolean appliesTo(Character caster, Character target) {
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
