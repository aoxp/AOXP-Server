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

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;

import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.map.Position;

public class QuietMovementStrategyTest {

	private MovementStrategy movement = new QuietMovementStrategy();

	@Test
	public void testMove() {
		final Position pos = new Position((byte) 50, (byte) 50, 1);
		final Position target = new Position((byte) 60, (byte) 60, 1);

		movement.setTarget(target);

		assertNull(movement.move(pos));

		final Character character = mock(Character.class);

		movement.setTarget(character);

		assertNull(movement.move(pos));
	}

}
