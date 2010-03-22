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

public class ManaPotionTest extends AbstractItemTest {

	private static final int MIN_MANA = 1;
	private static final int MAX_MANA = 5;
	
	private ManaPotion potion1;
	private ManaPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		StatModifyingItemProperties props1 = new StatModifyingItemProperties(WorldObjectType.MANA_POTION, 1, "Blue Potion", 1, 1, 0, null, null, false, false, false, false, MIN_MANA, MAX_MANA);
		potion1 = new ManaPotion(props1, 5);
		
		StatModifyingItemProperties props2 = new StatModifyingItemProperties(WorldObjectType.MANA_POTION, 1, "Big Blue Potion", 1, 1, 0, null, null, false, false, false, false, MAX_MANA, MAX_MANA);
		potion2 = new ManaPotion(props2, 1);
		
		object = potion2;
		ammount = 5;
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
		
		// Consumption of potion2 requires these 2 calls.
		inventory.cleanup();
		character.addToMana(MAX_MANA);
		
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
		
		// Consumption of potion1 requires just a call to addToMana.
		character.addToMana(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_MANA);
		assertTrue(capture.getValue() <= MAX_MANA);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinMana() {
		
		assertEquals(MIN_MANA, potion1.getMinMana());
		assertEquals(MAX_MANA, potion2.getMinMana());
	}

	@Test
	public void testGetMaxMana() {
		
		assertEquals(MAX_MANA, potion1.getMaxMana());
		assertEquals(MAX_MANA, potion2.getMaxMana());
	}

	@Test
	public void testClone() {
		
		ManaPotion clone = (ManaPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (ManaPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
}
