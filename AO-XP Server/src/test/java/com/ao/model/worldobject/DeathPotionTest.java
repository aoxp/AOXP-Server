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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.ItemProperties;

public class DeathPotionTest extends AbstractItemTest {

	private static final Integer USER_HP = 30;
	
	private DeathPotion potion1;
	private DeathPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		ItemProperties props1 = new ItemProperties(WorldObjectType.DEATH_POTION, 1, "Black Potion", 1, 1, null, null, false, false, false, true);
		potion1 = new DeathPotion(props1, 5);
		
		ItemProperties props2 = new ItemProperties(WorldObjectType.DEATH_POTION, 1, "Black Potion", 1, 1, null, null, false, true, true, false);
		potion2 = new DeathPotion(props2, 1);
		
		object = potion2;
		objectProps = props2;
		ammount = 1;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUseWithCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getHitPoints()).andReturn(USER_HP).anyTimes();
		
		// Consumption of potion2 requires these 2 calls.
		inventory.cleanup();
		character.addToHitPoints(-USER_HP);
		
		EasyMock.replay(inventory, character);
		
		potion2.use(character);
		
		EasyMock.verify(inventory, character);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getHitPoints()).andReturn(USER_HP).anyTimes();
		
		// Consumption of potion1 requires just a call to addToHitPoints.
		character.addToHitPoints(-USER_HP);
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testClone() {
		
		DeathPotion clone = (DeathPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (DeathPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
}
