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

package ao.model.inventory;

import ao.model.worldobject.Item;

/**
 * Concrete implementation of the user inventory.
 */
public class InventoryImpl implements Inventory {
	
	private static final int DEFAULT_INVENTORY_CAPACITY = 20;
	
	protected Item[] inventory;
	
	public InventoryImpl() {
		this(DEFAULT_INVENTORY_CAPACITY);
	}
	
	public InventoryImpl(int slots) {
		inventory = new Item[slots];
	}
	
	public InventoryImpl(Item[] inventory) {
		this.inventory = inventory;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#addItem(ao.model.worldobject.Item)
	 */
	@Override
	public int addItem(Item item) {
		int i;
		
		if ((i = hasItem(item)) != -1) {
			int amount = item.getAmount();
			int newAmount, oldAmount;
			int id = item.getId();
			
			// Stack the item to previous slots
			for (; i < inventory.length; i++) {
				if (inventory[i]!= null && inventory[i].getId() == id) {
					oldAmount = inventory[i].getAmount();
					newAmount = inventory[i].addAmount(amount);
					amount += oldAmount - newAmount; 
					if (amount == 0) {
						return 0;
					}
				}
			}
			// Set the item's amount to the remainder
			item.addAmount(amount - item.getAmount());
		}
		
		for (i = 0; i < inventory.length; i++) {
			if (inventory[i] == null) {
				inventory[i] = item;
				return 0;
			}
		}
		
		return item.getAmount();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#getItem(int)
	 */
	@Override
	public Item getItem(int slot) {
		if (slot < 0 || slot >= inventory.length) {
			return null;	
		}
		
		return inventory[slot];
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#hasFreeSlots()
	 */
	@Override
	public boolean hasFreeSlots() {
		for (Item item : inventory) {
			if (item == null) {
				return true;
			}		
		}
			
		return false;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#hasItem(ao.model.worldobject.Item)
	 */
	@Override
	public int hasItem(Item item) {
		int id = item.getId();
		
		for (int i = 0; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getId() == id) {
				return i;
			}
		}
		
		return -1;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#removeItem(int)
	 */
	@Override
	public Item removeItem(int slot) {
		Item item = getItem(slot);
		
		if (item != null) {
			inventory[slot] = null;
		}
		
		return item;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#removeItem(ao.model.worldobject.Item)
	 */
	@Override
	public Item removeItem(Item item) {
		int i;
		
		if ((i = hasItem(item)) == -1) {
			return null;
		}
		
		Item itemRemoved = item.clone();
		int remainingAmount = item.getAmount();
		int left;
		int id = item.getId();
		
		for (; i < inventory.length; i++) {
			if (inventory[i] != null && inventory[i].getId() == id) {
				remainingAmount -= inventory[i].getAmount();
				left = inventory[i].addAmount( -(inventory[i].getAmount() + remainingAmount));
				
				if (left == 0) {
					inventory[i] = null;
				}
				
				if (remainingAmount <= 0) {
					return itemRemoved;
				}
			}
		}
		
		// Couldn't remove the full amount, set the proper amount.
		itemRemoved.addAmount(-remainingAmount);
		
		return itemRemoved;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#removeItem(int, int)
	 */
	@Override
	public Item removeItem(int slot, int amount) {
		Item item = getItem(slot);
		if (item == null) {
			return null;
		}

		Item itemRemoved = item.clone();
		int left = item.addAmount(-amount);
		
		if (left == 0){
			inventory[slot] = null;
		}
		
		itemRemoved.addAmount(-left);

		return itemRemoved;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#getCapacity()
	 */
	@Override
	public int getCapacity() {
		return inventory.length;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#getItemAmount(ao.model.worldobject.Item)
	 */
	@Override
	public int getItemAmount(Item item) {

		int amount = 0;
		int id = item.getId();
		
		for (Item i : inventory){
			if (i != null && id == i.getId()){
				amount += i.getAmount();
			}
		}
		
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#cleanup()
	 */
	@Override
	public void cleanup() {
		for (int i = inventory.length - 1; i >= 0; i--) {
			if (inventory[i] != null && inventory[i].getAmount() == 0) {
				inventory[i] = null;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.inventory.Inventory#setCapacity(int)
	 */
	@Override
	public void setCapacity(int capacity) {
		Item[] tmpInventory = new Item[capacity];
		
		for (int i = 0; i < capacity; i++) {
			tmpInventory[i] = inventory[i];
		}
		
		// TODO : Throw all other items. What happens to non-falling items in such slots?
		
		inventory = tmpInventory;
	}

}
