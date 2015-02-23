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
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import com.ao.config.ServerConfig;
import com.ao.context.ApplicationContext;
import com.ao.context.ApplicationProperties;
import com.ao.mock.MockFactory;
import com.ao.network.Connection;
import com.ao.network.DataBuffer;
import com.ao.network.packet.IncomingPacket;
import com.ao.network.packet.outgoing.ErrorMessagePacket;
import com.ao.security.SecurityManager;
import com.ao.service.LoginService;
import com.ao.service.login.LoginServiceImpl;

public class LoginExistingCharacterPacketTest {

	private static final String BANNED_CHARACTER_NAME = "banned";
	private static final String BANNED_CHARACTER_PASSWORD = "a";
	private static final String CHARACTER_NAME = "test";
	private static final String CHARACTER_PASSWORD = "a";
	private static final byte CLIENT_MAJOR = 0;
	private static final byte CLIENT_MINOR = 12;
	private static final byte CLIENT_REVISION = 2;

	private Connection connection;
	private IncomingPacket packet;
	private DataBuffer inputBuffer;
	private ArgumentCaptor<ErrorMessagePacket> errPacket;

	private ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
	private SecurityManager security = ApplicationContext.getInstance(SecurityManager.class);

	static {
		ApplicationProperties.loadProperties("test.properties");
		ApplicationContext.reload();
	}

	@Before
	public void setUp() throws Exception {
		packet = new LoginExistingCharacterPacket();
		errPacket = ArgumentCaptor.forClass(ErrorMessagePacket.class);
		connection = MockFactory.mockConnection();
		inputBuffer = mock(DataBuffer.class);

		config.setRestrictedToAdmins(false);
	}

	private void writeLogin(final String charName, final String password,
			final byte major, final byte minor, final byte version,
			final String hash) throws Exception {
		when(inputBuffer.getReadableBytes()).thenReturn(charName.length() + 2 + security.getPasswordHashLength() + 6 + security.getClientHashLength());

		when(inputBuffer.getASCIIString()).thenReturn(charName);
		when(inputBuffer.getASCIIStringFixed(security.getPasswordHashLength())).thenReturn(password);
		when(inputBuffer.get()).thenReturn(major).thenReturn(minor).thenReturn(version);
		when(inputBuffer.getASCIIStringFixed(security.getClientHashLength())).thenReturn(hash);
	}

	@Test
	public void testHandleRestrictedToAdminsTest() throws Exception {
		config.setRestrictedToAdmins(true);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD,
				CLIENT_MAJOR, CLIENT_MINOR, CLIENT_REVISION, "");
		packet.handle(inputBuffer, connection);

		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.ONLY_ADMINS_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleCharacterNotFound() throws Exception {
		writeLogin("foo", "foo", CLIENT_MAJOR, CLIENT_MINOR, CLIENT_REVISION, "");
		packet.handle(inputBuffer, connection);

		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.CHARACTER_NOT_FOUND_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleIncorrectPassword() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD + "foo", CLIENT_MAJOR, CLIENT_MINOR, CLIENT_REVISION, "");
		packet.handle(inputBuffer, connection);

		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.INCORRECT_PASSWORD_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleOutOfDateClient() throws Exception {
		LoginServiceImpl service = (LoginServiceImpl) ApplicationContext.getInstance(LoginService.class);
		service.setCurrentClientVersion(CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_REVISION);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, (byte) 0, (byte) 0, (byte) 0, "");
		packet.handle(inputBuffer, connection);

		verify(connection).send(errPacket.capture());
		assertEquals(String.format(LoginServiceImpl.CLIENT_OUT_OF_DATE_ERROR_FORMAT, CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_REVISION), errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleBannedCharacter() throws Exception {
		writeLogin(BANNED_CHARACTER_NAME, BANNED_CHARACTER_PASSWORD,
				CLIENT_MAJOR, CLIENT_MINOR, CLIENT_REVISION, "");
		packet.handle(inputBuffer, connection);

		verify(connection).send(errPacket.capture());
		assertEquals(LoginServiceImpl.BANNED_CHARACTER_ERROR, errPacket.getValue().getMessage());
	}

}
