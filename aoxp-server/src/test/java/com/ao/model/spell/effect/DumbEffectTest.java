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
import com.ao.model.character.UserCharacter;
import com.ao.model.worldobject.WorldObject;

public class DumbEffectTest {

	private DumbEffect dumbEffect;

	@Before
	public void setUp() throws Exception {
		dumbEffect = new DumbEffect();
	}

	@Test
	public void testApplyCharacterCharacter() {
		final Character target = mock(Character.class);
		final Character caster = mock(Character.class);

		dumbEffect.apply(caster, target);
	}

	@Test
	public void testAppliesToCharacterCharacter() {
		final Character target = mock(Character.class);
		final Character caster = mock(Character.class);

		assertFalse(dumbEffect.appliesTo(caster, target));

		final Character deadUserTarget = mock(UserCharacter.class);
		when(deadUserTarget.isDead()).thenReturn(Boolean.TRUE);
		final Character aliveUserTarget = mock(UserCharacter.class);

		assertTrue(dumbEffect.appliesTo(caster, aliveUserTarget));
		assertFalse(dumbEffect.appliesTo(caster, deadUserTarget));
	}

	@Test
	public void testAppliesToCharacterWorldObject() {
		final Character caster = mock(Character.class);
		final WorldObject target = mock(WorldObject.class);

		assertFalse(dumbEffect.appliesTo(caster, target));
	}

	@Test
	public void testApplyCharacterWorldObject() {
		final WorldObject obj = mock(WorldObject.class);
		final Character caster = mock(Character.class);

		// Should do nothing....
		try {
			dumbEffect.apply(caster, obj);
			fail("Applying an effect for characters to a world object didn't fail.");
		} catch (InvalidTargetException e) {
			// this is ok
		}
	}

}
