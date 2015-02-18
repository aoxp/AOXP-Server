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

package com.ao.data.dao.ini;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.exception.DAOException;
import com.ao.model.worldobject.WorldObjectType;
import com.ao.model.worldobject.properties.BackpackProperties;
import com.ao.model.worldobject.properties.ForumProperties;
import com.ao.model.worldobject.properties.ItemProperties;
import com.ao.model.worldobject.properties.MineralProperties;
import com.ao.model.worldobject.properties.SignProperties;
import com.ao.model.worldobject.properties.StatModifyingItemProperties;
import com.ao.model.worldobject.properties.TemporalStatModifyingItemProperties;
import com.ao.model.worldobject.properties.WorldObjectProperties;

public class WorldObjectPropertiesDAOIniTest {

	private static final int YELLOW_POTION_INDEX = 36;
	private static final int BLUE_POTION_INDEX = 37;
	private static final int RED_POTION_INDEX = 38;
	private static final int GREEN_POTION_INDEX = 39;
	private static final int VIOLET_POTION_INDEX = 166;
	private static final int BLACK_POTION_INDEX = 645;

	private static final int SIGN_INDEX = 13;
	private static final int ULLATHORPE_FORUM_INDEX = 34;
	private static final int BACKPACK_INDEX = 866;
	private static final int MINERAL_INDEX = 192;


	private static final String TEST_OBJ_DAT = "src/test/resources/obj.dat";
	private static final int TEST_ITEMS_PER_ROW = 5;

	protected WorldObjectPropertiesDAOIni dao;

	@Before
	public void setUp() throws Exception {
		dao = new WorldObjectPropertiesDAOIni(TEST_OBJ_DAT, TEST_ITEMS_PER_ROW);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieveAll() {
		try {
			dao.loadAll();
		} catch (DAOException e) {
			fail("Loading of objects failed with message " + e.getMessage());
		}

		// Check some items to make sure they loaded properly...
		WorldObjectProperties yellowPotion = dao.getWorldObjectProperties(YELLOW_POTION_INDEX);
		assertTrue(yellowPotion instanceof TemporalStatModifyingItemProperties);
		assertEquals(WorldObjectType.DEXTERITY_POTION, yellowPotion.getType());

		WorldObjectProperties bluePotion = dao.getWorldObjectProperties(BLUE_POTION_INDEX);
		assertTrue(bluePotion instanceof StatModifyingItemProperties);
		assertEquals(WorldObjectType.MANA_POTION, bluePotion.getType());

		WorldObjectProperties redPotion = dao.getWorldObjectProperties(RED_POTION_INDEX);
		assertTrue(redPotion instanceof StatModifyingItemProperties);
		assertEquals(WorldObjectType.HP_POTION, redPotion.getType());

		WorldObjectProperties greenPotion = dao.getWorldObjectProperties(GREEN_POTION_INDEX);
		assertTrue(greenPotion instanceof TemporalStatModifyingItemProperties);
		assertEquals(WorldObjectType.STRENGTH_POTION, greenPotion.getType());

		WorldObjectProperties violetPotion = dao.getWorldObjectProperties(VIOLET_POTION_INDEX);
		assertTrue(violetPotion instanceof ItemProperties);
		assertEquals(WorldObjectType.POISON_POTION, violetPotion.getType());

		WorldObjectProperties blackPotion = dao.getWorldObjectProperties(BLACK_POTION_INDEX);
		assertTrue(blackPotion instanceof ItemProperties);
		assertEquals(WorldObjectType.DEATH_POTION, blackPotion.getType());

		WorldObjectProperties sign = dao.getWorldObjectProperties(SIGN_INDEX);
		assertTrue(sign instanceof SignProperties);
		assertEquals(WorldObjectType.SIGN, sign.getType());

		WorldObjectProperties ullathorpeForum = dao.getWorldObjectProperties(ULLATHORPE_FORUM_INDEX);
		assertTrue(ullathorpeForum instanceof ForumProperties);
		assertEquals(WorldObjectType.FORUM, ullathorpeForum.getType());

		WorldObjectProperties backpack = dao.getWorldObjectProperties(BACKPACK_INDEX);
		assertTrue(backpack instanceof BackpackProperties);
		assertEquals(WorldObjectType.BACKPACK, backpack.getType());

		WorldObjectProperties mineral = dao.getWorldObjectProperties(MINERAL_INDEX);
		assertTrue(mineral instanceof MineralProperties);
		assertEquals(WorldObjectType.MINERAL, mineral.getType());


		// TODO : Keep doing this with other object types. Also check some other attributes are properly loaded...
	}
}
