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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.easymock.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.character.Character;
import ao.model.worldobject.properties.DefensiveItemProperties;

public class ArmorTest extends AbstractDefensiveItemTest {

	private static final int MIN_DEF = 1;
	private static final int MAX_DEF = 5;
	
	private static final int MIN_MAGIC_DEF = 10;
	private static final int MAX_MAGIC_DEF = 50;
	
	private Armor armor1;
	private Armor armor2;
	
	@Before
	public void setUp() throws Exception {
		DefensiveItemProperties props1 = new DefensiveItemProperties(WorldObjectType.ARMOR, 1, "Leather Armor", 1, 1, 0, null, null, false, false, false, false, 1, MIN_DEF, MAX_DEF, MIN_MAGIC_DEF, MAX_MAGIC_DEF);
		armor1 = new Armor(props1, 5);
		
		DefensiveItemProperties props2 = new DefensiveItemProperties(WorldObjectType.ARMOR, 1, "Leather Armor", 1, 1, 0, null, null, false, false, false, false, 1, MAX_DEF, MAX_DEF, MAX_MAGIC_DEF, MAX_MAGIC_DEF);
		armor2 = new Armor(props2, 1);
		
		object = armor1;
		objectProps = props1;
		ammount = 5;
		itemEquipped = false;
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testClone() {
		Armor clone = (Armor) armor1.clone();
		
		// Make sure all fields match
		assertEquals(armor1.amount, clone.amount);
		assertEquals(armor1.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(armor1 == clone);
		
		
		clone = (Armor) armor2.clone();
		
		// Make sure all fields match
		assertEquals(armor2.amount, clone.amount);
		assertEquals(armor2.properties, clone.properties);
		
		// Make sure the object itself is different
		assertFalse(armor2 == clone);
	}

	@Test
	public void testUse() {
		Character character = EasyMock.createMock(Character.class);
		EasyMock.replay(character);
		
		// nothing should happen
		armor1.use(character);
		armor2.use(character);
		
		EasyMock.verify(character);
	}

}
