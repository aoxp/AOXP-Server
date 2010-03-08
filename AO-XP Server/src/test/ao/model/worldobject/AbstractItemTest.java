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

import org.junit.Test;

import ao.model.worldobject.properties.ItemProperties;

public abstract class AbstractItemTest extends AbstractWorldObjectTest {

	protected int ammount;
	
	@Test
	public void testAddAmount() {
		AbstractItem item = (AbstractItem) object;
		int ammount = item.getAmount();
		
		// Check adding and removing
		if (ammount < AbstractItem.MAX_STACKED_ITEMS) {
			item.addAmount(1);
			assertEquals(ammount + 1, item.getAmount());
			
			item.addAmount(-1);
			assertEquals(ammount, item.getAmount());
		} else {
			item.addAmount(-1);
			assertEquals(ammount - 1, item.getAmount());
			
			item.addAmount(1);
			assertEquals(ammount, item.getAmount());
		}
		
		// Check limits
		item.addAmount(AbstractItem.MAX_STACKED_ITEMS + 1);
		assertEquals(AbstractItem.MAX_STACKED_ITEMS, item.getAmount());
	}

	@Test
	public void testGetValue() {
		AbstractItem item = (AbstractItem) object;
		ItemProperties itemProps = (ItemProperties) objectProps;
		assertEquals(itemProps.getValue(), item.getValue());
	}

	@Test
	public void testGetManufactureDifficulty() {
		AbstractItem item = (AbstractItem) object;
		ItemProperties itemProps = (ItemProperties) objectProps;
		assertEquals(itemProps.getManufactureDifficulty(), item.getManufactureDifficulty());
	}

	@Test
	public void testGetUsageDifficulty() {
		AbstractItem item = (AbstractItem) object;
		ItemProperties itemProps = (ItemProperties) objectProps;
		assertEquals(itemProps.getUsageDifficulty(), item.getUsageDifficulty());
	}

	@Test
	public void testIsTradeable() {
		AbstractItem item = (AbstractItem) object;
		ItemProperties itemProps = (ItemProperties) objectProps;
		assertEquals(itemProps.isTradeable(), item.isTradeable());
	}

	@Test
	public void testIsNewbie() {
		AbstractItem item = (AbstractItem) object;
		ItemProperties itemProps = (ItemProperties) objectProps;
		assertEquals(itemProps.isNewbie(), item.isNewbie());
	}
	
	@Test
	public void testCanBeStolen() {
		AbstractItem item = (AbstractItem) object;
		assertTrue(item.canBeStolen());
	}
}
