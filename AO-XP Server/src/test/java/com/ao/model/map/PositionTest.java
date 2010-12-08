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
