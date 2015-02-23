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
		final WorldMap[] maps = dao.retrieveAll();
		final WorldMap map = maps[0];

		// Check for blocked / non-blocked
		assertTrue(map.getTile(0, 0).isBlocked());
		assertFalse(map.getTile(83, 28).isBlocked());

		// Check if tile exits are where expected
		assertNull(map.getTile(49, 49).getTileExit());

		final Position tileExit = map.getTile(19, 84).getTileExit();
		assertTrue(tileExit != null);
		assertTrue(tileExit.getX() >= WorldMap.MIN_X);
		assertTrue(tileExit.getX() <= WorldMap.MAX_X);
		assertTrue(tileExit.getY() >= WorldMap.MIN_Y);
		assertTrue(tileExit.getY() <= WorldMap.MAX_Y);

		assertEquals(map.getTile(0, 0).getTrigger(), Trigger.NONE);
		assertEquals(map.getTile(23, 32).getTrigger(), Trigger.UNDER_ROOF);
		assertEquals(map.getTile(57, 44).getTrigger(), Trigger.INVALID_POSITION);
		assertEquals(map.getTile(40, 74).getTrigger(), Trigger.SAFE_ZONE);
		assertEquals(map.getTile(28, 20).getTrigger(), Trigger.ANTI_PICKET);
		assertEquals(map.getTile(23, 33).getTrigger(), Trigger.FIGHT_ZONE);

		assertTrue(map.getTile(71, 55).isLava());
		assertFalse(map.getTile(71, 55).isWater());

		assertFalse(map.getTile(76, 72).isLava());
		assertTrue(map.getTile(76, 72).isWater());
	}

}
