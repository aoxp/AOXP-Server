package com.ao.data.dao.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
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
		
		// Check for blocked / non-blocked
		assertTrue(map.getTile(0, 0).isBlocked());
		assertFalse(map.getTile(83, 28).isBlocked());
		
		// Check if tile exits are where expected
		assertNull(map.getTile(49, 49).getTileExit());
		// TODO : This tile currently references to map 275 and will be ignored!! Fix this on the map by making it a telep to another pos on map 1!
//		assertTrue(map.getTile(83, 28).getTileExit() != null);
//		assertTrue(map.getTile(83, 28).getTileExit().getX() >= WorldMap.MIN_X);
//		assertTrue(map.getTile(83, 28).getTileExit().getX() <= WorldMap.MAX_X);
//		assertTrue(map.getTile(83, 28).getTileExit().getY() >= WorldMap.MIN_Y);
//		assertTrue(map.getTile(83, 28).getTileExit().getY() <= WorldMap.MAX_Y);

		assertEquals(map.getTile(0, 0).getTrigger(), Trigger.NONE);
//		assertEquals(map.getTile(56, 59).getTrigger(), Trigger.INVALID_POSITION);
		// TODO : The map also has trigger 4, 6 and 5... test for those...
		
		assertTrue(map.getTile(71, 55).isLava());
		assertFalse(map.getTile(71, 55).isWater());

		assertFalse(map.getTile(76, 72).isLava());
		assertTrue(map.getTile(76, 72).isWater());
	}

}
