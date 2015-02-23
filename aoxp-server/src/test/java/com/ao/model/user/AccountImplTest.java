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

package com.ao.model.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class AccountImplTest {

	private static final String ACCOUNT_NAME = "an account";
	private static final String ACCOUNT_PASSWORD = "a password";
	private static final String ACCOUNT_INVALID_PASSWORD = "invalid pass";
	private static final String ACCOUNT_EMAIL = "anemail@address.com";
	private static final boolean ACCOUNT_BANNED = false;
	private static final Set<String> ACCOUNT_CHARACTERS = new HashSet<String>();
	private static final String TEST_CHARACTER_NAME = "TEST";
	private static final String NON_EXISTING_CHARACTER_NAME = "non existing character";

	private AccountImpl account;

	@Before
	public void setUp() throws Exception {
		account = new AccountImpl(ACCOUNT_NAME, ACCOUNT_PASSWORD, ACCOUNT_EMAIL, ACCOUNT_CHARACTERS, ACCOUNT_BANNED);
		account.characters.add(TEST_CHARACTER_NAME);
	}

	@Test
	public void testGetName() {
		assertEquals(account.getName(), ACCOUNT_NAME);
	}

	@Test
	public void testGetMail() {
		assertEquals(account.getMail(), ACCOUNT_EMAIL);
	}

	@Test
	public void testGetCharacters() {
		assertEquals(account.getCharacters(), ACCOUNT_CHARACTERS);
	}

	@Test
	public void testHasCharacter() {
		assertTrue(account.hasCharacter(TEST_CHARACTER_NAME));
		assertFalse(account.hasCharacter(NON_EXISTING_CHARACTER_NAME));
	}

	@Test
	public void testIsBanned() {
		assertEquals(account.isBanned(), ACCOUNT_BANNED);

		account.setBanned(!ACCOUNT_BANNED);

		assertEquals(account.isBanned(), !ACCOUNT_BANNED);
	}

	@Test
	public void testAuthenticate() {
		assertTrue(account.authenticate(ACCOUNT_PASSWORD));
		assertFalse(account.authenticate(ACCOUNT_INVALID_PASSWORD));
	}

	@Test
	public void testAddCharacter() {
		final String charr = "foo";

		account.addCharacter(charr);

		assertTrue(account.characters.contains(charr));
	}

}
