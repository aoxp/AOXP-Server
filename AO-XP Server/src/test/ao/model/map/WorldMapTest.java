package ao.model.map;

import static org.junit.Assert.*;

import java.util.List;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import ao.model.character.Character;

public class WorldMapTest {

	private WorldMap map;
	
	@Before
	public void setUp() {
		map = new WorldMap("foo", 1, new Tile[] {});
	}
	
	@Test
	public void testGetTile() {
		Tile t = new Tile();
		Tile t2 = new Tile();
		Tile t3 = new Tile();
		
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
		
		for (int i = 0; i < 2600; i++) {
			tiles[i] = new Tile();
		}
		
		map = new WorldMap("foo", 1, tiles);
		
		map.getTile(8, 8).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(15, 8).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(8, 15).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(15, 15).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(5, 5).setCharacter(EasyMock.createMock(Character.class));
		map.getTile(25, 5).setCharacter(EasyMock.createMock(Character.class)); // This one shouldn't be in the return.
		
		List chars = map.getCharactersNearby(7, 7);
		
		assertEquals(chars.size(), 5);
		assertTrue(chars.contains(map.getTile(8, 8).getCharacter()));
		assertTrue(chars.contains(map.getTile(15, 8).getCharacter()));
		assertTrue(chars.contains(map.getTile(8, 15).getCharacter()));
		assertTrue(chars.contains(map.getTile(15, 15).getCharacter()));
		assertTrue(chars.contains(map.getTile(5, 5).getCharacter()));
	}

}
