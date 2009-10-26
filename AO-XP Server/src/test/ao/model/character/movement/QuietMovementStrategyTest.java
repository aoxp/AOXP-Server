package ao.model.character.movement;

import junit.framework.Assert;

import org.junit.Test;

import ao.model.map.Position;
import ao.model.map.Tile;
import ao.model.map.WorldMap;

public class QuietMovementStrategyTest {
	
	private MovementStrategy movement = new QuietMovementStrategy();

	@Test
	public void testMove() {
		WorldMap map = new WorldMap("foo", 0, new Tile[0]);
		
		Position pos = new Position((byte) 50, (byte) 50, map);
		Position target = new Position((byte) 60, (byte) 60, map);
		
		movement.setTarget(target);
		
		Assert.assertNull(movement.move(pos));
	}

}
