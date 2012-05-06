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
import com.ao.model.worldobject.properties.WoodProperties;

public class WoodTest extends AbstractItemTest {

	private Wood wood1;
	private Wood wood2;
	
	@Before
	public void setUp() throws Exception {
		WoodProperties props1 = new WoodProperties(WorldObjectType.WOOD, 1, "Black Potion", 1, 1, null, null, false, false, false, false, WoodType.NORMAL);
		wood1 = new Wood(props1, 5);
		
		WoodProperties props2 = new WoodProperties(WorldObjectType.WOOD, 1, "Black Potion", 1, 1, null, null, false, false, false, false, WoodType.ELVEN);
		wood2 = new Wood(props2, 1);
		
		object = wood2;
		objectProps = props2;
		ammount = 1;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		wood1.use(character);
		wood2.use(character);
		
		EasyMock.verify(character);
	}

	@Test
	public void testClone() {
		
		Wood clone = (Wood) wood1.clone();
		
		// Make sure all fields match
		assertEquals(wood1.amount, clone.amount);
		assertEquals(wood1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(wood1 == clone);
		
		
		clone = (Wood) wood2.clone();
		
		// Make sure all fields match
		assertEquals(wood2.amount, clone.amount);
		assertEquals(wood2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(wood2 == clone);
	}
	
	@Test
	public void testGetWoodType() {
		assertEquals(WoodType.NORMAL, wood1.getWoodType());
		assertEquals(WoodType.ELVEN, wood2.getWoodType());
	}
}
