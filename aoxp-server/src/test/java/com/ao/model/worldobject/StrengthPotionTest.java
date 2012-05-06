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

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.TemporalStatModifyingItemProperties;

public class StrengthPotionTest extends AbstractItemTest {

	private static final int MIN_STR = 1;
	private static final int MAX_STR = 5;
	private static final int DURATION = 300;
	
	private StrengthPotion potion1;
	private StrengthPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		TemporalStatModifyingItemProperties props1 = new TemporalStatModifyingItemProperties(WorldObjectType.STRENGTH_POTION, 1, "Green Potion", 1, 1, null, null, false, false, false, false, MIN_STR, MAX_STR, DURATION);
		potion1 = new StrengthPotion(props1, 5);
		
		TemporalStatModifyingItemProperties props2 = new TemporalStatModifyingItemProperties(WorldObjectType.STRENGTH_POTION, 1, "Big Green Potion", 1, 1, null, null, false, false, false, false, MAX_STR, MAX_STR, DURATION);
		potion2 = new StrengthPotion(props2, 1);
		
		object = potion2;
		ammount = 1;
		objectProps = props2;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUseWithCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of potion2 requires these 2 calls.
		inventory.cleanup();
		character.addToStrength(MAX_STR, DURATION);
		
		EasyMock.replay(inventory, character);
		
		potion2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		Capture<Integer> capture = new Capture<Integer>();
		
		// Consumption of potion1 requires just a call to addToStrength.
		character.addToStrength(EasyMock.capture(capture), EasyMock.eq(DURATION));
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_STR);
		assertTrue(capture.getValue() <= MAX_STR);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinModifier() {
		
		assertEquals(MIN_STR, potion1.getMinModifier());
		assertEquals(MAX_STR, potion2.getMinModifier());
	}

	@Test
	public void testGetMaxModifier() {
		
		assertEquals(MAX_STR, potion1.getMaxModifier());
		assertEquals(MAX_STR, potion2.getMaxModifier());
	}

	@Test
	public void testClone() {
		
		StrengthPotion clone = (StrengthPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (StrengthPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
	@Test
	public void testGetEffectDuration() {
		assertEquals(DURATION, potion1.getEffectDuration());
		assertEquals(DURATION, potion2.getEffectDuration());
	}
}
