package ao.domain.character.movement;

import ao.domain.character.Character;
import ao.domain.map.Position;

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
	 * @param pos	The final destination position 
	 */
	void setTarget(Position pos);
}
