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

package com.ao.model.worldobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.ItemProperties;

public class EmptyBottleTest extends AbstractItemTest {

	private EmptyBottle bottle1;
	private EmptyBottle bottle2;
	
	@Before
	public void setUp() throws Exception {
		ItemProperties props1 = new ItemProperties(WorldObjectType.EMPTY_BOTTLE, 1, "Empty Bottle", 1, 1, 0, null, null, false, false, false, false);
		bottle1 = new EmptyBottle(props1, 1);
		
		ItemProperties props2 = new ItemProperties(WorldObjectType.EMPTY_BOTTLE, 1, "Empty Bottle", 1, 1, 0, null, null, false, false, false, false);
		bottle2 = new EmptyBottle(props2, 1);
		
		object = bottle2;
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
		
		bottle1.use(character);
		bottle2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testClone() {
		
		EmptyBottle clone = (EmptyBottle) bottle1.clone();
		
		// Make sure all fields match
		assertEquals(bottle1.amount, clone.amount);
		assertEquals(bottle1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(bottle1 == clone);
		
		
		clone = (EmptyBottle) bottle2.clone();
		
		// Make sure all fields match
		assertEquals(bottle2.amount, clone.amount);
		assertEquals(bottle2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(bottle2 == clone);
	}

}
