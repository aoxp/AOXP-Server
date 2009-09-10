package ao.model.character.movement;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.map.Position;

/**
 * Dummy movement strategy, just stay quiet.
 */
public class QuietMovementStrategy implements MovementStrategy {

	@Override
	public Heading move() {
		// No, I will not.
		
		return null;
	}

	@Override
	public void setTarget(Character target) {
		// I don't care the target, I will never move!
	}

	@Override
	public void setTarget(Position pos) {
		// I don't care the target, I will never move!
	}

	@Override
	public void setPosition(Position pos) {
		// I don't care my position, it will never change!
	}

}
