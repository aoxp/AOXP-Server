package ao.domain.inventory;

import ao.domain.worldobject.Item;

public class InventoryImpl implements Inventory {
	
	Item[] inventory;
	
	public InventoryImpl(){
		this(18);
	}
	
	public InventoryImpl(int slots){
		if (slots <= 0){
			return;
		}
		
		inventory = new Item[slots];
	}
	
	@Override
	public int addItem(Item item) {
		if (this.hasItem(item) != -1){
			int amount = item.getAmount();
			
			for (int i = 0; i < inventory.length; i++){
				if (inventory[i].getId() == item.getId()){
					amount = inventory[i].addAmount(amount); 
					if (amount == 0){
						return 0;
					}
				}
			}
			item.addAmount(-(item.getAmount() - amount));
		}
		
		for (int i = 0; i < inventory.length; i++){
			if (inventory[i] == null){
				inventory[i] = item;
				return 0;
			}
		}
		
		return item.getAmount();
	}

	@Override
	public Item getItem(int slot) {
		if (slot < 0 || slot >= inventory.length){
			return null;	
		}
		
		return inventory[slot];
	}

	@Override
	public boolean hasFreeSlots() {
		// TODO Auto-generated method stub
		for (Item item : inventory){
			if (item == null ){
				return true;
			}		
		}
			
		return false;
	}

	@Override
	public int hasItem(Item item) {
		for (int i = 0; i < inventory.length; i++){
			if (inventory[i].getId() == item.getId()){
				return i;
			}
		}
		
		return -1;
	}

	@Override
	public Item removeItem(int slot) {
		Item item = getItem(slot);
		if (item == null){
			return null;
		}

		inventory[slot] = null;
		return item;
	}

	@Override
	public Item removeItem(Item item) {
		//TODO: Empezar a usar la parte lo que devuelve addAmount.
		if ( this.hasItem(item) == -1 ){
			return null;
		}
		
		Item itemRemoved = item.clone();
		int remainingAmount = item.getAmount();
		
		for (int i = 0; i < inventory.length; i++){
			if (inventory[i].getId() == item.getId()) {
				remainingAmount -= inventory[i].getAmount();
				inventory[i].addAmount( -(inventory[i].getAmount()+remainingAmount));
				
				if (inventory[i].getAmount() <= 0){
					inventory[i] = null;
				}
				
				if (remainingAmount <= 0){
					return itemRemoved;
				}
			}
		}
		
		itemRemoved.addAmount(-remainingAmount);
		
		return itemRemoved;
	}

	@Override
	public Item removeItem(int slot, int amount) {
		Item item = getItem(slot);
		if (item == null){
			return null;
		}

		Item itemRemoved = item;
		
		item.addAmount(-amount);
		
		/**
		 * Remove the remaining amount from a copy of the item,
		 * so the copy will end with the amount of removed items
		 */
		itemRemoved.addAmount(-item.getAmount());
		
		if (item.getAmount() <= 0){
			inventory[slot] = null;
		}

		return itemRemoved;
	}

	@Override
	public int getCapacity() {
		return inventory.length;
	}

	@Override
	public int getItemAmount(Item item) {

		int amount = 0;
		
		for (Item i : inventory){
			if (item.getId() == i.getId()){
				amount += i.getAmount();
			}
		}
		
		return amount;
	}

}
