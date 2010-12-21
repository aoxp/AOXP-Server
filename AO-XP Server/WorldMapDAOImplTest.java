package com.ao.data.dao.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.WorldMapDAO;
import com.ao.model.map.Position;
import com.ao.model.map.Tile;
import com.ao.model.map.Trigger;
import com.ao.model.map.WorldMap;

public class WorldMapDAOImplTest {
	
	private static final String MAPS_PATH = "src/test/resources/maps/";
	private static final String MAPS_CONFIG_FILE = "resources/maps.properties";
	private static final int MAPS_AMOUNT = 1;
	
	private WorldMapDAO dao;
	
	@Before
	public void setUp() throws Exception {
		dao = new WorldMapDAOImpl(MAPS_PATH, MAPS_AMOUNT, MAPS_CONFIG_FILE);
	}

	@Test
	public void testRetrieveAll() {
		WorldMap[] maps = dao.retrieveAll();
		
		assertEquals(maps.length, 1);
		
		WorldMap map = maps[0];
		
		for (int x = 1; x < 100; x++) {
			for (int y = 1; y < 100; y++) {
				Tile tile = map.getTile(x, y);
				
				if (tile.isLava() || tile.isWater()) {
//					System.out.println(x + " " + y + ": " + tile.toString());
				}
			}
		}
//		assertTrue(map.getTile(28, 58).isBlocked());
//		assertTrue(map.getTile(28, 59).isBlocked());
//		assertTrue(map.getTile(28, 60).isBlocked());
//		assertTrue(map.getTile(37, 58).isBlocked());
//		
//		assertEquals(map.getTile(2, 1).getTrigger(), Trigger.NONE);
//		assertEquals(map.getTile(20, 20).getTrigger(), Trigger.NONE);
//
//		for (int x = 28; x < 43; x++) {
//			for (int y = 41; y < 56; y++) {
//				assertTrue(map.getTile(x, y).isLava());
//			}
//		}
//		
//		for (int x = 54; x < 69; x++) {
//			for (int y = 55; y < 62; y++) {
//				assertTrue(map.getTile(x, y).isWater());
//			}
//		}
//		
//		for (int x = 54; x < 69; x++) {
//			for (int y = 63; y < 70; y++) {
//				assertTrue(map.getTile(x, y).isWater());
//			}
//		}
//		
//		for (int x = 70; x < 85; x++) {
//			for (int y = 55; y < 62; y++) {
//				assertTrue(map.getTile(x, 37).isWater());
//			}
//		}
//		
//		
//		assertTrue(map.getTile(29, 59).isUnderRoof());
//		assertTrue(map.getTile(41, 50).isUnderRoof());
//		assertTrue(map.getTile(62, 50).isUnderRoof());
//		
//		assertTrue(map.getTile(41, 59).isSafeZone());
//		assertTrue(map.getTile(46, 64).isSafeZone());
//		
//		
//		Position tileExit = new Position((byte) 50, (byte) 50, map);
//		
//		assertEquals(map.getTile(21, 44).getTileExit(), tileExit);
//		assertEquals(map.getTile(24, 46).getTileExit(), tileExit);
//		
//		// No tile exit.
//		assertNull(map.getTile(70, 70).getTileExit());
	}

}
