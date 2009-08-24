package ao.domain.inventory;

import ao.domain.worldobject.Item;

public class InventoryImpl implements Inventory {
	
	private static final int DEFAULT_INVENTORY_CAPACITY = 18;
	
	Item[] inventory;
	
	public InventoryImpl() {
		this(DEFAULT_INVENTORY_CAPACITY);
	}
	
	public InventoryImpl(int slots) {
		inventory = new Item[slots];
	}
	
	@Override
	public int addItem(Item item) {
		int i;
		
		if ((i = this.hasItem(item)) != -1) {
			int amount = item.getAmount();
			int newAmount, oldAmount;
			int id = item.getId();
			
			// Stack the item to previous slots
			for (; i < inventory.length; i++) {
				if (inventory[i].getId() == id) {
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

	@Override
	public Item getItem(int slot) {
		if (slot < 0 || slot >= inventory.length) {
			return null;	
		}
		
		return inventory[slot];
	}

	@Override
	public boolean hasFreeSlots() {
		for (Item item : inventory) {
			if (item == null ){
				return true;
			}		
		}
			
		return false;
	}

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

	@Override
	public Item removeItem(int slot) {
		Item item = getItem(slot);
		
		if (item != null) {
			inventory[slot] = null;
		}
		
		return item;
	}

	@Override
	public Item removeItem(Item item) {
		int i;
		
		if ((i = this.hasItem(item)) == -1) {
			return null;
		}
		
		Item itemRemoved = item.clone();
		int remainingAmount = item.getAmount();
		int left;
		int id = item.getId();
		
		for (; i < inventory.length; i++) {
			if (inventory[i].getId() == id) {
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

	@Override
	public int getCapacity() {
		return inventory.length;
	}

	@Override
	public int getItemAmount(Item item) {

		int amount = 0;
		int id = item.getId();
		
		for (Item i : inventory){
			if (id == i.getId()){
				amount += i.getAmount();
			}
		}
		
		return amount;
	}

}
