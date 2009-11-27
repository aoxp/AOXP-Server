package ao.model.character.movement;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.map.Position;
import ao.model.map.WorldMap;

public class GreedyMovementStrategyTest {

	private MovementStrategy movement = new GreedyMovementStrategy();

	@Test
	public void testMove() {
		WorldMap map = new WorldMap("foo", 0, null);
		
		Position pos = new Position((byte) 50, (byte) 50, map);
		Position target = new Position((byte) 60, (byte) 60, map);
		
		// Should go to northeast
		moveTest(pos, target, Heading.WEST, Heading.SOUTH);
		moveTestCharacter(pos, target, Heading.WEST, Heading.SOUTH);
		
		target.setY((byte) 20);
		
		// Should go to southeast
		moveTest(pos, target, Heading.WEST, Heading.NORTH);
		moveTestCharacter(pos, target, Heading.WEST, Heading.NORTH);
		
		target.setX((byte) 20);
		
		// Should go to southwest
		moveTest(pos, target, Heading.EAST, Heading.NORTH);
		moveTestCharacter(pos, target, Heading.EAST, Heading.NORTH);
		
		target.setY((byte) 60);
		
		// Should go to northwest
		moveTest(pos, target, Heading.EAST, Heading.SOUTH);
		moveTestCharacter(pos, target, Heading.EAST, Heading.SOUTH);
	}

	private void moveTest(Position pos, Position target, Heading shouldnt1, Heading shouldnt2) {
		movement.setTarget(target);
		
		_moveTest(pos, target, shouldnt1, shouldnt2);
	}
	
	private void moveTestCharacter(Position pos, Position target, Heading shouldnt1, Heading shouldnt2) {
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getPosition()).andReturn(target).anyTimes();
		EasyMock.replay(character);
		
		movement.setTarget(character);
		
		_moveTest(pos, target, shouldnt1, shouldnt2);
	}
	
	private void _moveTest(Position pos, Position target, Heading shouldnt1, Heading shouldnt2) {
		Heading move = null;
		
		// Save this values because they will change and we don't want to modify the original object.
		byte x = pos.getX();
		byte y = pos.getY();
		
		int steps = pos.getDistance(target);
		
		for (int i = 0; i < steps; i++) {
			move = movement.move(pos);
			movePosition(pos, move);
			
			Assert.assertNotNull(move);
			Assert.assertNotSame(shouldnt1, move);
			Assert.assertNotSame(shouldnt2, move);
		}
		
		// Has arrived to target.
		Assert.assertEquals(target.getX(), pos.getX());
		Assert.assertEquals(target.getY(), pos.getY());
		Assert.assertNull(movement.move(pos));
		
		pos.setX(x);
		pos.setY(y);
	}
	
	private void movePosition(Position pos, Heading direction) {
		switch (direction) {
		case NORTH:
			pos.setY((byte) (pos.getY() + 1));
			break;
			
		case SOUTH:
			pos.setY((byte) (pos.getY() - 1));
			break;
			
		case EAST:
			pos.setX((byte) (pos.getX() + 1));
			break;
			
		case WEST:
			pos.setX((byte) (pos.getX() - 1));
			break;
		}
	}
	
}
