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
import ao.model.worldobject.properties.ItemProperties;

public class GrabablePropTest extends AbstractItemTest {

	private GrabableProp prop1;
	private GrabableProp prop2;
	
	@Before
	public void setUp() throws Exception {
		ItemProperties props1 = new ItemProperties(WorldObjectType.GRABABLE_PROP, 1, "Black Potion", 1, true, 1, 0, null, null, false, true, false, true);
		prop1 = new GrabableProp(props1, 5);
		
		ItemProperties props2 = new ItemProperties(WorldObjectType.GRABABLE_PROP, 1, "Black Potion", 1, true, 1, 0, null, null, false, false, false, true);
		prop2 = new GrabableProp(props2, 1);
		
		object = prop2;
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
		prop1.use(character);
		prop2.use(character);
		
		EasyMock.verify(character);
	}

	@Test
	public void testClone() {
		
		GrabableProp clone = (GrabableProp) prop1.clone();
		
		// Make sure all fields match
		assertEquals(prop1.amount, clone.amount);
		assertEquals(prop1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(prop1 == clone);
		
		
		clone = (GrabableProp) prop2.clone();
		
		// Make sure all fields match
		assertEquals(prop2.amount, clone.amount);
		assertEquals(prop2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(prop2 == clone);
	}
	
}
