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

package com.ao.model.map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;

public class WorldMapTest {

	private WorldMap map;

	@Before
	public void setUp() {
		map = new WorldMap("foo", 1, (short) 1, new Tile[] {});
	}

	@Test
	public void testGetTile() {
		Tile t = new Tile(true, true, false, Trigger.NONE, null, null, null);
		Tile t2 = new Tile(false, true, false, Trigger.NONE, null, null, null);
		Tile t3 = new Tile(true, true, false, Trigger.NONE, null, null, null);

		map = new WorldMap("foo", 1, (short) 1, new Tile[] {t, t2, t3});

		assertEquals(map.getTile(0, 0), t);
		assertEquals(map.getTile(1, 0), t2);
		assertEquals(map.getTile(2, 0), t3);
	}

	@Test
	public void testEquals() {
		WorldMap map2 = new WorldMap("foo", 1, (short) 1, new Tile[] {});
		WorldMap map3 = new WorldMap("foo", 2, (short) 1, new Tile[] {});
		WorldMap map4 = new WorldMap("asd", 1, (short) 1, new Tile[] {});
		WorldMap map5 = new WorldMap("asd", 3, (short) 1, new Tile[] {null});
		WorldMap map6 = new WorldMap(null, 4, (short) 1, new Tile[] {});

		assertTrue(map.equals(map));
		assertTrue(map.equals(map2));
		assertFalse(map.equals(map3));
		assertFalse(map.equals(map4));
		assertFalse(map.equals(map5));
		assertFalse(map6.equals(map));
		assertFalse(map.equals(null));
		assertFalse(map.equals(new String()));
	}

	@Test
	public void testGetName() {
		assertEquals("foo", map.getName());
	}

	@Test
	public void testGetId() {
		assertEquals(1, map.getId());
	}

	@Test
	public void testGetCharactersNearby() {
		Tile[] tiles = new Tile[2600];
		for (int i = 0; i < 2600; i++) {
			tiles[i] = new Tile(true, true, false, Trigger.NONE, null, null, null);
		}

		map = new WorldMap("foo", 1, (short) 1, tiles);

		map.getTile(8, 8).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(15, 8).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(8, 15).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(15, 15).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(5, 5).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(25, 5).setCharacter(EasyMock.createMock(Character.class)); // This one shouldn't be in the return.

		List<Character> chars = map.getCharactersNearby(7, 7);

		assertEquals(chars.size(), 5);
		assertTrue(chars.contains(map.getTile(8, 8).getCharacter()));
		assertTrue(chars.contains(map.getTile(15, 8).getCharacter()));
		assertTrue(chars.contains(map.getTile(8, 15).getCharacter()));
		assertTrue(chars.contains(map.getTile(15, 15).getCharacter()));
		assertTrue(chars.contains(map.getTile(5, 5).getCharacter()));
	}

}
