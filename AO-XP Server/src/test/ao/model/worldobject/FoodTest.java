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

import static org.junit.Assert.*;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;
import ao.model.worldobject.properties.StatModifyingItemProperties;

public class FoodTest extends AbstractItemTest {

	private static final int MIN_HUN = 1;
	private static final int MAX_HUN = 5;
	
	private Food food1;
	private Food food2;
	
	@Before
	public void setUp() throws Exception {
		StatModifyingItemProperties props1 = new StatModifyingItemProperties(WorldObjectType.FOOD, 1, "Apple", 1, 1, 0, null, null, false, false, false, false, MIN_HUN, MAX_HUN);
		food1 = new Food(props1, 5);
		
		StatModifyingItemProperties props2 = new StatModifyingItemProperties(WorldObjectType.FOOD, 1, "Green Apple", 1, 1, 0, null, null, false, false, false, false, MAX_HUN, MAX_HUN);
		food2 = new Food(props2, 1);
		
		object = food2;
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

	@Test
	public void testClone() {
		
		Food clone = (Food) food1.clone();
		
		// Make sure all fields match
		assertEquals(food1.amount, clone.amount);
		assertEquals(food1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(food1 == clone);
		
		
		clone = (Food) food2.clone();
		
		// Make sure all fields match
		assertEquals(food2.amount, clone.amount);
		assertEquals(food2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(food2 == clone);
	}
	
}
