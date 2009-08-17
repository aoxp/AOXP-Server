package ao.domain.inventory;

import ao.domain.worldobject.Item;

public class InventoryImpl implements Inventory {
	// TODO : Esto está cableado maaaal!!
	Item[] inventory = new Item[18];
	
	@Override
	public boolean addItem(Item item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Item getItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasFreeSlots() {
		// TODO Auto-generated method stub
		for (Item item : inventory) {
			if (item == null ) {
				return true;
			}
		}
		
		return false;
	}

	@Override
	public int hasItem(Item item) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Item removeItem(int slot) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Item removeItem(Item item) {
		// TODO Auto-generated method stub
		return null;
	}

}
