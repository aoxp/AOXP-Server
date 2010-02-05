/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ao.model.spell.effect;

import java.util.Random;

import ao.exception.InvalidTargetException;
import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

/**
 * An effect that alters HP (either increases or decreases)
 */
public class HitPointsEffect implements Effect {

	private static final float EFFECT_MULTIPLIER = 0.03f;
	
	private static final Random randomGenerator = new Random();
	
	private int minPoints;
	private int deltaPoints;
	
	/**
	 * Creates a new HitPointsEffect instance.
	 * @param minPoints The minimum points to be added / subtracted (if negative)
	 * @param maxPoints The maximum points to be added / subtracted (if negative)
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
		points += (int) Math.round(points * EFFECT_MULTIPLIER * caster.getLevel());
	    
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
		throw new InvalidTargetException();
	}

}
