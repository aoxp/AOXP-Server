/**
 * 
 */
package ao.domain.inventory;

import ao.domain.worldobject.Item;

/**
 * @author Marco
 *
 */
public interface Inventory {
	/**
	 * Checks if the inventory has any free slots.
	 * @return True if there are any free slots.
	 */
	boolean hasFreeSlots();
	
	/**
	 * Adds an Item to the inventory.
	 * @param item references to the item to add
	 * @return 0 if the item was successfully added or
	 * 			the remainder amount of the item that could not be completely added
	 */
	int addItem(Item item);
	
	/**
	 * Removes the item of the desired slot.
	 * @param slot references to the slot of the item to be removed
	 * @return The item removed.
	 */
	Item removeItem(int slot);
	
	/**
	 * Removes the item from the inventory
	 * @param slot references to the slot of the item to be removed
	 * @param amount to the amount of the item to be removed
	 * @return The item removed with the amount removed
	 */
	Item removeItem(int slot, int amount);
	
	/**
	 * Removes the item from the inventory. If the requested amount is greater than
	 * the existing amount, everything is removed.
	 * @param item references to the item to be removed
	 * @return The item removed, null if the item wasn't in the inventory
	 */
	Item removeItem(Item item);
	
	
	/**
	 * Gets the item of the desired slot.
	 * @param slot references to the slot of the item.
	 * @return The item at the requested slot
	 */
	Item getItem(int slot);	
	
	/**
	 * Checks if an Item is in the inventory
	 * @param item references to the item to find
	 * @return The slot of the item in the inventory or -1 if the item isn't in the inventory
	 */
	int hasItem(Item item);
	
	/**
	 * Gets the current inventory capacity
	 * @return The number of slots in the inventory
	 */
	int getCapacity();
	
	/**
	 * Gets the amount of an item  in the inventory
	 * @param item to look for its amount
	 * @return The amount of the item.
	 */
	int getItemAmount(Item item);
}
