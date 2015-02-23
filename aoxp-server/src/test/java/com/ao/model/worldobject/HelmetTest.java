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

package com.ao.model.worldobject;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.worldobject.properties.DefensiveItemProperties;

public class HelmetTest extends AbstractDefensiveItemTest {

	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;

	private static final int MIN_MAGIC_DEF = 10;
	private static final int MAX_MAGIC_DEF = 50;

	private Helmet helmet1;
	private Helmet helmet2;

	@Before
	public void setUp() throws Exception {
		final DefensiveItemProperties props1 = new DefensiveItemProperties(WorldObjectType.HELMET, 1, "Viking Helmet", 1, 1, 0, null, null, false, false, false, false, 1, MIN_DEF, MAX_DEF, MIN_MAGIC_DEF, MAX_MAGIC_DEF);
		helmet1 = new Helmet(props1, 5);

		final DefensiveItemProperties props2 = new DefensiveItemProperties(WorldObjectType.HELMET, 1, "Viking Helmet", 1, 1, 0, null, null, false, false, false, false, 1, MAX_DEF, MAX_DEF, MAX_MAGIC_DEF, MAX_MAGIC_DEF);
		helmet2 = new Helmet(props2, 1);

		object = helmet1;
		ammount = 5;
		objectProps = props1;
		itemEquipped = false;
	}

	@Test
	public void testClone() {
		final Helmet clone = (Helmet) helmet1.clone();

		// Make sure all fields match
		assertEquals(helmet1.amount, clone.amount);
		assertEquals(helmet1.properties, clone.properties);

		// Make sure the object itself is different
		assertNotSame(helmet1, clone);


		final Helmet clone2 = (Helmet) helmet2.clone();

		// Make sure all fields match
		assertEquals(helmet2.amount, clone2.amount);
		assertEquals(helmet2.properties, clone2.properties);

		// Make sure the object itself is different
		assertNotSame(helmet2, clone2);
	}

	@Test
	public void testUse() {
		final Character character = mock(Character.class);

		// nothing should happen
		helmet1.use(character);
		helmet2.use(character);

		verifyZeroInteractions(character);
	}

}
