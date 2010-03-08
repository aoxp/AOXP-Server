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

package ao.model.worldobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;
import ao.model.worldobject.properties.StatModifyingItemProperties;

public class DrinkTest extends AbstractItemTest {

	private static final int MIN_THIRST = 1;
	private static final int MAX_THIRST = 5;
	
	private Drink drink1;
	private Drink drink2;
	
	@Before
	public void setUp() throws Exception {
		StatModifyingItemProperties props1 = new StatModifyingItemProperties(1, "Apple Juice", 1, true, 1, 0, null, false, MIN_THIRST, MAX_THIRST);
		drink1 = new Drink(props1, 5);
		
		StatModifyingItemProperties props2 = new StatModifyingItemProperties(1, "Green Apple Juice", 1, true, 1, 0, null, false, MAX_THIRST, MAX_THIRST);
		drink2 = new Drink(props2, 1);
		
		object = drink2;
		ammount = 1;
		objectProps = props2;
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
		
		// Consumption of drink1 requires just a call to addToThirstiness.
		character.addToThirstiness(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		drink1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_THIRST);
		assertTrue(capture.getValue() <= MAX_THIRST);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinThirst() {
		
		assertEquals(MIN_THIRST, drink1.getMinThirst());
		assertEquals(MAX_THIRST, drink2.getMinThirst());
	}

	@Test
	public void testGetMaxThirst() {
		
		assertEquals(MAX_THIRST, drink1.getMaxThirst());
		assertEquals(MAX_THIRST, drink2.getMaxThirst());
	}
	
	@Test
	public void testClone() {
		
		Drink clone = (Drink) drink1.clone();
		
		// Make sure all fields match
		assertEquals(drink1.amount, clone.amount);
		assertEquals(drink1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(drink1 == clone);
		
		
		clone = (Drink) drink2.clone();
		
		// Make sure all fields match
		assertEquals(drink2.amount, clone.amount);
		assertEquals(drink2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(drink2 == clone);
	}

}
