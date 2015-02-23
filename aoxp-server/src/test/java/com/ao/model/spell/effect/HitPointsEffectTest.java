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

package com.ao.model.spell.effect;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ao.exception.InvalidTargetException;
import com.ao.model.character.Character;
import com.ao.model.worldobject.WorldObject;

public class HitPointsEffectTest {

	private static final int MIN_POINTS = 5;
	private static final int MAX_POINTS = 10;

	private HitPointsEffect hpEffect1;
	private HitPointsEffect hpEffect2;

	@Before
	public void setUp() throws Exception {
		hpEffect1 = new HitPointsEffect(MIN_POINTS, MAX_POINTS);
		hpEffect2 = new HitPointsEffect(-MAX_POINTS, -MIN_POINTS);
	}

	@Test
	public void testAppliesToCharacterCharacter() {
		final Character deadTarget = mock(Character.class);
		when(deadTarget.isDead()).thenReturn(Boolean.TRUE);
		final Character aliveTarget = mock(Character.class);
		final Character caster = mock(Character.class);

		assertTrue(hpEffect1.appliesTo(caster, aliveTarget));
		assertTrue(hpEffect2.appliesTo(caster, aliveTarget));
		assertFalse(hpEffect1.appliesTo(caster, deadTarget));
		assertFalse(hpEffect2.appliesTo(caster, deadTarget));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		final Character caster = mock(Character.class);
		final WorldObject target = mock(WorldObject.class);

		assertFalse(hpEffect1.appliesTo(caster, target));
		assertFalse(hpEffect2.appliesTo(caster, target));
	}

	@Test
	public void testApplyCharacterWorldObject() {
		final WorldObject obj = mock(WorldObject.class);
		final Character caster = mock(Character.class);

		// Should do nothing....
		try {
			hpEffect1.apply(caster, obj);
			fail("Applying an effect for characters to a world object didn't fail.");
		} catch (final InvalidTargetException e) {
			// this is ok
		}
	}

}
