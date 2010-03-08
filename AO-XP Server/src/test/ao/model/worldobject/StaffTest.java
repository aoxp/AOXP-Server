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

package ao.model.worldobject;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.worldobject.properties.StaffProperties;

public class StaffTest extends WeaponTest {

	private static final int MIN_HIT = 1;
	private static final int MAX_HIT = 5;
	private static final int PIERCING_DAMAGE = 4;
	private static final int MAGIC_POWER = 40;
	private static final int DAMAGE_BONUS = 20;
	
	@Before
	public void setUp() throws Exception {
		StaffProperties props1 = new StaffProperties(1, "Walnut Rod", 1, true, 1, 0, null, false, 1, true, PIERCING_DAMAGE, MIN_HIT, MAX_HIT, MAGIC_POWER, DAMAGE_BONUS);
		weapon1 = new Staff(props1, 5);
		
		StaffProperties props2 = new StaffProperties(1, "Plum Rod", 1, true, 1, 0, null, false, 1, false, PIERCING_DAMAGE, MAX_HIT, MAX_HIT, MAGIC_POWER, DAMAGE_BONUS);
		weapon2 = new Staff(props2, 1);
		
		object = weapon1;
		ammount = 5;
		objectProps = props1;
		itemEquipped = false;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testGetDamageBonus() {
		assertEquals(DAMAGE_BONUS, ((Staff) weapon1).getDamageBonus());
		assertEquals(DAMAGE_BONUS, ((Staff) weapon2).getDamageBonus());
	}
	
	@Test
	public void testGetMagicPower() {
		assertEquals(MAGIC_POWER, ((Staff) weapon1).getMagicPower());
		assertEquals(MAGIC_POWER, ((Staff) weapon2).getMagicPower());
	}
}
