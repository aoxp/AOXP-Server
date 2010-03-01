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

public class AccessoryTest extends AbstractDefensiveItemTest {

	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;
	
	private static final int MIN_MAGIC_DEF = 10;
	private static final int MAX_MAGIC_DEF = 50;
	
	private Accessory accessory1;
	private Accessory accessory2;
	
	@Before
	public void setUp() throws Exception {
		accessory1 = new Accessory(1, "Gold Ring", 5, true, 1, 1, 0, 0, null, false, 1, MIN_DEF, MAX_DEF, MIN_MAGIC_DEF, MAX_MAGIC_DEF);
		accessory2 = new Accessory(1, "Diamond Ring", 1, true, 1, 1, 0, 0, null, false, 1, MAX_DEF, MAX_DEF, MAX_MAGIC_DEF, MAX_MAGIC_DEF);
		
		item = accessory1;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Gold Ring";
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
		Accessory clone = (Accessory) accessory1.clone();
		
		// Make sure all fields match
		assertEquals(accessory1.amount, clone.amount);
		assertEquals(accessory1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(accessory1.graphic, clone.graphic);
		assertEquals(accessory1.id, clone.id);
		assertEquals(accessory1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(accessory1.maxDef, clone.maxDef);
		assertEquals(accessory1.minDef, clone.minDef);
		assertEquals(accessory1.maxMagicDef, clone.maxMagicDef);
		assertEquals(accessory1.minMagicDef, clone.minMagicDef);
		assertEquals(accessory1.name, clone.name);
		assertEquals(accessory1.tradeable, clone.tradeable);
		assertEquals(accessory1.usageDifficulty, clone.usageDifficulty);
		assertEquals(accessory1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(accessory1 == clone);
		
		
		clone = (Accessory) accessory2.clone();
		
		// Make sure all fields match
		assertEquals(accessory2.amount, clone.amount);
		assertEquals(accessory2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(accessory2.graphic, clone.graphic);
		assertEquals(accessory2.id, clone.id);
		assertEquals(accessory2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(accessory2.maxDef, clone.maxDef);
		assertEquals(accessory2.minDef, clone.minDef);
		assertEquals(accessory2.maxMagicDef, clone.maxMagicDef);
		assertEquals(accessory2.minMagicDef, clone.minMagicDef);
		assertEquals(accessory2.name, clone.name);
		assertEquals(accessory2.tradeable, clone.tradeable);
		assertEquals(accessory2.usageDifficulty, clone.usageDifficulty);
		assertEquals(accessory2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(accessory2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		accessory1.use(character);
		accessory2.use(character);
		
		EasyMock.verify(character);
	}

}
