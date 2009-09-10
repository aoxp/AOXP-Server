package ao.model.character.movement;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.map.Position;

public interface MovementStrategy {

	/**
	 * Applies the movement strategy
	 * 
	 * @return null if there is no need to move.
	 */
	Heading move();
	
	/**
	 * Targets a new character
	 * @param target	The character to target
	 */
	void setTarget(Character target);
	
	/**
	 * Targets a position
	 * @param pos	The final destination position 
	 */
	void setTarget(Position pos);

	/**
	 * Sets the current position.
	 * @param pos The position.
	 */
	void setPosition(Position pos);
}
