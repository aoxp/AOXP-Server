package ao.domain.character;

import ao.domain.inventory.Inventory;
import ao.domain.map.Position;
import ao.domain.worldobject.EquipableItem;
import ao.domain.worldobject.Item;

public interface Character {

	/**
	 * Retrieves the character's hit points. 
	 * @return The character's hit points.
	 */
	int getHitPoints();
	
	/**
	 * Adds (or subtracts if the given number is negative) points to the character's hit points.
	 * @param points	The points to add.
	 */
	void addToHitPoints(int points);
	
	/**
	 * Retrieves the maximum character's hit points.
	 * @return The maximum caracter's hit points.
	 */
	int getMaxHitPoints();
	
	/**
	 * Adds (or subtracts if the given number is negative) points to character's max hit points.
	 * @param points 	The points to add.
	 */
	void addToMaxHP(int points);
	
	/**
	 * Retrieves the character's mana points.
	 * @return The caracter's mana points.
	 */
	int getMana();
	
	/**
	 * Adds (or subtracts if the given number is negative) points to the character's mana points.
	 * @param points The points to add.
	 */
	void addToMana(int points);
	
	/**
	 * Retrieves the maximum character's mana points.
	 * @return The maximum character's mana points.
	 */
	int getMaxMana();
	
	/**
	 * Adds (or subtracts if the given number is negative) points to the character's maximum mana points.
	 * @param points The points to add.
	 */
	void addToMaxMana(int points);
	
	/**
	 * Retrieves the character's thirstiness.
	 * @return The character's thirstiness points.
	 */
	int getThirstiness();
	
	/**
	 * Adds (or subtracts if the given number is negative) points to the character's thirstiness.
	 * @param points The points to add.
	 */
	void addToThirstiness(int points);
	
	/**
	 * Retrieves the character's hunger points.
	 * @return The character's hunger points.
	 */
	int getHunger();
	
	/**
	 * Adds (or subtracts if the given number is negative) points to the character's hunger.
	 * @param points
	 */
	void addToHunger(int points);
	
	/**
	 * Checks if the character is paralyzed.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isParalyzed();

	/**
	 * Sets whether the character is paralyzed, or not.
	 * @param paralyzed The character's paralysis status.
	 */
	void setParalyzed(boolean paralyzed);
	
	/**
	 * Checks if the character is immobilized.
	 * @return True if the character is paralyzed, false otherwise.
	 */
	boolean isImmobilized();
	
	/**
	 * Sets whether the character is immobilized, or not.
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
	
	/**
	 * Uses the given item (must be in the character's inventory).
	 * @param item The item to use.
	 */
	void use(Item item);
	
	/**
	 * Equips the given item (must be in the character's inventory).
	 * @param item The item to use.
	 */
	void equip(EquipableItem item);
	
	/**
	 * Retrieves the character's total attack power (considering items and effects).
	 * @return The character's total attack power (considering items and effects).
	 */
	int getAttackPower();
	
	/**
	 * Retrieves the character's total defense power (considering items and effects).
	 * @return The character's total defense power (considering items and effects).
	 */
	int getDefensePower();
	
	/**
	 * Retrieves a character's inventory.
	 * @return The character's inventory.
	 */
	Inventory getInventory();
	
	/**
	 * Retrieves the character's reputation.
	 * @return The character's reputation.
	 */
	Reputation getReputation();
	
	/**
	 * Retrieves the user's nickname.
	 * @return The user's name-
	 */
	String getName();
	
	/**
	 * Retrieves the user's display name (includes status, guild, etc).
	 * @return The user's display name.
	 */
	String getDisplayName();
	
	/**
	 * Retrieves the Character's description.
	 * @return The Character's description.
	 */
	String getDescription();
	
	/**
	 * Retrieves the character's body.
	 * @return The character's body.
	 */
	int getBody();
	
	/**
	 * Retrieves the character's head.
	 * @return The character's head.
	 */
	int getHead();

	/**
	 * Retrieves the character's poisoning status.
	 * @return The character's poisoning status.
	 */
	boolean isPoisoned();
	
	/**
	 * Sets whether the character is poisoned, or not.
	 * @param poisoned The new character's poisoning status.
	 */
	void setPoisoned(boolean poisoned);
	
	/**
	 * Retrieves the character's level.
	 * @return The character's level.
	 */
	byte getLevel();
	
	/**
	 * Adds the nivel levels to character's level.
	 * @param level The levels to add.
	 */
	void addToLevel(byte level);
}
