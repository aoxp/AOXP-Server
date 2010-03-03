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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.model.worldobject.properties.TeleportProperties;

public class TeleportTest extends AbstractWorldObjectTest {

	private static final int RADIUS = 4;
	
	private Teleport teleport1;
	
	@Before
	public void setUp() throws Exception {
		TeleportProperties props1 = new TeleportProperties(1, "Teleport", 1, RADIUS);
		teleport1 = new Teleport(props1);
		
		object = teleport1;
		objectProps = props1;
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRadius() {
		assertEquals(RADIUS, teleport1.getRadius());
	}
}
