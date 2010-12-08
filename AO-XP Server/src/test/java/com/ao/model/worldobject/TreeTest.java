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

import org.junit.After;
import org.junit.Before;

import com.ao.model.worldobject.properties.ResourceSourceProperties;

public class TreeTest extends AbstractResourceSourceTest {

	private Tree tree1;
	
	@Before
	public void setUp() throws Exception {
		ResourceSourceProperties props1 = new ResourceSourceProperties(WorldObjectType.TREE, 1, "Elven Tree", 1, 5);
		tree1 = new Tree(props1);
		
		object = tree1;
		objectProps = props1;
	}

	@After
	public void tearDown() throws Exception {
	}
}
