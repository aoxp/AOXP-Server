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

import org.junit.Before;
import org.junit.Test;

import ao.model.worldobject.properties.KeyProperties;

public class KeyTest extends AbstractWorldObjectTest {

	private static final int CODE = 123;
	
	private Key key1;
	
	@Before
	public void setUp() throws Exception {
		KeyProperties props1 = new KeyProperties(WorldObjectType.KEY, 1, "Llave maestra", 1, 1, 0, null, null, false, false, false, false, CODE);
		key1 = new Key(props1, 1);
		
		object = key1;
		objectProps = props1;
	}
	
	@Test
	public void testClone() {
		Key clone = (Key) key1.clone();
		
		// Make sure all fields match
		assertEquals(key1.amount, clone.amount);
		assertEquals(key1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(key1 == clone);

	}
	
	@Test
	public void testGetMinHit() {
		assertEquals(CODE, key1.getCode());
	}
	
}
