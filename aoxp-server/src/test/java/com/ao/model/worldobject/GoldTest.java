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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.ItemProperties;

public class GoldTest extends AbstractItemTest {

	private Gold gold1;
	private Gold gold2;

	@Before
	public void setUp() throws Exception {
		final ItemProperties props = new ItemProperties(WorldObjectType.MONEY, 13, "Monedas de oro", 1, 1, null, null, false, true, true, true);
		gold1 = new Gold(props, 1000);
		gold2 = new Gold(props, 2000);

		object = gold1;
		objectProps = props;
		ammount = 1000;
	}

	@Test
	public void testUse() {
		final Character character = mock(Character.class);
		final Inventory inventory = mock(Inventory.class);
		when(character.getInventory()).thenReturn(inventory);

		gold1.use(character);

		verify(inventory).cleanup();
		verify(character).addMoney(1000);
	}

	@Test
	public void testClone() {
		final Gold clone = (Gold) gold1.clone();

		assertEquals(gold1.getAmount(), clone.getAmount());
		assertNotSame(clone, gold1);

		final Gold clone2 = (Gold) gold2.clone();

		assertEquals(gold2.getAmount(), clone2.getAmount());
		assertNotSame(clone2, gold2);
	}
}
