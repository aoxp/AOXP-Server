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

package com.ao.model.character;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

public class ReputationImplTest {

	private static final int ASSASIN_POINTS = 500;
	private static final int BANDIT_POINTS = 500;
	private static final int BURGEOIS_POINTS = 500;
	private static final int THIEFF_POINTS = 500;
	private static final int NOBLE_POINTS = 500;
	private static final boolean BELONGS_TO_FACTION = false;

	private Reputation rep;

	@Before
	public void setUp() {
		rep = new ReputationImpl(ASSASIN_POINTS, BANDIT_POINTS, BURGEOIS_POINTS, THIEFF_POINTS, NOBLE_POINTS, BELONGS_TO_FACTION);
	}

	@Test
	public void testAddToAssassin() {
		rep.addToAssassin(10);

		assertEquals(ASSASIN_POINTS + 10, rep.getAssassin());
	}

	@Test
	public void testAddToBandit() {
		rep.addToBandit(10);

		assertEquals(BANDIT_POINTS + 10, rep.getBandit());
	}

	@Test
	public void testAddToBourgeois() {
		rep.addToBourgeois(10);

		assertEquals(BURGEOIS_POINTS + 10, rep.getBourgeois());
	}

	@Test
	public void testAddToNoblePoints() {
		rep.addToNoble(10);

		assertEquals(NOBLE_POINTS + 10, rep.getNoble());
	}

	@Test
	public void testAddToThief() {
		rep.addToThief(10);

		assertEquals(THIEFF_POINTS + 10, rep.getThief());
	}

	@Test
	public void testGetAlignment() {
		assertSame(Alignment.CRIMINAL, rep.getAlignment());

		rep.addToNoble(600);

		assertSame(Alignment.CITIZEN, rep.getAlignment());
	}

	@Test
	public void testGetAssassin() {
		assertEquals(ASSASIN_POINTS, rep.getAssassin());
	}

	@Test
	public void testGetBandit() {
		assertEquals(BANDIT_POINTS, rep.getBandit());
	}

	@Test
	public void testGetBourgeois() {
		assertEquals(BURGEOIS_POINTS, rep.getBourgeois());
	}

	@Test
	public void testGetThief() {
		assertEquals(THIEFF_POINTS, rep.getThief());
	}

	@Test
	public void testGetNoble() {
		assertEquals(NOBLE_POINTS, rep.getNoble());
	}

	@Test
	public void testBelongsToFaction() {
		assertFalse(rep.belongsToFaction());
	}

	@Test
	public void testSetBelongsToFaction() {
		rep.setBelongsToFaction(!BELONGS_TO_FACTION);

		assertEquals(!BELONGS_TO_FACTION, rep.belongsToFaction());
	}

}
