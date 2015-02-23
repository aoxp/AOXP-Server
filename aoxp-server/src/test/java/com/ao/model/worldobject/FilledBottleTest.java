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

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.RefillableStatModifyingItemProperties;

public class FilledBottleTest extends AbstractItemTest {

	private static final int THIRST = 5;

	private FilledBottle bottle1;
	private FilledBottle bottle2;


	@Before
	public void setUp() throws Exception {
		final RefillableStatModifyingItemProperties emptyProps = new RefillableStatModifyingItemProperties(WorldObjectType.EMPTY_BOTTLE, 1, "Water Bottle", 1, 1, null, null, false, false, false, false, 0, 0, false, null);
		final RefillableStatModifyingItemProperties props = new RefillableStatModifyingItemProperties(WorldObjectType.FILLED_BOTTLE, 1, "Water Bottle", 1, 1, null, null, false,false, false, false, THIRST, THIRST, true, emptyProps);
		bottle1 = new FilledBottle(props, 5);

		bottle2 = new FilledBottle(props, 1);

		object = bottle2;
		ammount = 1;
		objectProps = props;
	}

	@Test
	public void testUseWithCleanup() {
		final Inventory inventory = mock(Inventory.class);
		final Character character = mock(Character.class);
		when(character.getInventory()).thenReturn(inventory);

		final ArgumentCaptor<Item> addedItem = ArgumentCaptor.forClass(Item.class);
		when(inventory.addItem(addedItem.capture())).thenReturn(1);

		bottle2.use(character);

		// Consumption of bottle2 requires these 2 calls.
		verify(inventory).cleanup();
		verify(character).addToThirstiness(THIRST);

		assertThat(addedItem.getValue(), instanceOf(EmptyBottle.class));
		final EmptyBottle emptyBottle = (EmptyBottle) addedItem.getValue();
		assertEquals(((RefillableStatModifyingItemProperties) bottle2.properties).getOtherStateProperties(), emptyBottle.properties);
		assertEquals(1, emptyBottle.amount);
	}

	@Test
	public void testUseWithoutCleanup() {
		final Inventory inventory = mock(Inventory.class);
		final ArgumentCaptor<Item> addedItem = ArgumentCaptor.forClass(Item.class);
		when(inventory.addItem(addedItem.capture())).thenReturn(1);
		final Character character = mock(Character.class);
		when(character.getInventory()).thenReturn(inventory);

		bottle1.use(character);

		// Consumption of bottle1 requires just a call to addToThirstiness.
		verify(character).addToThirstiness(THIRST);

		assertThat(addedItem.getValue(), instanceOf(EmptyBottle.class));
		final EmptyBottle emptyBottle = (EmptyBottle) addedItem.getValue();
		assertEquals(((RefillableStatModifyingItemProperties) bottle1.properties).getOtherStateProperties(), emptyBottle.properties);
		assertEquals(1, emptyBottle.amount);
	}

	@Test
	public void testClone() {
		final FilledBottle clone = (FilledBottle) bottle1.clone();

		// Make sure all fields match
		assertEquals(bottle1.amount, clone.amount);
		assertEquals(bottle1.properties, clone.properties);

		// Make sure the object itself is different
		assertNotSame(bottle1, clone);


		final FilledBottle clone2 = (FilledBottle) bottle2.clone();

		// Make sure all fields match
		assertEquals(bottle2.amount, clone2.amount);
		assertEquals(bottle2.properties, clone2.properties);

		// Make sure the object itself is different
		assertNotSame(bottle2, clone2);
	}

	@Test
	public void testGetThirst() {
		assertEquals(THIRST, bottle1.getThirst());
		assertEquals(THIRST, bottle2.getThirst());
	}
}
