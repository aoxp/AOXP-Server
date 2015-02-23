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

package com.ao.network.packet.incoming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.ao.config.ServerConfig;
import com.ao.context.ApplicationContext;
import com.ao.context.ApplicationProperties;
import com.ao.data.dao.AccountDAO;
import com.ao.mock.MockFactory;
import com.ao.model.builder.UserCharacterBuilder;
import com.ao.model.character.Attribute;
import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.user.Account;
import com.ao.model.user.ConnectedUser;
import com.ao.network.Connection;
import com.ao.network.DataBuffer;
import com.ao.network.packet.IncomingPacket;
import com.ao.network.packet.outgoing.ErrorMessagePacket;
import com.ao.security.SecurityManager;
import com.ao.service.LoginService;
import com.ao.service.MapService;
import com.ao.service.login.LoginServiceImpl;

public class LoginNewCharacterPacketTest {

	private static final String CHARACTER_NAME = "testnew";
	private static final String CHARACTER_PASSWORD = "a";
	private static final String CHARACTER_MAIL = "thistestisnotgoingtofail@aoxp.com";
	private static final byte CHARACTER_ARCHETYPE = (byte) UserArchetype.ASSASIN.ordinal();
	private static final byte CHARACTER_RACE = (byte) Race.HUMAN.ordinal();
	private static final byte CHARACTER_GENDER = (byte) Gender.MALE.ordinal();
	private static final byte CHARACTER_HOMELAND = 1;
	private static final byte CHARACTER_HEAD = 10;

	private static final String INVALID_CHARACTER_NAME = " ";

	private static final byte CLIENT_MAJOR = 0;
	private static final byte CLIENT_MINOR = 12;
	private static final byte CLIENT_VERSION = 2;

	private Connection connection;
	private IncomingPacket packet;
	private DataBuffer inputBuffer;
	private ArgumentCaptor<ErrorMessagePacket> errPacket;
	private ServerConfig config;
	private SecurityManager security;
	private MapService mapService;

	static {
		ApplicationProperties.loadProperties("test.properties");
		ApplicationContext.reload();
	}

	@Before
	public void setUp() throws Exception {
		packet = new LoginNewCharacterPacket();
		errPacket = ArgumentCaptor.forClass(ErrorMessagePacket.class);
		connection = MockFactory.mockConnection();

		inputBuffer = mock(DataBuffer.class);

		config = ApplicationContext.getInstance(ServerConfig.class);
		security = ApplicationContext.getInstance(SecurityManager.class);

		mapService = ApplicationContext.getInstance(MapService.class);
		mapService.loadCities();

		config.setRestrictedToAdmins(false);
		config.setCharacterCreationEnabled(true);
	}

	@After
	public void tearDown() {
		ApplicationContext.getInstance(AccountDAO.class).delete(CHARACTER_NAME);
		ApplicationContext.getInstance(AccountDAO.class).delete(INVALID_CHARACTER_NAME);
	}

	@Test
	public void invalidEmailTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, "foo", CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(UserCharacterBuilder.INVALID_EMAIL_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void invalidNameTest() throws Exception {

		writeLogin(INVALID_CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(UserCharacterBuilder.INVALID_NAME_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void successfulCharacterCreation() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);

		final ArgumentCaptor<Account> capture = ArgumentCaptor.forClass(Account.class);
		verify((ConnectedUser) connection.getUser()).setAccount(capture.capture());
		final Account account = capture.getValue();

		assertTrue(account.hasCharacter(CHARACTER_NAME));
		assertEquals(account.getName(), CHARACTER_NAME);
		assertEquals(account.getMail(), CHARACTER_MAIL);

		// TODO: Check if the charfile was created.
	}

 	@Test
	public void clientOutOfDateTest() throws Exception {
		LoginServiceImpl service = (LoginServiceImpl) ApplicationContext.getInstance(LoginService.class);
		service.setCurrentClientVersion(CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, (byte) 0, (byte) 0,
				(byte) 0, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(String.format(LoginServiceImpl.CLIENT_OUT_OF_DATE_ERROR_FORMAT, CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION), errPacket.getValue().getMessage());
	}

	@Test
	public void nameTakenTest() throws Exception {
		ApplicationContext.getInstance(AccountDAO.class).create(CHARACTER_NAME, CHARACTER_PASSWORD, CHARACTER_MAIL);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.ACCOUNT_NAME_TAKEN_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void dicesThrewTest() throws Exception {
		final ConnectedUser user = (ConnectedUser) connection.getUser();
		when(user.getAttribute(any(Attribute.class))).thenReturn(null);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.MUST_THROW_DICES_BEFORE_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void invalidRaceTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", (byte) -1, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.INVALID_RACE_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void invalidGenderTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, (byte) -1, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.INVALID_GENDER_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void invalidHeadTest() throws Exception{
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				(byte) -1, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.INVALID_HEAD_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void invalidArchetypeTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, (byte) -1,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.INVALID_ARCHETYPE_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void characterCreationDisabledTest() throws Exception {
		config.setCharacterCreationEnabled(false);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.CHARACTER_CREATION_DISABLED_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void restrictedToAdminsTest() throws Exception {
		config.setRestrictedToAdmins(true);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_HEAD, CHARACTER_MAIL, CHARACTER_HOMELAND);

		packet.handle(inputBuffer, connection);
		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.ONLY_ADMINS_ERROR, errPacket.getValue().getMessage());
	}

	private void writeLogin(final String charName, final String password, final byte major,
			final byte minor, final byte version, final String hash, final byte race, final byte gender,
			final byte archetype, final byte head, final String mail, final byte homeland) throws Exception {
		when(inputBuffer.getReadableBytes()).thenReturn(charName.length() + 2 + security.getPasswordHashLength() + 8 + security.getClientHashLength() + mail.length() + 2);
		when(inputBuffer.getASCIIString()).thenReturn(charName).thenReturn(mail);
		when(inputBuffer.getASCIIStringFixed(security.getPasswordHashLength())).thenReturn(password);
		when(inputBuffer.get()).thenReturn(major).thenReturn(minor)
			.thenReturn(version).thenReturn(race).thenReturn(gender)
			.thenReturn(archetype).thenReturn(head).thenReturn(homeland);
		when(inputBuffer.getASCIIStringFixed(security.getClientHashLength())).thenReturn(hash);
	}
}
