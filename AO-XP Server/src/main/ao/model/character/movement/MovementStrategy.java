package ao.model.character.movement;

import ao.model.character.Character;
import ao.model.map.Position;

public interface MovementStrategy {

	/**
	 * Applies the movement strategy
	 */
	void move();
	
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
}
