package ao.model.map;

import static org.junit.Assert.*;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

public class PositionTest {

	private static final byte X_POSITION = 50;
	private static final byte Y_POSITION = 50;
	
	Position pos;
	
	@Before
	public void setUp() {
		pos = new Position(X_POSITION, Y_POSITION, EasyMock.createMock(WorldMap.class));
	}
	
	@Test
	public void testAddToX() {
		pos.addToX(7);
		
		assertEquals(pos.getX(), X_POSITION + 7);
		
		pos.addToX(-6);
		
		assertEquals(pos.getX(), X_POSITION + 1);
	}

	@Test
	public void testAddToY() {
		pos.addToY(7);
		
		assertEquals(pos.getY(), Y_POSITION + 7);
		
		pos.addToY(-6);
		
		assertEquals(pos.getY(), Y_POSITION + 1);
	}

	@Test
	public void testGetDistance() {
		Position anotherPos = new Position((byte) (X_POSITION +  20), (byte) (Y_POSITION +  20), EasyMock.createMock(WorldMap.class));
		
		assertEquals(pos.getDistance(anotherPos), 40);
	}
	
	@Test
	public void testInVisionRange() {
		Position anotherPos = new Position((byte) (X_POSITION +  20), (byte) (Y_POSITION +  20), pos.getMap());
		
		assertFalse(pos.inVisionRange(anotherPos));
		
		anotherPos.setX((byte) (X_POSITION + 5));
		anotherPos.setY((byte) (Y_POSITION + 5));
		
		assertTrue(pos.inVisionRange(anotherPos));
		
		anotherPos.setMap(EasyMock.createMock(WorldMap.class));
		
		assertFalse(pos.inVisionRange(anotherPos));
	}

}
