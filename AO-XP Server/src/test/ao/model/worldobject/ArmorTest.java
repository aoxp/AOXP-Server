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

public class ArmorTest extends AbstractDefensiveItemTest {

	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;
	
	private static final int MIN_MAGIC_DEF = 10;
	private static final int MAX_MAGIC_DEF = 50;
	
	private Armor armor1;
	private Armor armor2;
	
	@Before
	public void setUp() throws Exception {
		armor1 = new Armor(1, "Leather Armor", 5, true, 1, 1, 0, 0, null, false, 1, MIN_DEF, MAX_DEF, MIN_MAGIC_DEF, MAX_MAGIC_DEF);
		armor2 = new Armor(1, "Leather Armor", 1, true, 1, 1, 0, 0, null, false, 1, MAX_DEF, MAX_DEF, MAX_MAGIC_DEF, MAX_MAGIC_DEF);
		
		item = armor1;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Leather Armor";
		itemValue = 1;
		itemNewbie = false;
		itemEquipped = false;
		itemEquippedGraphic = 1;
		itemMinDef = MIN_DEF;
		itemMaxDef = MAX_DEF;
		itemMinMagicDef = MIN_MAGIC_DEF;
		itemMaxMagicDef = MAX_MAGIC_DEF;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClone() {
		Armor clone = (Armor) armor1.clone();
		
		// Make sure all fields match
		assertEquals(armor1.amount, clone.amount);
		assertEquals(armor1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(armor1.graphic, clone.graphic);
		assertEquals(armor1.id, clone.id);
		assertEquals(armor1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(armor1.maxDef, clone.maxDef);
		assertEquals(armor1.minDef, clone.minDef);
		assertEquals(armor1.maxMagicDef, clone.maxMagicDef);
		assertEquals(armor1.minMagicDef, clone.minMagicDef);
		assertEquals(armor1.name, clone.name);
		assertEquals(armor1.tradeable, clone.tradeable);
		assertEquals(armor1.usageDifficulty, clone.usageDifficulty);
		assertEquals(armor1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(armor1 == clone);
		
		
		clone = (Armor) armor2.clone();
		
		// Make sure all fields match
		assertEquals(armor2.amount, clone.amount);
		assertEquals(armor2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(armor2.graphic, clone.graphic);
		assertEquals(armor2.id, clone.id);
		assertEquals(armor2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(armor2.maxDef, clone.maxDef);
		assertEquals(armor2.minDef, clone.minDef);
		assertEquals(armor2.maxMagicDef, clone.maxMagicDef);
		assertEquals(armor2.minMagicDef, clone.minMagicDef);
		assertEquals(armor2.name, clone.name);
		assertEquals(armor2.tradeable, clone.tradeable);
		assertEquals(armor2.usageDifficulty, clone.usageDifficulty);
		assertEquals(armor2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(armor2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		armor1.use(character);
		armor2.use(character);
		
		EasyMock.verify(character);
	}

}
