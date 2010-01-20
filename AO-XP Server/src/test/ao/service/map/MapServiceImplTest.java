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

import ao.model.character.Character;
import ao.model.character.NPCCharacter;
import ao.model.map.Position;
import ao.model.map.Tile;
import ao.model.map.Trigger;
import ao.model.map.WorldMap;
import ao.model.worldobject.WorldObject;
import ao.service.MapService;

public class MapServiceImplTest {

	private WorldMap map;
	private MapService service;
	private static final String MAPS_PATH = "src/test/resources/maps/";
	
	@Before
	public void setUp() throws Exception {
				
		service = new MapServiceImpl(MAPS_PATH, (int) 1);
		
		service.loadMaps();
		
		map = service.getMap(1); 
	}
	
	@Test
	public void testGetMap() {
		WorldObject worldObject = EasyMock.createMock(WorldObject.class);
		NPCCharacter npc = EasyMock.createMock(NPCCharacter.class);
		Position tileExit = new Position((byte) 1, (byte) 1, service.getMap(9));
		Tile tile = service.getMap(1).getTile(75, 75);
		
		tile.setBlocked(true);
		tile.setLayer((short)1111, (byte) 1);
		tile.setLayer((short)2222, (byte) 2);
		tile.setLayer((short)3333, (byte) 3);
		tile.setLayer((short)4444, (byte) 4);
		tile.setTrigger(Trigger.get((short) 0));
		tile.setTileExit(tileExit);
		tile.setWorldObject(worldObject);
		tile.setNPC(npc);

		Assert.assertEquals(tile.getLayer((byte) 1), 1111);
		Assert.assertEquals(tile.getLayer((byte) 2), 2222);
		Assert.assertEquals(tile.getLayer((byte) 3), 3333);
		Assert.assertEquals(tile.getLayer((byte) 4), 4444);
		Assert.assertEquals(tile.getTrigger(),Trigger.get((short) 0)); 
		Assert.assertEquals(tile.getTileExit(), tileExit);
		Assert.assertEquals(tile.getWorldObject(), worldObject);
		Assert.assertEquals(tile.getNPC(), npc);
	}

	@Test
	public void testBlockedTile() {
		assertTrue(map.getTile(0, 0).isBlocked());
		Assert.assertFalse(map.getTile(1, 0).isBlocked());
		Assert.assertFalse(map.getTile(50, 50).isBlocked());
	}

	@Test
	public void testTriggerTile() {
		assertEquals(map.getTile(0, 0).getTrigger(), Trigger.get((short) 0));
		assertEquals(map.getTile(1, 0).getTrigger(), Trigger.get((short) 0));
		assertEquals(map.getTile(49, 49).getTrigger(), Trigger.get((short) 0));
	}

	@Test
	public void testLayersTile() {
		assertEquals(map.getTile(0, 0).getLayer((byte) 1), 6005);	
		assertEquals(map.getTile(1, 0).getLayer((byte) 3), 9434);
		assertEquals(map.getTile(49, 49).getLayer((byte) 2), 7331);
		assertEquals(map.getTile(49, 49).getLayer((byte) 4), 0);
	}

	@Test
	public void testNPCTile() {
		NPCCharacter npc1 = EasyMock.createMock(NPCCharacter.class);
		NPCCharacter npc2 = EasyMock.createMock(NPCCharacter.class);
		NPCCharacter npc3 = EasyMock.createMock(NPCCharacter.class);
		
		map.getTile(0, 0).setNPC(npc1);
		map.getTile(1, 1).setNPC(npc2);
		map.getTile(49, 49).setNPC(npc3);
		 
		Assert.assertEquals(map.getTile(0, 0).getNPC(), npc1);
		Assert.assertEquals(map.getTile(1, 1).getNPC(), npc2);
		Assert.assertEquals(map.getTile(49 , 49).getNPC(), npc3);
 	}

	@Test
	public void testExitTile() {
		Position tileExit1 = new Position((byte) 1, (byte) 1, service.getMap(1));
		Position tileExit2 = new Position((byte) 11, (byte) 11, service.getMap(11));
		Position tileExit3 = null;
		Assert.assertEquals(map.getTile(0, 0).getTileExit(), tileExit1);
		Assert.assertEquals(map.getTile(1, 0).getTileExit(), tileExit2);
		Assert.assertEquals(map.getTile(49, 49).getTileExit(), tileExit3);
	}
	
}