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

package ao.service.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junit.framework.Assert;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

import ao.model.map.Position;
import ao.model.map.Trigger;
import ao.model.map.WorldMap;

public class MapServiceImplTest {

	private MapServiceImpl service;
	private static final String MAPS_PATH = "src/test/resources/maps/";
	
	@Before
	public void setUp() {
				
		service = new MapServiceImpl(MAPS_PATH, (int) 1);

	}

	@Test
	public void testGetMap() {
		WorldMap map = EasyMock.createMock(WorldMap.class);
		service.maps.add(map);
		//Increase mapAmount because getMap checks the id.
		service.mapsAmount++;
		assertEquals(service.getMap(service.maps.lastIndexOf(map) + 1), map);
	}
	
	@Test
	public void testLoadMaps() {
		
		service.loadMaps();
		WorldMap map = service.getMap(1);
		
		Position tileExit1 = new Position((byte) 1, (byte) 1, map);
		Position tileExit2 = new Position((byte) 11, (byte) 11, service.getMap(11));
		Position tileExit3 = null;

		assertTrue(map.getTile(0, 0).isBlocked());
		Assert.assertFalse(map.getTile(1, 0).isBlocked());
		Assert.assertFalse(map.getTile(50, 50).isBlocked());

		assertEquals(map.getTile(0, 0).getTrigger(), Trigger.NONE);
		assertEquals(map.getTile(1, 0).getTrigger(), Trigger.NONE);
		assertEquals(map.getTile(49, 49).getTrigger(), Trigger.NONE);

		assertEquals(map.getTile(0, 0).getLayer((byte) 1), 6005);	
		assertEquals(map.getTile(1, 0).getLayer((byte) 3), 9434);
		assertEquals(map.getTile(49, 49).getLayer((byte) 2), 7331);
		assertEquals(map.getTile(49, 49).getLayer((byte) 4), 0);
		
		assertEquals(map.getTile(0, 0).getTileExit(), tileExit1);
		assertEquals(map.getTile(1, 0).getTileExit(), tileExit2);
		assertEquals(map.getTile(49, 49).getTileExit(), tileExit3);
	}
	
}