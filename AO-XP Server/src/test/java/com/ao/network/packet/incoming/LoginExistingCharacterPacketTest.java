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

import org.easymock.Capture;
import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

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
	private static final byte CLIENT_VERSION = 2;

	private Connection connection;
	private IncomingPacket packet;
	private DataBuffer inputBuffer;
	private Capture<ErrorMessagePacket> errPacket;

	private ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
	private SecurityManager security = ApplicationContext.getInstance(SecurityManager.class);

	static {
		ApplicationProperties.loadProperties("test.properties");
		ApplicationContext.reload();
	}

	@Before
	public void setUp() throws Exception {
		packet = new LoginExistingCharacterPacket();
		errPacket = new Capture<ErrorMessagePacket>();
		connection = MockFactory.mockConnection(errPacket);

		inputBuffer = EasyMock.createMock(DataBuffer.class);

		config.setRestrictedToAdmins(false);
	}

	private void writeLogin(String charName, String password, byte major, byte minor, byte version, String hash) throws Exception {
		EasyMock.expect(inputBuffer.getReadableBytes()).andReturn(charName.length() + 2 + security.getPasswordHashLength() + 6 + security.getClientHashLength());

		EasyMock.expect(inputBuffer.getASCIIString()).andReturn(charName).once();
		EasyMock.expect(inputBuffer.getASCIIStringFixed(security.getPasswordHashLength())).andReturn(password).once();
		EasyMock.expect(inputBuffer.get()).andReturn(major).once();
		EasyMock.expect(inputBuffer.get()).andReturn(minor).once();
		EasyMock.expect(inputBuffer.get()).andReturn(version).once();
		EasyMock.expect(inputBuffer.getASCIIStringFixed(security.getClientHashLength())).andReturn(hash).once();

		EasyMock.replay(inputBuffer);
	}

	@Test
	public void testHandleRestrictedToAdminsTest() throws Exception {
		config.setRestrictedToAdmins(true);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD,
				CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "");
		packet.handle(inputBuffer, connection);

		assertEquals(LoginServiceImpl.ONLY_ADMINS_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleCharacterNotFound() throws Exception {
		writeLogin("foo", "foo", CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "");
		packet.handle(inputBuffer, connection);

		assertEquals(LoginServiceImpl.CHARACTER_NOT_FOUND_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleIncorrectPassword() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD + "foo", CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "");
		packet.handle(inputBuffer, connection);

		assertEquals(LoginServiceImpl.INCORRECT_PASSWORD_ERROR, errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleOutOfDateClient() throws Exception {
		LoginServiceImpl service = (LoginServiceImpl) ApplicationContext.getInstance(LoginService.class);
		service.setCurrentClientVersion(CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION);

		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, (byte) 0, (byte) 0, (byte) 0, "");
		packet.handle(inputBuffer, connection);

		assertEquals(String.format(LoginServiceImpl.CLIENT_OUT_OF_DATE_ERROR_FORMAT, CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION), errPacket.getValue().getMessage());
	}

	@Test
	public void testHandleBannedCharacter() throws Exception {
		writeLogin(BANNED_CHARACTER_NAME, BANNED_CHARACTER_PASSWORD,
				CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "");
		packet.handle(inputBuffer, connection);

		assertEquals(LoginServiceImpl.BANNED_CHARACTER_ERROR, errPacket.getValue().getMessage());
	}

}
