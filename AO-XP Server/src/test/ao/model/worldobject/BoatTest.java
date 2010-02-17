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

import static org.junit.Assert.*;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;

public class BoatTest extends AbstractDefensiveItemTest {
	
	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;

	private static final int MIN_HIT = 1;
	private static final int MAX_HIT = 5;
	
	private Boat boat1;
	private Boat boat2;
	
	@Before
	public void setUp() throws Exception {
		boat1 = new Boat(1, "Small Boat", 5, true, 1, 1, 0, 0, null, false, 1, MIN_DEF, MAX_DEF, MIN_HIT, MAX_HIT);
		boat2 = new Boat(1, "Small Boat", 1, true, 1, 1, 0, 0, null, false, 1, MAX_DEF, MAX_DEF, MAX_HIT, MAX_HIT);
		
		item = boat1;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Small Boat";
		itemValue = 1;
		itemNewbie = false;
		itemEquipped = false;
		itemEquippedGraphic = 1;
		itemMinDef = MIN_DEF;
		itemMaxDef = MAX_DEF;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClone() {
		Boat clone = (Boat) boat1.clone();
		
		// Make sure all fields match
		assertEquals(boat1.amount, clone.amount);
		assertEquals(boat1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(boat1.graphic, clone.graphic);
		assertEquals(boat1.id, clone.id);
		assertEquals(boat1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(boat1.maxDef, clone.maxDef);
		assertEquals(boat1.minDef, clone.minDef);
		assertEquals(boat1.maxHit, clone.maxHit);
		assertEquals(boat1.minHit, clone.minHit);
		assertEquals(boat1.name, clone.name);
		assertEquals(boat1.tradeable, clone.tradeable);
		assertEquals(boat1.usageDifficulty, clone.usageDifficulty);
		assertEquals(boat1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(boat1 == clone);
		
		
		clone = (Boat) boat2.clone();
		
		// Make sure all fields match
		assertEquals(boat2.amount, clone.amount);
		assertEquals(boat2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(boat2.graphic, clone.graphic);
		assertEquals(boat2.id, clone.id);
		assertEquals(boat2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(boat2.maxDef, clone.maxDef);
		assertEquals(boat2.minDef, clone.minDef);
		assertEquals(boat2.maxHit, clone.maxHit);
		assertEquals(boat2.minHit, clone.minHit);
		assertEquals(boat2.name, clone.name);
		assertEquals(boat2.tradeable, clone.tradeable);
		assertEquals(boat2.usageDifficulty, clone.usageDifficulty);
		assertEquals(boat2.value, clone.value);
		
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
}
