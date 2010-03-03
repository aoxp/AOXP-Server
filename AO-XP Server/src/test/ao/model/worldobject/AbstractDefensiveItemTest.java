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

import ao.model.worldobject.properties.DefensiveItemProperties;

public abstract class AbstractDefensiveItemTest extends AbstractEquipableItemTest {
	
	@Test
	public void testGetMaxDef() {
		assertEquals(((DefensiveItemProperties) objectProps).getMaxDef(), ((AbstractDefensiveItem) object).getMaxDef());
	}
	
	@Test
	public void testGetMinDef() {
		assertEquals(((DefensiveItemProperties) objectProps).getMinDef(), ((AbstractDefensiveItem) object).getMinDef());
	}
	
	@Test
	public void testGetMaxMagicDef() {
		assertEquals(((DefensiveItemProperties) objectProps).getMaxMagicDef(), ((AbstractDefensiveItem) object).getMaxMagicDef());
	}

	@Test
	public void testGetMinMagicDef() {
		assertEquals(((DefensiveItemProperties) objectProps).getMinMagicDef(), ((AbstractDefensiveItem) object).getMinMagicDef());
	}

}
