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

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.model.character.Character;
import com.ao.model.inventory.Inventory;
import com.ao.model.spell.Spell;
import com.ao.model.spell.effect.Effect;
import com.ao.model.worldobject.properties.ParchmentProperties;

public class ParchmentTest extends AbstractItemTest {

	private Parchment parchment1;
	private Parchment parchment2;
	private Spell spell1;
	private Spell spell2;
	@Before
	public void setUp() throws Exception {
		spell1 = new Spell(1, new Effect[] {}, 0, 0, 0, "foo", "foo", false, 1, 2, "OHL VOR PEK");
		ParchmentProperties props1 = new ParchmentProperties(WorldObjectType.PARCHMENT, 1, "Dardo Mágico", 1, 300, null, null, false, false, false, false, spell1);
		parchment1 = new Parchment(props1, 1);

		spell2 = new Spell(2, new Effect[] {}, 0, 0, 0, "foo", "foo", false, 1, 2, "Rahma Nañarak O'al");
		ParchmentProperties props2 = new ParchmentProperties(WorldObjectType.PARCHMENT, 1, "Terrible hambre de Igor", 100, 200, null, null, false, false, false, false, spell2);
		parchment2 = new Parchment(props2, 5);

		object = parchment1;
		objectProps = props1;
		ammount = 1;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetSpell() {
		assertEquals(parchment1.getSpell(), spell1);
		assertEquals(parchment2.getSpell(), spell2);
	}

	@Test
	public void testUseWithCleanup() {

		Inventory inventory = EasyMock.createMock(Inventory.class);

		Character character = EasyMock.createMock(Character.class);

		Spell[] spells = new Spell[] {};

		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getSpells()).andReturn(spells).anyTimes();

		// Consumption of parchment1 requires these 2 calls.
		inventory.cleanup();
		character.addSpell(spell1);

		EasyMock.replay(inventory, character);

		parchment1.use(character);

		assertArrayEquals(character.getSpells(), spells);

		EasyMock.verify(inventory, character);
	}

	@Test
	public void testUseWithoutCleanup() {

		Inventory inventory = EasyMock.createMock(Inventory.class);

		Character character = EasyMock.createMock(Character.class);

		Spell[] spells = new Spell[] {};

		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getSpells()).andReturn(spells).anyTimes();

		// Consumption of parchment1 requires just a call to addSpell.
		character.addSpell(spell2);

		EasyMock.replay(inventory, character);

		parchment2.use(character);

		assertArrayEquals(character.getSpells(), spells);

		EasyMock.verify(inventory, character);
	}

	@Test
	public void testUseWithASpellAlreadyLearnt() {

		Inventory inventory = EasyMock.createMock(Inventory.class);

		Character character = EasyMock.createMock(Character.class);

		Spell[] spells = new Spell[] {spell1};

		EasyMock.expect(character.getInventory()).andReturn(inventory).anyTimes();
		EasyMock.expect(character.getSpells()).andReturn(spells).anyTimes();

		// Consumption of parchment1 already learnt doesn't requires any call.

		EasyMock.replay(inventory, character);

		parchment1.use(character);

		assertArrayEquals(character.getSpells(), spells);

		EasyMock.verify(inventory, character);
	}

	@Test
	public void testClone() {
		Parchment clone1 = (Parchment) parchment1.clone();

		assertEquals(clone1.getAmount(), parchment1.getAmount());
		assertEquals(clone1.getSpell(), parchment1.getSpell());
		assertEquals(clone1.properties, parchment1.properties);
		assertFalse(clone1 == parchment1);

		Parchment clone2 = (Parchment) parchment2.clone();

		assertEquals(clone2.getAmount(), parchment2.getAmount());
		assertEquals(clone2.getSpell(), parchment2.getSpell());
		assertEquals(clone2.properties, parchment2.properties);
		assertFalse(clone2 == parchment2);
	}

}
