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

package com.ao.model.character.movement;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.map.Heading;
import com.ao.model.map.Position;
import com.ao.model.map.WorldMap;

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
