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

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;
import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.properties.ItemProperties;

public class GoldTest extends AbstractItemTest {

	private Gold gold1;
	private Gold gold2;
	
	@Before
	public void setUp() throws Exception {
		ItemProperties props = new ItemProperties(WorldObjectType.MONEY, 13, "Monedas de oro", 1, 1, 1, null, null, false, true, true, true);
		gold1 = new Gold(props, 1000);
		gold2 = new Gold(props, 2000);
		
		object = gold1;
		objectProps = props;
		ammount = 1000;
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		Inventory inventory = EasyMock.createMock(Inventory.class);
		
		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		
		inventory.cleanup();
		character.addMoney(1000);
		
		EasyMock.replay(character, inventory);
		
		gold1.use(character);
		
		EasyMock.verify(character, inventory);
		
	}
	
	@Test
	public void testClone() {
		Gold clone = (Gold) gold1.clone();
		
		assertEquals(gold1.getAmount(), clone.getAmount());
		assertFalse(clone == gold1);
		
		clone = (Gold) gold2.clone();
		
		assertEquals(gold2.getAmount(), clone.getAmount());
		assertFalse(clone == gold2);
		
	}
	
}
