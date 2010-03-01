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

public class AgilityPotionTest extends AbstractItemTest {

	private static final int MIN_AGI = 1;
	private static final int MAX_AGI = 5;
	
	private AgilityPotion potion1;
	private AgilityPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		potion1 = new AgilityPotion(1, "Yellow Potion", 5, true, 1, 1, 0, 0, null, false, MIN_AGI, MAX_AGI);
		potion2 = new AgilityPotion(1, "Big Yellow Potion", 1, true, 1, 1, 0, 0, null, false, MAX_AGI, MAX_AGI);
		
		item = potion2;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Big Yellow Potion";
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
		
		// Consumption of potion2 requires these 2 calls.
		inventory.cleanup();
		character.addToAgility(MAX_AGI);
		
		EasyMock.replay(inventory, character);
		
		potion2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		Capture<Integer> capture = new Capture<Integer>();
		
		// Consumption of potion1 requires just a call to addToAgility.
		character.addToAgility(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_AGI);
		assertTrue(capture.getValue() <= MAX_AGI);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinModifier() {
		
		assertEquals(MIN_AGI, potion1.getMinModifier());
		assertEquals(MAX_AGI, potion2.getMinModifier());
	}

	@Test
	public void testGetMaxModifier() {
		
		assertEquals(MAX_AGI, potion1.getMaxModifier());
		assertEquals(MAX_AGI, potion2.getMaxModifier());
	}

	@Test
	public void testClone() {
		
		AgilityPotion clone = (AgilityPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(potion1.graphic, clone.graphic);
		assertEquals(potion1.id, clone.id);
		assertEquals(potion1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(potion1.maxModifier, clone.maxModifier);
		assertEquals(potion1.minModifier, clone.minModifier);
		assertEquals(potion1.name, clone.name);
		assertEquals(potion1.tradeable, clone.tradeable);
		assertEquals(potion1.usageDifficulty, clone.usageDifficulty);
		assertEquals(potion1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (AgilityPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(potion2.graphic, clone.graphic);
		assertEquals(potion2.id, clone.id);
		assertEquals(potion2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(potion2.maxModifier, clone.maxModifier);
		assertEquals(potion2.minModifier, clone.minModifier);
		assertEquals(potion2.name, clone.name);
		assertEquals(potion2.tradeable, clone.tradeable);
		assertEquals(potion2.usageDifficulty, clone.usageDifficulty);
		assertEquals(potion2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
}
