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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.worldobject.properties.BoatProperties;

public class BoatTest extends AbstractDefensiveItemTest {
	
	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;

	private static final int MIN_HIT = 1;
	private static final int MAX_HIT = 5;

	private static final int MIN_MAGIC_DEF = 10;
	private static final int MAX_MAGIC_DEF = 50;
	private static final int USAGE_DIFFICULTY = 3;
	
	private Boat boat1;
	private Boat boat2;
	
	@Before
	public void setUp() throws Exception {
		BoatProperties props1 = new BoatProperties(WorldObjectType.BOAT, 1, "Small Boat", 1, true, 1, USAGE_DIFFICULTY, 0, null, null, false, 1, MIN_DEF, MAX_DEF, MIN_MAGIC_DEF, MAX_MAGIC_DEF, MIN_HIT, MAX_HIT);
		boat1 = new Boat(props1, 5);
		
		BoatProperties props2 = new BoatProperties(WorldObjectType.BOAT, 1, "Small Boat", 1, true, 1, USAGE_DIFFICULTY, 0, null, null, false, 1, MAX_DEF, MAX_DEF, MAX_MAGIC_DEF, MAX_MAGIC_DEF, MAX_HIT, MAX_HIT);
		boat2 = new Boat(props2, 1);
		
		object = boat1;
		objectProps = props1;
		ammount = 5;
		itemEquipped = false;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClone() {
		Boat clone = (Boat) boat1.clone();
		
		// Make sure all fields match
		assertEquals(boat1.amount, clone.amount);
		assertEquals(boat1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(boat1 == clone);
		
		
		clone = (Boat) boat2.clone();
		
		// Make sure all fields match
		assertEquals(boat2.amount, clone.amount);
		assertEquals(boat2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(boat2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		boat1.use(character);
		boat2.use(character);
		
		EasyMock.verify(character);
	}
	
	@Test
	public void testGetMinHit() {
		assertEquals(MIN_HIT, boat1.getMinHit());
		assertEquals(MAX_HIT, boat2.getMinHit());
	}

	@Test
	public void testGetMaxHit() {
		assertEquals(MAX_HIT, boat1.getMaxHit());
		assertEquals(MAX_HIT, boat2.getMaxHit());
	}
	
	@Test
	public void testGetDamageBonus() {
		int damage = boat1.getDamageBonus();
		
		assertTrue(damage >= MIN_HIT);
		assertTrue(damage <= MAX_HIT);
		assertEquals(MAX_HIT, boat2.getDamageBonus());
	}
	
	@Test
	@Override
	public void testCanBeStolen() {
		assertFalse(boat1.canBeStolen());
		assertFalse(boat2.canBeStolen());
	}
	
	@Test
	public void testGetUsageDifficulty() {
		assertEquals(USAGE_DIFFICULTY, boat1.getUsageDifficulty());
		assertEquals(USAGE_DIFFICULTY, boat2.getUsageDifficulty());
	}
}
