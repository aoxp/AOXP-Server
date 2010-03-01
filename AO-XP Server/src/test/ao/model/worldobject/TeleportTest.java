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

public class TeleportTest extends AbstractItemTest {

	private static final int RADIUS = 4;
	
	private Teleport teleport1;
	private Teleport teleport2;
	
	@Before
	public void setUp() throws Exception {
		teleport1 = new Teleport(1, "Teleport", 5, true, 1, 1, 0, 0, null, false, RADIUS);
		teleport2 = new Teleport(1, "Teleport", 1, true, 1, 1, 0, 0, null, false, RADIUS);
		
		item = teleport2;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Teleport";
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
		
		teleport1.use(character);
		teleport2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testClone() {
		
		Teleport clone = (Teleport) teleport1.clone();
		
		// Make sure all fields match
		assertEquals(teleport1.amount, clone.amount);
		assertEquals(teleport1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(teleport1.graphic, clone.graphic);
		assertEquals(teleport1.id, clone.id);
		assertEquals(teleport1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(teleport1.name, clone.name);
		assertEquals(teleport1.tradeable, clone.tradeable);
		assertEquals(teleport1.usageDifficulty, clone.usageDifficulty);
		assertEquals(teleport1.value, clone.value);
		assertEquals(teleport1.radius, clone.radius);
		
		// Make sure the object itself is different
		assertFalse(teleport1 == clone);
		
		
		clone = (Teleport) teleport2.clone();
		
		// Make sure all fields match
		assertEquals(teleport2.amount, clone.amount);
		assertEquals(teleport2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(teleport2.graphic, clone.graphic);
		assertEquals(teleport2.id, clone.id);
		assertEquals(teleport2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(teleport2.name, clone.name);
		assertEquals(teleport2.tradeable, clone.tradeable);
		assertEquals(teleport2.usageDifficulty, clone.usageDifficulty);
		assertEquals(teleport2.value, clone.value);
		assertEquals(teleport2.radius, clone.radius);
		
		// Make sure the object itself is different
		assertFalse(teleport2 == clone);
	}

}
