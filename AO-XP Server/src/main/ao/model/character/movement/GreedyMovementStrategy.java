package ao.model.character.movement;

import java.util.Random;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.map.Position;

public class GreedyMovementStrategy implements MovementStrategy {

	private Position targetPosition;
	private Character targetCharacter;
	private Position position;
	
	private Random randomGenerator = new Random();
	
	@Override
	public Heading move() {
		if (targetCharacter != null) {
			targetPosition = targetCharacter.getPosition();
		}
		
		int x = (int) position.getX() - targetPosition.getX();
		int y = (int) position.getY() - targetPosition.getY();
		
		if (x < 0 && y > 0) { // Northeast
		} else if (x > 0 && y > 0) { // Northwest
			return randomGenerator.nextInt(1) == 0 ? Heading.NORTH : Heading.WEST;
		} else if (x > 0 && y < 0) { // Southwest
			return randomGenerator.nextInt(1) == 0 ? Heading.SOUTH : Heading.WEST;
		} else if (x < 0 && y < 0) { // Southeast
			return randomGenerator.nextInt(1) == 0 ? Heading.SOUTH : Heading.EAST;
		} else if (x == 0 && y > 0) { // North
			return Heading.NORTH;
		} else if (x == 0 && y < 0) { // South
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

	@Override
	public void setPosition(Position pos) {
		position = pos;
	}

}
