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

package ao.model.character.movement;

import java.util.Random;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.map.Position;

public class GreedyMovementStrategy implements MovementStrategy {

	private Position targetPosition;
	private Character targetCharacter;
	
	private Random randomGenerator = new Random();
	
	@Override
	public Heading move(Position pos) {
		if (targetCharacter != null) {
			targetPosition = targetCharacter.getPosition();
		}
		
		int x = (int) pos.getX() - targetPosition.getX();
		int y = (int) pos.getY() - targetPosition.getY();
		
		if (x < 0 && y < 0) { // Northeast
			return randomGenerator.nextInt(2) == 0 ? Heading.NORTH : Heading.EAST;
		} else if (x > 0 && y < 0) { // Northwest
			return randomGenerator.nextInt(2) == 0 ? Heading.NORTH : Heading.WEST;
		} else if (x > 0 && y > 0) { // Southwest
			return randomGenerator.nextInt(2) == 0 ? Heading.SOUTH : Heading.WEST;
		} else if (x < 0 && y > 0) { // Southeast
			return randomGenerator.nextInt(2) == 0 ? Heading.SOUTH : Heading.EAST;
		} else if (x == 0 && y < 0) { // North
			return Heading.NORTH;
		} else if (x == 0 && y > 0) { // South
			return Heading.SOUTH;
		} else if (x > 0 && y == 0) { // West
			return Heading.WEST;
		} else if (x < 0 && y == 0) { // East
			return Heading.EAST;
		}

		// position and targetPos are the same position
		return null;
	}

	@Override
	public void setTarget(Character target) {
		targetCharacter = target;
	}

	@Override
	public void setTarget(Position pos) {
		targetPosition = pos;
	}

}
