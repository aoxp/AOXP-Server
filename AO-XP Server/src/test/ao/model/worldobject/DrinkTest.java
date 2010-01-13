package ao.model.worldobject;

import static org.junit.Assert.*;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;

public class DrinkTest {

	private static final int MIN_THIRST = 1;
	private static final int MAX_THIRST = 5;
	
	private Drink drink1;
	private Drink drink2;
	
	@Before
	public void setUp() throws Exception {
		drink1 = new Drink(1, "Apple Juice", 5, true, 1, 1, 0, 0, null, MIN_THIRST, MAX_THIRST);
		drink2 = new Drink(1, "Green Apple Juice", 1, true, 1, 1, 0, 0, null, MAX_THIRST, MAX_THIRST);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUseWithCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of drink2 requires these 2 calls.
		inventory.cleanup();
		character.addToThirstiness(MAX_THIRST);
		
		EasyMock.replay(inventory, character);
		
		drink2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		Capture<Integer> capture = new Capture<Integer>();
		
		// Consumption of drink1 requires just a call to addToHunger.
		character.addToThirstiness(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		drink1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_THIRST);
		assertTrue(capture.getValue() <= MAX_THIRST);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinHun() {
		
		assertEquals(MIN_THIRST, drink1.getMinThirst());
		assertEquals(MAX_THIRST, drink2.getMinThirst());
	}

	@Test
	public void testGetMaxHun() {
		
		assertEquals(MAX_THIRST, drink1.getMaxThirst());
		assertEquals(MAX_THIRST, drink2.getMaxThirst());
	}

}
