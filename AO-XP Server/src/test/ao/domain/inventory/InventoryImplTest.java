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
		Item item = EasyMock.createMock(Item.class);
		EasyMock.expect(item.getId()).andReturn(1).anyTimes();
		EasyMock.replay(item);
		
		inventory.addItem(item);
		Assert.assertTrue(inventory.hasItem(item) != -1);
	}

	@Test
	public void testGetItem() {
		Item item = EasyMock.createMock(Item.class);
		EasyMock.expect(item.getId()).andReturn(1).anyTimes();
		EasyMock.replay(item);
		
		inventory.addItem(item);
		int slot = inventory.hasItem(item);
		Assert.assertNotNull(inventory.getItem(slot));
		
		// Test bounds
		inventory.getItem(-1);
		inventory.getItem(inventory.getCapacity());
	}

	@Test
	public void testHasFreeSlots() {
		Assert.assertTrue(inventory.hasFreeSlots());
		
		Item[] item = new Item[inventory.getCapacity()];

		for (int i = 0; i< inventory.getCapacity(); i++) {
			item[i] = EasyMock.createMock(Item.class);
			EasyMock.expect(item[i].getId()).andReturn(i).anyTimes();
			EasyMock.replay(item[i]);
			
			inventory.addItem(item[i]);
		} 
		
		Assert.assertFalse(inventory.hasFreeSlots());
		
		inventory.removeItem(0);
		Assert.assertTrue(inventory.hasFreeSlots());
	}

	@Test
	public void testHasItem() {
		Item item = EasyMock.createMock(Item.class);
		EasyMock.expect(item.getId()).andReturn(1).anyTimes();
		EasyMock.replay(item);
		
		Assert.assertEquals(-1, inventory.hasItem(item));
		inventory.addItem(item);
		Assert.assertTrue(inventory.hasItem(item) != -1);
	}

	@Test
	public void testRemoveItemInt() {
		Item item = EasyMock.createMock(Item.class);
		EasyMock.expect(item.getId()).andReturn(1).anyTimes();
		EasyMock.replay(item);
		
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
		Item item = EasyMock.createMock(Item.class);
		EasyMock.expect(item.getId()).andReturn(1).anyTimes();
		EasyMock.expect(item.getAmount()).andReturn(10).anyTimes();
		EasyMock.expect(item.addAmount(-10)).andReturn(0);
		Item newItem = EasyMock.createMock(Item.class);
		EasyMock.expect(newItem.getAmount()).andReturn(10).anyTimes();
		EasyMock.expect(item.clone()).andReturn(newItem);
		EasyMock.replay(item, newItem);
		
		inventory.addItem(item);
		inventory.removeItem(item);
		Assert.assertEquals(-1, inventory.hasItem(item));
		
		// Try to remove a item not in inventory
		Assert.assertNull(inventory.removeItem(item));
	}

	@Test
	public void testRemoveItemIntInt() {
		Item item = EasyMock.createMock(Item.class);
		EasyMock.expect(item.getId()).andReturn(1).anyTimes();
		EasyMock.expect(item.getAmount()).andReturn(2);
		EasyMock.expect(item.addAmount(-1)).andReturn(1);
		EasyMock.expect(item.addAmount(-1)).andReturn(0);
		Item newItem = EasyMock.createMock(Item.class);
		EasyMock.expect(newItem.getAmount()).andReturn(2).anyTimes();
		EasyMock.expect(item.clone()).andReturn(newItem);
		Item newItem2 = EasyMock.createMock(Item.class);
		EasyMock.expect(newItem2.getAmount()).andReturn(1).anyTimes();
		EasyMock.expect(item.clone()).andReturn(newItem2);
		EasyMock.expect(newItem.addAmount(-1)).andReturn(1);
		EasyMock.expect(newItem2.addAmount(-0)).andReturn(1);
		EasyMock.replay(item, newItem, newItem2);
		
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
