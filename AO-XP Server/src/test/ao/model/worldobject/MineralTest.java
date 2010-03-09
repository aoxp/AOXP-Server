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
import ao.model.worldobject.properties.ItemProperties;

public class MineralTest extends AbstractItemTest {

	private Mineral mineral1;
	private Mineral mineral2;
	
	@Before
	public void setUp() throws Exception {
		ItemProperties props1 = new ItemProperties(WorldObjectType.MINERAL, 1, "Gold", 1, true, 1, 0, null, null, false);
		mineral1 = new Mineral(props1, 1);
		
		ItemProperties props2 = new ItemProperties(WorldObjectType.MINERAL, 1, "Cooper", 1, true, 1, 0, null, null, false);
		mineral2 = new Mineral(props2, 1);
		
		object = mineral2;
		ammount = 1;
		objectProps = props2;
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
		
		mineral1.use(character);
		mineral2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testClone() {
		
		Mineral clone = (Mineral) mineral1.clone();
		
		// Make sure all fields match
		assertEquals(mineral1.amount, clone.amount);
		assertEquals(mineral1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(mineral1 == clone);
		
		
		clone = (Mineral) mineral2.clone();
		
		// Make sure all fields match
		assertEquals(mineral2.amount, clone.amount);
		assertEquals(mineral2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(mineral2 == clone);
	}

}
