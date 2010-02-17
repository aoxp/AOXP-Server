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

public class ShieldTest extends AbstractDefensiveItemTest {

	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;
	
	private Shield shield1;
	private Shield shield2;
	
	@Before
	public void setUp() throws Exception {
		shield1 = new Shield(1, "Turtle Shield", 5, true, 1, 1, 0, 0, null, false, 1, MIN_DEF, MAX_DEF);
		shield2 = new Shield(1, "Turtle Shield", 1, true, 1, 1, 0, 0, null, false, 1, MAX_DEF, MAX_DEF);
		
		item = shield1;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Turtle Shield";
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
		Shield clone = (Shield) shield1.clone();
		
		// Make sure all fields match
		assertEquals(shield1.amount, clone.amount);
		assertEquals(shield1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(shield1.graphic, clone.graphic);
		assertEquals(shield1.id, clone.id);
		assertEquals(shield1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(shield1.maxDef, clone.maxDef);
		assertEquals(shield1.minDef, clone.minDef);
		assertEquals(shield1.name, clone.name);
		assertEquals(shield1.tradeable, clone.tradeable);
		assertEquals(shield1.usageDifficulty, clone.usageDifficulty);
		assertEquals(shield1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(shield1 == clone);
		
		
		clone = (Shield) shield2.clone();
		
		// Make sure all fields match
		assertEquals(shield2.amount, clone.amount);
		assertEquals(shield2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(shield2.graphic, clone.graphic);
		assertEquals(shield2.id, clone.id);
		assertEquals(shield2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(shield2.maxDef, clone.maxDef);
		assertEquals(shield2.minDef, clone.minDef);
		assertEquals(shield2.name, clone.name);
		assertEquals(shield2.tradeable, clone.tradeable);
		assertEquals(shield2.usageDifficulty, clone.usageDifficulty);
		assertEquals(shield2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(shield2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		shield1.use(character);
		shield2.use(character);
		
		EasyMock.verify(character);
	}

}
