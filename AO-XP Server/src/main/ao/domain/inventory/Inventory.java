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
	 * @return True if the item was successfully added (the full amount).
	 * 		If not, the amount is altered to match the remaining.
	 */
	boolean addItem(Item item);
	
	/**
	 * Removes the item of the desired slot.
	 * @param slot references to the slot of the item to be removed
	 * @return the item removed.
	 */
	Item removeItem(int slot);
	
	/**
	 * Removes the item from the inventory.
	 * @param item references to the item to be removed
	 * @return the item removed, null if the item wasn't in the inventory
	 */
	Item removeItem(Item item);
	
	
	/**
	 * Gets the item of the desired slot.
	 * @param slot references to the slot of the item.
	 * @return the item
	 */
	Item getItem(int slot);	
	
	/**
	 * Checks if an Item is in the inventory
	 * @param item references to the item to find
	 * @return the slot of the item in the inventory or -1 if the item isn't in the inventory
	 */
	int hasItem(Item item);

}
