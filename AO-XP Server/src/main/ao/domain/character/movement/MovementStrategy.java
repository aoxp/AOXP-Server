package ao.domain.character.movement;

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
}
