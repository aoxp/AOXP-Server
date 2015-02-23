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
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.worldobject.properties.AmmunitionProperties;

public class AmmunitionTest extends AbstractEquipableItemTest {

	protected static final int MIN_HIT = 1;
	protected static final int MAX_HIT = 5;

	protected Ammunition ammunition1;
	protected Ammunition ammunition2;

	@Before
	public void setUp() throws Exception {
		final AmmunitionProperties props1 = new AmmunitionProperties(WorldObjectType.AMMUNITION, 1, "Arrow", 1, 1, null, null, false, false, false, false, 1, MIN_HIT, MAX_HIT);
		ammunition1 = new Ammunition(props1, 5);

		final AmmunitionProperties props2 = new AmmunitionProperties(WorldObjectType.AMMUNITION, 1, "Fire Arrow", 1, 1, null, null, false, false, false, false, 1, MAX_HIT, MAX_HIT);
		ammunition2 = new Ammunition(props2, 1);

		object = ammunition1;
		ammount = 5;
		objectProps = props1;
		itemEquipped = false;
	}

	@Test
	public void testClone() {
		final Ammunition clone = (Ammunition) ammunition1.clone();

		// Make sure all fields match
		assertEquals(ammunition1.amount, clone.amount);
		assertEquals(ammunition1.properties, clone.properties);

		// Make sure the object itself is different
		assertNotSame(ammunition1, clone);


		final Ammunition clone2 = (Ammunition) ammunition2.clone();

		// Make sure all fields match
		assertEquals(ammunition2.amount, clone2.amount);
		assertEquals(ammunition2.properties, clone2.properties);

		// Make sure the object itself is different
		assertNotSame(ammunition2, clone2);
	}

	@Test
	public void testUse() {
		final Character character = mock(Character.class);

		// nothing should happen
		ammunition1.use(character);
		ammunition2.use(character);
	}

	@Test
	public void testGetMinHit() {
		assertEquals(MIN_HIT, ammunition1.getMinHit());
		assertEquals(MAX_HIT, ammunition2.getMinHit());
	}

	@Test
	public void testGetMaxHit() {
		assertEquals(MAX_HIT, ammunition1.getMaxHit());
		assertEquals(MAX_HIT, ammunition2.getMaxHit());
	}

	@Test
	public void testGetDamage() {
		final int damage = ammunition1.getDamage();

		assertThat(damage, greaterThanOrEqualTo(MIN_HIT));
		assertThat(damage, lessThanOrEqualTo(MAX_HIT));
		assertEquals(MAX_HIT, ammunition2.getDamage());
	}
}
