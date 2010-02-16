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

public class HPPotionTest extends AbstractItemTest {

	private static final int MIN_HP = 1;
	private static final int MAX_HP = 5;
	
	private HPPotion potion1;
	private HPPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		potion1 = new HPPotion(1, "Red Potion", 5, true, 1, 1, 0, 0, null, MIN_HP, MAX_HP);
		potion2 = new HPPotion(1, "Big Red Potion", 1, true, 1, 1, 0, 0, null, MAX_HP, MAX_HP);
		
		item = potion2;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Big Red Potion";
		itemValue = 1;
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
		character.addToHitPoints(MAX_HP);
		
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
		
		// Consumption of potion1 requires just a call to addToHitPoints.
		character.addToHitPoints(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_HP);
		assertTrue(capture.getValue() <= MAX_HP);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinHP() {
		
		assertEquals(MIN_HP, potion1.getMinHP());
		assertEquals(MAX_HP, potion2.getMinHP());
	}

	@Test
	public void testGetMaxHP() {
		
		assertEquals(MAX_HP, potion1.getMaxHP());
		assertEquals(MAX_HP, potion2.getMaxHP());
	}

	@Test
	public void testClone() {
		
		HPPotion clone = (HPPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(potion1.graphic, clone.graphic);
		assertEquals(potion1.id, clone.id);
		assertEquals(potion1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(potion1.maxHP, clone.maxHP);
		assertEquals(potion1.minHP, clone.minHP);
		assertEquals(potion1.name, clone.name);
		assertEquals(potion1.tradeable, clone.tradeable);
		assertEquals(potion1.usageDifficulty, clone.usageDifficulty);
		assertEquals(potion1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (HPPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(potion2.graphic, clone.graphic);
		assertEquals(potion2.id, clone.id);
		assertEquals(potion2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(potion2.maxHP, clone.maxHP);
		assertEquals(potion2.minHP, clone.minHP);
		assertEquals(potion2.name, clone.name);
		assertEquals(potion2.tradeable, clone.tradeable);
		assertEquals(potion2.usageDifficulty, clone.usageDifficulty);
		assertEquals(potion2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
}
