/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

/**
 *
 */
package com.ao.model.inventory;

import com.ao.model.worldobject.Item;

/**
 * Character inventory.
 */
public interface Inventory extends Iterable<Item> {
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
	 * Sets the capacity of the inventory
	 * @param capacity The number of slots for the inventory
	 */
	void setCapacity(int capacity);

	/**
	 * Gets the amount of an item  in the inventory
	 * @param item to look for its amount
	 * @return The amount of the item.
	 */
	int getItemAmount(Item item);

	/**
	 * Removes all items with an amount of 0.
	 */
	void cleanup();
}
