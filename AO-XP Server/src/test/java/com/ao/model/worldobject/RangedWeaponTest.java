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

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.worldobject.properties.RangedWeaponProperties;

public class RangedWeaponTest extends AbstractEquipableItemTest {

	protected static final int MIN_HIT = 1;
	protected static final int MAX_HIT = 5;
	protected static final int PIERCING_DAMAGE = 4;

	protected RangedWeapon weapon1;
	protected RangedWeapon weapon2;
	
	@Before
	public void setUp() throws Exception {
		RangedWeaponProperties props1 = new RangedWeaponProperties(WorldObjectType.WEAPON, 1, "Throw Knifes", 1, 1, 0, null, null, false, false, false, false, 1, true, PIERCING_DAMAGE, MIN_HIT, MAX_HIT, false);
		weapon1 = new RangedWeapon(props1, 5);
		
		RangedWeaponProperties props2 = new RangedWeaponProperties(WorldObjectType.WEAPON, 1, "Crossbow", 1, 1, 0, null, null, false, false, false, false, 1, false, PIERCING_DAMAGE, MAX_HIT, MAX_HIT, true);
		weapon2 = new RangedWeapon(props2, 1);
		
		object = weapon1;
		ammount = 5;
		objectProps = props1;
		itemEquipped = false;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClone() {
		RangedWeapon clone = (RangedWeapon) weapon1.clone();
		
		// Make sure all fields match
		assertEquals(weapon1.amount, clone.amount);
		assertEquals(weapon1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(weapon1 == clone);
		
		
		clone = (RangedWeapon) weapon2.clone();
		
		// Make sure all fields match
		assertEquals(weapon2.amount, clone.amount);
		assertEquals(weapon2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(weapon2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		weapon1.use(character);
		weapon2.use(character);
		
		EasyMock.verify(character);
	}
	
	@Test
	public void testGetMinHit() {
		assertEquals(MIN_HIT, weapon1.getMinHit());
		assertEquals(MAX_HIT, weapon2.getMinHit());
	}
	
	@Test
	public void testGetMaxHit() {
		assertEquals(MAX_HIT, weapon1.getMaxHit());
		assertEquals(MAX_HIT, weapon2.getMaxHit());
	}
	
	@Test
	public void testGetPiercingDamage() {
		assertEquals(PIERCING_DAMAGE, weapon1.getPiercingDamage());
		assertEquals(PIERCING_DAMAGE, weapon2.getPiercingDamage());
	}
	
	@Test
	public void testGetStabs() {
		assertTrue(weapon1.getStabs());
		assertFalse(weapon2.getStabs());
	}
	
	@Test
	public void testGetDamage() {
		int damage = weapon1.getDamage();
		
		assertTrue(damage >= MIN_HIT);
		assertTrue(damage <= MAX_HIT);
		assertEquals(MAX_HIT, weapon2.getDamage());
	}
	
	@Test
	public void testGetNeedsAmmunition() {
		assertFalse(weapon1.getNeedsAmmunition());
		assertTrue(weapon2.getNeedsAmmunition());
	}
}
