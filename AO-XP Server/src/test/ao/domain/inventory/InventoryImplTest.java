package ao.domain.inventory;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InventoryImplTest {
	private InventoryImpl inventory;
	
	@Before
	public void setUp() throws Exception {
		this.inventory = new InventoryImpl();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testHasFreeSlots() {
		assertEquals(false, this.inventory.hasFreeSlots());
	}

	@Test
	public void testHasItem() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItemInt() {
		fail("Not yet implemented");
	}

	@Test
	public void testRemoveItemItem() {
		fail("Not yet implemented");
	}

}
