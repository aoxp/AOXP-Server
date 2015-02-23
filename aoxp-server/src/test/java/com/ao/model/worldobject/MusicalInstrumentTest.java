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
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.worldobject.properties.MusicalInstrumentProperties;

public class MusicalInstrumentTest extends AbstractItemTest {

	private static final int ATTEMPTS = 100;

	private MusicalInstrument instrument1;
	private MusicalInstrument instrument2;

	private List<Integer> sounds1;
	private List<Integer> sounds2;

	@Before
	public void setUp() throws Exception {
		sounds1 = new LinkedList<Integer>();
		sounds1.add(1);
		sounds1.add(3);
		sounds1.add(5);

		sounds2 = new LinkedList<Integer>();
		sounds2.add(2);

		final MusicalInstrumentProperties props1 = new MusicalInstrumentProperties(WorldObjectType.MUSICAL_INSTRUMENT, 1, "Horn", 1, 1, null, null, false, false, false, false, 1, sounds1);
		instrument1 = new MusicalInstrument(props1, 5);

		final MusicalInstrumentProperties props2 = new MusicalInstrumentProperties(WorldObjectType.MUSICAL_INSTRUMENT, 1, "Drum", 1, 1, null, null, false, false, false, false, 1, sounds2);
		instrument2 = new MusicalInstrument(props2, 1);

		object = instrument1;
		objectProps = props1;
	}

	@Test
	public void testClone() {
		final MusicalInstrument clone = (MusicalInstrument) instrument1.clone();

		// Make sure all fields match
		assertEquals(instrument1.amount, clone.amount);
		assertEquals(instrument1.properties, clone.properties);

		// Make sure the object itself is different
		assertNotSame(instrument1, clone);


		final MusicalInstrument clone2 = (MusicalInstrument) instrument2.clone();

		// Make sure all fields match
		assertEquals(instrument2.amount, clone2.amount);
		assertEquals(instrument2.properties, clone2.properties);

		// Make sure the object itself is different
		assertNotSame(instrument2, clone2);
	}

	@Test
	public void testUse() {
		final Character character = mock(Character.class);

		instrument1.use(character);
		instrument2.use(character);

		// nothing should happen
		verifyZeroInteractions(character);
	}

	@Test
	public void testGetSounds() {
		for (int i = 0; i < ATTEMPTS; i++) {
			assertTrue(sounds1.contains(instrument1.getSoundToPlay()));
		}

		assertEquals(sounds2.get(0), (Integer) instrument2.getSoundToPlay());
	}
}
