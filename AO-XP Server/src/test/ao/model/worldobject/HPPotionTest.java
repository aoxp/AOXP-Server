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

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;
import ao.model.worldobject.properties.StatModifyingItemProperties;

public class HPPotionTest extends AbstractItemTest {

	private static final int MIN_HP = 1;
	private static final int MAX_HP = 5;
	
	private HPPotion potion1;
	private HPPotion potion2;
	
	@Before
	public void setUp() throws Exception {
		
		StatModifyingItemProperties props1 = new StatModifyingItemProperties(WorldObjectType.HP_POTION, 1, "Red Potion", 1, true, 1, 0, null, null, false, false, false, false, MIN_HP, MAX_HP);
		potion1 = new HPPotion(props1, 5);
		
		StatModifyingItemProperties props2 = new StatModifyingItemProperties(WorldObjectType.HP_POTION, 1, "Big Red Potion", 1, true, 1, 0, null, null, false, false, false, false, MAX_HP, MAX_HP);
		potion2 = new HPPotion(props2, 1);
		
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
		
		// Consumption of potion2 requires these 2 calls.
		inventory.cleanup();
		character.addToHitPoints(MAX_HP);
		
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
		
		// Consumption of potion1 requires just a call to addToHitPoints.
		character.addToHitPoints(EasyMock.capture(capture));
		
		EasyMock.replay(inventory, character);
		
		potion1.use(character);
		
		/// Make sure the value is in the correct range
		assertTrue(capture.getValue() >= MIN_HP);
		assertTrue(capture.getValue() <= MAX_HP);
		
		EasyMock.verify(inventory, character);
	}

	@Test
	public void testGetMinHP() {
		
		assertEquals(MIN_HP, potion1.getMinHP());
		assertEquals(MAX_HP, potion2.getMinHP());
	}

	@Test
	public void testGetMaxHP() {
		
		assertEquals(MAX_HP, potion1.getMaxHP());
		assertEquals(MAX_HP, potion2.getMaxHP());
	}

	@Test
	public void testClone() {
		
		HPPotion clone = (HPPotion) potion1.clone();
		
		// Make sure all fields match
		assertEquals(potion1.amount, clone.amount);
		assertEquals(potion1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion1 == clone);
		
		
		clone = (HPPotion) potion2.clone();
		
		// Make sure all fields match
		assertEquals(potion2.amount, clone.amount);
		assertEquals(potion2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(potion2 == clone);
	}
	
}
