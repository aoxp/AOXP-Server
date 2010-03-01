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

public class HelmetTest extends AbstractDefensiveItemTest {

	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;

	private static final int MIN_MAGIC_DEF = 10;
	private static final int MAX_MAGIC_DEF = 50;
	
	private Helmet helmet1;
	private Helmet helmet2;
	
	@Before
	public void setUp() throws Exception {
		helmet1 = new Helmet(1, "Viking Helmet", 5, true, 1, 1, 0, 0, null, false, 1, MIN_DEF, MAX_DEF, MIN_MAGIC_DEF, MAX_MAGIC_DEF);
		helmet2 = new Helmet(1, "Viking Helmet", 1, true, 1, 1, 0, 0, null, false, 1, MAX_DEF, MAX_DEF, MAX_MAGIC_DEF, MAX_MAGIC_DEF);
		
		item = helmet1;
		itemGraphic = 1;
		itemId = 1;
		itemIsTradeable = true;
		itemManufactureDifficulty = 0;
		itemUsageDifficulty = 0;
		itemName = "Viking Helmet";
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
		Helmet clone = (Helmet) helmet1.clone();
		
		// Make sure all fields match
		assertEquals(helmet1.amount, clone.amount);
		assertEquals(helmet1.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(helmet1.graphic, clone.graphic);
		assertEquals(helmet1.id, clone.id);
		assertEquals(helmet1.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(helmet1.maxDef, clone.maxDef);
		assertEquals(helmet1.minDef, clone.minDef);
		assertEquals(helmet1.maxMagicDef, clone.maxMagicDef);
		assertEquals(helmet1.minMagicDef, clone.minMagicDef);
		assertEquals(helmet1.name, clone.name);
		assertEquals(helmet1.tradeable, clone.tradeable);
		assertEquals(helmet1.usageDifficulty, clone.usageDifficulty);
		assertEquals(helmet1.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(helmet1 == clone);
		
		
		clone = (Helmet) helmet2.clone();
		
		// Make sure all fields match
		assertEquals(helmet2.amount, clone.amount);
		assertEquals(helmet2.forbiddenArchetypes, clone.forbiddenArchetypes);
		assertEquals(helmet2.graphic, clone.graphic);
		assertEquals(helmet2.id, clone.id);
		assertEquals(helmet2.manufactureDifficulty, clone.manufactureDifficulty);
		assertEquals(helmet2.maxDef, clone.maxDef);
		assertEquals(helmet2.minDef, clone.minDef);
		assertEquals(helmet2.maxMagicDef, clone.maxMagicDef);
		assertEquals(helmet2.minMagicDef, clone.minMagicDef);
		assertEquals(helmet2.name, clone.name);
		assertEquals(helmet2.tradeable, clone.tradeable);
		assertEquals(helmet2.usageDifficulty, clone.usageDifficulty);
		assertEquals(helmet2.value, clone.value);
		
		// Make sure the object itself is different
		assertFalse(helmet2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		helmet1.use(character);
		helmet2.use(character);
		
		EasyMock.verify(character);
	}

}
