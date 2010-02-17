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

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;

public class PoisonPotionTest extends AbstractItemTest {

	private PoisonPotion potion1;
	private PoisonPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		potion1 = new PoisonPotion(1, "Violet Potion", 5, true, 1, 1, 0, 0, null, false);
		potion2 = new PoisonPotion(1, "Violet Potion", 1, true, 1, 1, 0, 0, null, false);
		
		item = potion2;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Violet Potion";
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
		character.setPoisoned(false);
		
		EasyMock.replay(inventory, character);
		
		potion2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of potion1 requires just a call to setPoisoned.
		character.setPoisoned(false);
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testClone() {
		
		PoisonPotion clone = (PoisonPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(potion1.graphic, clone.graphic);
		assertEquals(potion1.id, clone.id);
		assertEquals(potion1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(potion1.name, clone.name);
		assertEquals(potion1.tradeable, clone.tradeable);
		assertEquals(potion1.usageDifficulty, clone.usageDifficulty);
		assertEquals(potion1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (PoisonPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(potion2.graphic, clone.graphic);
		assertEquals(potion2.id, clone.id);
		assertEquals(potion2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(potion2.name, clone.name);
		assertEquals(potion2.tradeable, clone.tradeable);
		assertEquals(potion2.usageDifficulty, clone.usageDifficulty);
		assertEquals(potion2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
}
