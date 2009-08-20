package ao.domain.character;

import ao.domain.map.Position;

public interface Character {

	/**
	 * Retrieves the character's hp points. 
	 * @return The character's hp points.
	 */
	int getHP();
	
	/**
	 * Retrieves the maximum character's hp points.
	 * @return The maximum caracter's hp points.
	 */
	int getMaxHP();
	
	/**
	 * Retrieves the character's mana points.
	 * @return The caracter's mana points.
	 */
	int getMana();
	
	/**
	 * Retrieves the maximum character's mana points.
	 * @return The maximum character's mana points.
	 */
	int getMaxMana();
	
	/**
	 * Retrieves the character's thirstiness.
	 * @return The character's thirstiness points.
	 */
	int getThirstiness();
	
	/**
	 * Retrieves the character's hunger points.
	 * @return The character's hunger points.
	 */
	int getHunger();
	
	/**
	 * Checks if the character is paralyzed.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isParalyzed();

	/**
	 * Checks if the character is immobilized.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isImmobilized();
	
	/**
	 * Retrieves the character's position.
	 * @return The character's position.
	 */
	Position getPosition();
	
	/**
	 * Sets the character's position.
	 * @param pos The new character's position.
	 */
	void setPosition(Position pos);
	
}
