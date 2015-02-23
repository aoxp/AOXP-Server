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
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.exception.DAOException;
import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.Skill;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.map.City;
import com.ao.model.user.Account;
import com.ao.model.user.ConnectedUser;

public class UserDAOIniTest {

	private UserDAOIni dao;

	private static final String CHARACTER_NICK = "test";
	private static final String NEW_CHARACTER_NICK = "newchartest";
	private static final String CHARACTER_MAIL = "test@test.com";
	private static final String CHARACTER_PASSWORD = "testpass";
	private static final String CHARFILES_PATH = "src/test/resources/charfiles/";

	@Before
	public void setUp() throws Exception {
		// Disable escaping for Ini4j
		System.setProperty("org.ini4j.config.escape", "false");

		dao = new UserDAOIni(CHARFILES_PATH);
	}

	@After
	public void tearDown() throws Exception {
		// Be really sure the file is not there before the next test
		final File file = new File(dao.getCharFilePath(NEW_CHARACTER_NICK));
		file.delete();
	}

	@Test
	public void testRetrieve() throws DAOException {
		final Account acc = dao.retrieve(CHARACTER_NICK);

		assertNotNull(acc);

		// Ensure we get the requested character and not another one.
		assertEquals(CHARACTER_NICK, acc.getName());
	}

	@Test
	public void testCreateAccount() throws DAOException {
		final Account acc = dao.create(NEW_CHARACTER_NICK, CHARACTER_PASSWORD, CHARACTER_MAIL);

		assertEquals(NEW_CHARACTER_NICK, acc.getName());
		assertEquals(CHARACTER_MAIL, acc.getMail());

		assertTrue(acc.authenticate(CHARACTER_PASSWORD));

		final File file = new File(dao.getCharFilePath(NEW_CHARACTER_NICK));

		assertTrue(file.exists());

		// Don't leave the file there!
		file.delete();
	}

	@Test
	public void testDelete() throws DAOException {
		dao.create(NEW_CHARACTER_NICK, CHARACTER_PASSWORD, CHARACTER_MAIL);
		final File file = new File(dao.getCharFilePath(NEW_CHARACTER_NICK));

		assertTrue(file.exists());

		dao.delete(NEW_CHARACTER_NICK);

		assertFalse(file.exists());
	}

	@Test
	public void testCreateCharacter() throws DAOException {
		final byte[] skills = new byte[Skill.AMOUNT];

		for (int i = 0; i < Skill.AMOUNT; i++) {
			if (i == 1) {
				skills[i] = 10;
			} else {
				skills[i] = 0;
			}
		}

		final City city = mock(City.class);

		// TODO: Use constants!!
		final UserCharacter chara = dao.create(mock(ConnectedUser.class), NEW_CHARACTER_NICK, Race.HUMAN, Gender.FEMALE,
				UserArchetype.ASSASIN, 75, city, (byte) 18, (byte) 18,
				(byte) 18, (byte) 18, (byte) 18, 10, 1);

		final File file = new File(dao.getCharFilePath(NEW_CHARACTER_NICK));

		assertTrue(file.exists());

		assertEquals(NEW_CHARACTER_NICK, chara.getName());
		assertEquals(UserArchetype.ASSASIN.getArchetype(), chara.getArchetype());
		assertEquals(Gender.FEMALE, chara.getGender());
		assertEquals(Race.HUMAN, chara.getRace());
		assertEquals((byte) 1, chara.getLevel());

		// TODO: To be continued... :P

		file.delete();
	}
}
