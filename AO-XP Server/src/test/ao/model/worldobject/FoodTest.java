package ao.model.worldobject;

import static org.junit.Assert.*;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;

public class FoodTest {

	private static final int MIN_HUN = 1;
	private static final int MAX_HUN = 5;
	
	private Food food1;
	private Food food2;
	
	@Before
	public void setUp() throws Exception {
		food1 = new Food(1, "Apple", 5, true, 1, 1, 0, 0, null, MIN_HUN, MAX_HUN);
		food2 = new Food(1, "Green Apple", 1, true, 1, 1, 0, 0, null, MAX_HUN, MAX_HUN);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUseWithCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of food2 requires these 2 calls.
		inventory.cleanup();
		character.addToHunger(MAX_HUN);
		
		EasyMock.replay(inventory, character);
		
		food2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		Capture<Integer> capture = new Capture<Integer>();
		
		// Consumption of food1 requires just a call to addToHunger.
		character.addToHunger(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		food1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_HUN);
		assertTrue(capture.getValue() <= MAX_HUN);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinHun() {
		
		assertEquals(MIN_HUN, food1.getMinHun());
		assertEquals(MAX_HUN, food2.getMinHun());
	}

	@Test
	public void testGetMaxHun() {
		
		assertEquals(MAX_HUN, food1.getMaxHun());
		assertEquals(MAX_HUN, food2.getMaxHun());
	}

}
