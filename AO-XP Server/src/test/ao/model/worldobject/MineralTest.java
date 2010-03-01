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

public class MineralTest extends AbstractItemTest {

	private Mineral mineral1;
	private Mineral mineral2;
	
	@Before
	public void setUp() throws Exception {
		mineral1 = new Mineral(1, "Gold", 5, true, 1, 1, 0, 0, null, false);
		mineral2 = new Mineral(1, "Cooper", 1, true, 1, 1, 0, 0, null, false);
		
		item = mineral2;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Cooper";
		itemValue = 1;
		itemNewbie = false;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUse() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Usage of minerals do nothing.
		EasyMock.replay(inventory, character);
		
		mineral2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testClone() {
		
		Mineral clone = (Mineral) mineral1.clone();
		
		// Make sure all fields match
		assertEquals(mineral1.amount, clone.amount);
		assertEquals(mineral1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(mineral1.graphic, clone.graphic);
		assertEquals(mineral1.id, clone.id);
		assertEquals(mineral1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(mineral1.name, clone.name);
		assertEquals(mineral1.tradeable, clone.tradeable);
		assertEquals(mineral1.usageDifficulty, clone.usageDifficulty);
		assertEquals(mineral1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(mineral1 == clone);
		
		
		clone = (Mineral) mineral2.clone();
		
		// Make sure all fields match
		assertEquals(mineral2.amount, clone.amount);
		assertEquals(mineral2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(mineral2.graphic, clone.graphic);
		assertEquals(mineral2.id, clone.id);
		assertEquals(mineral2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(mineral2.name, clone.name);
		assertEquals(mineral2.tradeable, clone.tradeable);
		assertEquals(mineral2.usageDifficulty, clone.usageDifficulty);
		assertEquals(mineral2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(mineral2 == clone);
	}

}
