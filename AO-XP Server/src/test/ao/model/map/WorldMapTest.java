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

package ao.model.map;

import static org.junit.Assert.*;

import java.util.List;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

public class WorldMapTest {

	private WorldMap map;
	
	@Before
	public void setUp() {
		map = new WorldMap("foo", 1, new Tile[] {});
	}
	
	@Test
	public void testGetTile() {
		short[] layers = new short[Tile.LAYERS]; 
		Tile t = new Tile(true, layers, (byte) 0, null, null, null);
		Tile t2 = new Tile(false, layers, (byte) 0, null, null, null);
		Tile t3 = new Tile(true, layers, (byte) 0, null, null, null);
		
		map = new WorldMap("foo", 1, new Tile[] {t, t2, t3});
		
		assertTrue(map.getTile(0, 0) == t);
		assertTrue(map.getTile(1, 0) == t2);
		assertTrue(map.getTile(2, 0) == t3);
	}

	@Test
	public void testGetName() {
		assertEquals("foo", map.getName());
	}

	@Test
	public void testGetId() {
		assertEquals(map.getId(), 1);
	}
	
	@Test
	public void testGetCharactersNearby() {
		Tile[] tiles = new Tile[2600];
		short [] layers = new short[Tile.LAYERS - 1];
		for (int i = 0; i < 2600; i++) {
			tiles[i] = new Tile(true, layers, (byte) 0, null, null, null);
		}
		
		map = new WorldMap("foo", 1, tiles);
		
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
