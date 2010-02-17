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

public class FoodTest extends AbstractItemTest {

	private static final int MIN_HUN = 1;
	private static final int MAX_HUN = 5;
	
	private Food food1;
	private Food food2;
	
	@Before
	public void setUp() throws Exception {
		food1 = new Food(1, "Apple", 5, true, 1, 1, 0, 0, null, false, MIN_HUN, MAX_HUN);
		food2 = new Food(1, "Green Apple", 1, true, 1, 1, 0, 0, null, false, MAX_HUN, MAX_HUN);
		
		item = food2;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Green Apple";
		itemValue = 1;
		itemNewbie = false;
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
		assertEquals(food1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(food1.graphic, clone.graphic);
		assertEquals(food1.id, clone.id);
		assertEquals(food1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(food1.maxHun, clone.maxHun);
		assertEquals(food1.minHun, clone.minHun);
		assertEquals(food1.name, clone.name);
		assertEquals(food1.tradeable, clone.tradeable);
		assertEquals(food1.usageDifficulty, clone.usageDifficulty);
		assertEquals(food1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(food1 == clone);
		
		
		clone = (Food) food2.clone();
		
		// Make sure all fields match
		assertEquals(food2.amount, clone.amount);
		assertEquals(food2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(food2.graphic, clone.graphic);
		assertEquals(food2.id, clone.id);
		assertEquals(food2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(food2.maxHun, clone.maxHun);
		assertEquals(food2.minHun, clone.minHun);
		assertEquals(food2.name, clone.name);
		assertEquals(food2.tradeable, clone.tradeable);
		assertEquals(food2.usageDifficulty, clone.usageDifficulty);
		assertEquals(food2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(food2 == clone);
	}
	
}
