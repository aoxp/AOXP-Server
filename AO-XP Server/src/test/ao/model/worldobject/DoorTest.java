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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ao.model.worldobject.properties.DoorProperties;

public class DoorTest extends AbstractWorldObjectTest {

	private static final boolean OPEN = true;
	private static final boolean LOCKED = false;
	private static final int CODE = 123;
	private static final int OPEN_GRH = 50;
	private static final int CLOSED_GRH = 51;
	
	private Door door1;
	
	@Before
	public void setUp() throws Exception {
		DoorProperties props1 = new DoorProperties(WorldObjectType.DOOR, 1, "Puerta abierta", 1,
				OPEN, LOCKED, CODE, OPEN_GRH, CLOSED_GRH);
		door1 = new Door(props1);
		
		object = door1;
		objectProps = props1;
	}
	
	@Test
	public void testGetOpen() {
		Assert.assertTrue(door1.getOpen());
	}
	
	@Test
	public void testGetLocked() {
		Assert.assertFalse(door1.getLocked());
	}
	
	@Test
	public void testGetCode() {
		assertEquals(CODE, door1.getCode());
	}
	
	@Test
	public void testGetOpenGrh() {
		assertEquals(OPEN_GRH, door1.getOpenGrh());
	}
	
	@Test
	public void testGetClosedGrh() {
		assertEquals(CLOSED_GRH, door1.getClosedGrh());
	}
	
}