package ao.domain.worldobject;

import ao.domain.character.Gender;
import ao.domain.character.Race;
import ao.domain.character.Reputation;
import ao.domain.character.archetype.UserArchetype;

public interface Item extends WorldObject {

	/**
	 * Retrieves the item's unique id.
	 * @return The item's unique id.
	 */
	int getId();
	
	/**
	 * Retrieves the item's amount.
	 * @return The amount of items put together.
	 */
	int getAmount();
	
	/**
	 * Attempts to use the item.
	 */
	void use();
	
	/**
	 * Adds (or subtracts if the given amount is negative) an amount of the item
	 * @param amount	references to the amount to be added (negative to subtract)
	 * @return			the new amount of the item.
	 */
	int addAmount(int amount);
	
	/**
	 * Checks if the item can be used/equipped given a race, gender, archetype and reputation.
	 * @param race			The race attempting to use the item.
	 * @param gender		The gender of the character attempting to use the item.
	 * @param archetype		The archetype attempting to use the item.
	 * @param reputation	The reputation of the character attempting to use the item.
	 * @return True if the item can be used, false otherwise.
	 */
	boolean canBeUsedBy(Race race, Gender gender, UserArchetype archetype, Reputation reputation);
	
	/**
	 * Retrieves the manufacture difficulty of the item (how hard it's to make).
	 * @return The manufacture difficulty of the item (how hard it's to make).
	 */
	int getManufactureDifficulty(); // TODO : Nota, esto reemplaza a minSkill para carpinteria, fundicion y herreria
	
	/**
	 * Retrieves the usage difficulty of the item.
	 * @return The usage difficulty of the item.
	 */
	int getUsageDifficulty(); // TODO : Nota, esto reemplaza minSkill para navegar, con lo que los separamos.
	
	/**
	 * Gets a deep clone of the item
	 * @return a copy of the item.
	 */
	Item clone();
	
	/**
	 * Check if the item is tradeable
	 * @return True if the item is tradeable, false otherwise
	 */
	boolean isTradeable();
	
	/**
	 * Retrieves the item's sell price
	 * @return Item sell price
	 */
	int getSellPrice();
	
	/**
	 * Retrieves the item's buy price
	 * @return Item buy price
	 */
	int getBuyPrice();
}
