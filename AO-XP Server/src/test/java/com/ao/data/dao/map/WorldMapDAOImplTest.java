package com.ao.data.dao.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.WorldMapDAO;
import com.ao.model.map.Position;
import com.ao.model.map.Trigger;
import com.ao.model.map.WorldMap;

public class WorldMapDAOImplTest {
	
	private WorldMapDAO dao;
	private static final String MAPS_PATH = "src/test/resources/maps/";
	private static final String MAPS_CONFIG_FILE = "resources/maps.properties";
	
	@Before
	public void setUp() {
		dao = new WorldMapDAOImpl(MAPS_PATH, 1, MAPS_CONFIG_FILE);
	}

	@Test
	public void testLoadMaps() {
		
		WorldMap[] maps = dao.retrieveAll();
		WorldMap map = maps[0];
		
		Position tileExit1 = new Position((byte) 1, (byte) 1, map);
		Position tileExit2 = new Position((byte) 11, (byte) 11, new WorldMap(11));
		Position tileExit3 = null;

		assertTrue(map.getTile(0, 0).isBlocked());
		assertFalse(map.getTile(1, 0).isBlocked());
		assertFalse(map.getTile(50, 50).isBlocked());

		assertEquals(map.getTile(0, 0).getTrigger(), Trigger.NONE);
		assertEquals(map.getTile(1, 0).getTrigger(), Trigger.NONE);
		assertEquals(map.getTile(49, 49).getTrigger(), Trigger.NONE);

		// TODO: Test for water, lava, under roof and safe zone.
		
		assertEquals(map.getTile(0, 0).getTileExit(), tileExit1);
		assertEquals(map.getTile(1, 0).getTileExit(), tileExit2);
		assertEquals(map.getTile(49, 49).getTileExit(), tileExit3);
	}

}
