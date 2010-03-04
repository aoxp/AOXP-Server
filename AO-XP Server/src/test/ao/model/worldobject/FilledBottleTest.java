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

import junit.framework.Assert;

import org.easymock.Capture;
import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.inventory.Inventory;
import ao.model.worldobject.properties.ItemProperties;
import ao.model.worldobject.properties.StatModifyingItemProperties;

public class FilledBottleTest extends AbstractItemTest {

	private static final int THIRST = 5;
	
	private FilledBottle bottle1;
	private FilledBottle bottle2;
	

	@Before
	public void setUp() throws Exception {
		StatModifyingItemProperties props = new StatModifyingItemProperties(1, "Water Bottle", 1, true, 1, 0, 0, null, false, THIRST, THIRST);
		ItemProperties emptyProps = new ItemProperties(1, "Water Bottle", 1, true, 1, 0, 0, null, false);
		bottle1 = new FilledBottle(props, 5, emptyProps);
		
		bottle2 = new FilledBottle(props, 1, emptyProps);
		
		object = bottle2;
		ammount = 1;
		objectProps = props;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testUseWithCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		Capture<Item> addedItem = new Capture<Item>();
		EasyMock.expect(inventory.addItem(EasyMock.capture(addedItem))).andReturn(1);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of bottle2 requires these 2 calls.
		inventory.cleanup();
		character.addToThirstiness(THIRST);
		
		EasyMock.replay(inventory, character);
		
		bottle2.use(character);
		
		EasyMock.verify(inventory, character);
		
		Assert.assertTrue(addedItem.getValue() instanceof EmptyBottle);
		EmptyBottle emptyBottle = (EmptyBottle) addedItem.getValue();
		Assert.assertEquals(bottle2.emptyBottleProperties, emptyBottle.properties);
		Assert.assertEquals(1, emptyBottle.amount);
	}
	
	@Test
	public void testUseWithoutCleanup() {
		
		Inventory inventory = EasyMock.createMock(Inventory.class);
		Capture<Item> addedItem = new Capture<Item>();
		EasyMock.expect(inventory.addItem(EasyMock.capture(addedItem))).andReturn(1);
		
		Character character = EasyMock.createMock(Character.class);
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		// Consumption of bottle1 requires just a call to addToThirstiness.
		character.addToThirstiness(THIRST);
		
		EasyMock.replay(inventory, character);
		
		bottle1.use(character);
		
		EasyMock.verify(inventory, character);

		Assert.assertTrue(addedItem.getValue() instanceof EmptyBottle);
		EmptyBottle emptyBottle = (EmptyBottle) addedItem.getValue();
		Assert.assertEquals(bottle1.emptyBottleProperties, emptyBottle.properties);
		Assert.assertEquals(1, emptyBottle.amount);
	}
}
