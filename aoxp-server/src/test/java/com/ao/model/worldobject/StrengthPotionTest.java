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

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.lessThanOrEqualTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.TemporalStatModifyingItemProperties;

public class StrengthPotionTest extends AbstractItemTest {

	private static final int MIN_STR = 1;
	private static final int MAX_STR = 5;
	private static final int DURATION = 300;

	private StrengthPotion potion1;
	private StrengthPotion potion2;

	@Before
	public void setUp() throws Exception {
		final TemporalStatModifyingItemProperties props1 = new TemporalStatModifyingItemProperties(WorldObjectType.STRENGTH_POTION, 1, "Green Potion", 1, 1, null, null, false, false, false, false, MIN_STR, MAX_STR, DURATION);
		potion1 = new StrengthPotion(props1, 5);

		final TemporalStatModifyingItemProperties props2 = new TemporalStatModifyingItemProperties(WorldObjectType.STRENGTH_POTION, 1, "Big Green Potion", 1, 1, null, null, false, false, false, false, MAX_STR, MAX_STR, DURATION);
		potion2 = new StrengthPotion(props2, 1);

		object = potion2;
		ammount = 1;
		objectProps = props2;
	}

	@Test
	public void testUseWithCleanup() {
		final Inventory inventory = mock(Inventory.class);
		final Character character = mock(Character.class);
		when(character.getInventory()).thenReturn(inventory);

		potion2.use(character);

		// Consumption of potion2 requires these 2 calls.
		verify(inventory).cleanup();
		verify(character).addToStrength(MAX_STR, DURATION);
	}

	@Test
	public void testUseWithoutCleanup() {
		final Inventory inventory = mock(Inventory.class);
		final Character character = mock(Character.class);
		when(character.getInventory()).thenReturn(inventory);

		potion1.use(character);

		// Consumption of potion1 requires just a call to addToStrength.
		final ArgumentCaptor<Integer> capture = ArgumentCaptor.forClass(Integer.class);
		verify(character).addToStrength(capture.capture(), eq(DURATION));

		/// Make sure the value is in the correct range
		assertThat(capture.getValue(), greaterThanOrEqualTo(MIN_STR));
		assertThat(capture.getValue(), lessThanOrEqualTo(MAX_STR));
	}

	@Test
	public void testGetMinModifier() {
		assertEquals(MIN_STR, potion1.getMinModifier());
		assertEquals(MAX_STR, potion2.getMinModifier());
	}

	@Test
	public void testGetMaxModifier() {
		assertEquals(MAX_STR, potion1.getMaxModifier());
		assertEquals(MAX_STR, potion2.getMaxModifier());
	}

	@Test
	public void testClone() {
		final StrengthPotion clone = (StrengthPotion) potion1.clone();

		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.properties, clone.properties);

		// Make sure the object itself is different
		assertNotSame(potion1, clone);


		final StrengthPotion clone2 = (StrengthPotion) potion2.clone();

		// Make sure all fields match
		assertEquals(potion2.amount, clone2.amount);
		assertEquals(potion2.properties, clone2.properties);

		// Make sure the object itself is different
		assertNotSame(potion2, clone2);
	}

	@Test
	public void testGetEffectDuration() {
		assertEquals(DURATION, potion1.getEffectDuration());
		assertEquals(DURATION, potion2.getEffectDuration());
	}
}
