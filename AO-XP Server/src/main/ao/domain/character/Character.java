package ao.domain.character;

import ao.domain.map.Position;

public interface Character {

	/**
	 * Retrieves the character's hp points. 
	 * @return The character's hp points.
	 */
	int getHP();
	
	/**
	 * Adds (or substracts if the given number is negative) points to the character's hp.
	 * @param points	The points to add.
	 */
	void addToHP(int points);
	
	/**
	 * Retrieves the maximum character's hp points.
	 * @return The maximum caracter's hp points.
	 */
	int getMaxHP();
	
	/**
	 * Adds (or substracts if the given number is negative) points to character's max hp.
	 * @param points 	The points to add.
	 */
	void addToMaxHP(int points);
	
	/**
	 * Retrieves the character's mana points.
	 * @return The caracter's mana points.
	 */
	int getMana();
	
	/**
	 * Adds (or substracts if the given number is negative) points to the character's hp.
	 * @param points The points to add.
	 */
	void addToMana(int points);
	
	/**
	 * Retrieves the maximum character's mana points.
	 * @return The maximum character's mana points.
	 */
	int getMaxMana();
	
	/**
	 * Adds (or substracts if the given number is negative) points to the character's maximum mana points.
	 * @param points The points to add.
	 */
	void addToMaxMana(int points);
	
	/**
	 * Retrieves the character's thirstiness.
	 * @return The character's thirstiness points.
	 */
	int getThirstiness();
	
	/**
	 * Adds (or substracts if the given number is negative) points to the character's thirstiness.
	 * @param points The points to add.
	 */
	void addToThirstiness(int points);
	
	/**
	 * Retrieves the character's hunger points.
	 * @return The character's hunger points.
	 */
	int getHunger();
	
	/**
	 * Adds (or substracts if the given number is negative) points to the character's hunger.
	 * @param points
	 */
	void addToHunger(int points);
	
	/**
	 * Checks if the character is paralyzed.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isParalyzed();

	/**
	 * Sets wether the character is paralyzed, or not.
	 * @param paralyzed The character's paralysis status.
	 */
	void setParalyzed(boolean paralyzed);
	
	/**
	 * Checks if the character is immobilized.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isImmobilized();
	
	/**
	 * Sets wether the character is immobilized, or not.
	 * @param immobilized The character's immobilization status.
	 */
	void setImmobilized(boolean immobilized);
	
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
