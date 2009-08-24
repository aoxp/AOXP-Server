package ao.domain.inventory;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.domain.worldobject.Item;

public class InventoryImplTest {

	private Inventory inventory;
	
	@Before
	public void setUp() throws Exception {
		inventory = new InventoryImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddItem() {
		// TODO : Complete mocking once implementation is done
		Item item = EasyMock.createMock(Item.class);
		
		inventory.addItem(item);
		Assert.assertTrue(inventory.hasItem(item) != -1);
	}

	@Test
	public void testGetItem() {
		// TODO : Complete mocking once implementation is done
		Item item = EasyMock.createMock(Item.class);
		
		inventory.addItem(item);
		int slot = inventory.hasItem(item);
		Assert.assertNotNull(inventory.getItem(slot));
		
		// Test bounds
		inventory.getItem(-1);
		inventory.getItem(inventory.getCapacity());
	}

	@Test
	public void testHasFreeSlots() {
		// TODO : Complete mocking once implementation is done
		Assert.assertTrue(inventory.hasFreeSlots());
		
		// TODO : Fill the inventory and then make sure hasFreeSlots() is false 
	}

	@Test
	public void testHasItem() {
		// TODO : Complete mocking once implementation is done
		Item item = EasyMock.createMock(Item.class);
		
		Assert.assertEquals(-1, inventory.hasItem(item));
		inventory.addItem(item);
		Assert.assertTrue(inventory.hasItem(item) != -1);
	}

	@Test
	public void testRemoveItemInt() {
		// TODO : Complete mocking once implementation is done
		Item item = EasyMock.createMock(Item.class);
		
		inventory.addItem(item);
		int slot = inventory.hasItem(item);
		Assert.assertNotNull(inventory.removeItem(slot));
		Assert.assertEquals(-1, inventory.hasItem(item));
		
		// Test bounds
		Assert.assertNull(inventory.removeItem(-1));
		Assert.assertNull(inventory.removeItem(inventory.getCapacity()));
	}

	@Test
	public void testRemoveItemItem() {
		// TODO : Complete mocking once implementation is done
		Item item = EasyMock.createMock(Item.class);
		
		inventory.addItem(item);
		inventory.removeItem(item);
		Assert.assertEquals(-1, inventory.hasItem(item));
		
		// Try to remove a item not in inventory
		Assert.assertNull(inventory.removeItem(item));
	}

	@Test
	public void testRemoveItemIntInt() {
		// TODO : Make this mock fake an amount of 2
		Item item = EasyMock.createMock(Item.class);
		
		inventory.addItem(item);
		int slot = inventory.hasItem(item);
		
		Item removedItem = inventory.removeItem(slot, 1);
		Assert.assertNotNull(removedItem);
		Assert.assertTrue(inventory.hasItem(item) != -1);
		
		removedItem = inventory.removeItem(slot, 1);
		Assert.assertNotNull(removedItem);
		Assert.assertEquals(-1, inventory.hasItem(item));
		
		// Test bounds
		Assert.assertNull(inventory.removeItem(-1, 1));
		Assert.assertNull(inventory.removeItem(inventory.getCapacity(), 1));
	}

}
