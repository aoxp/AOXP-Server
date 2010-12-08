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

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import com.ao.context.ApplicationContext;
import com.ao.model.character.Skill;
import com.ao.model.user.ConnectedUser;
import com.ao.network.Connection;
import com.ao.network.DataBuffer;
import com.ao.network.ServerPacketsManager;
import com.ao.network.packet.IncomingPacket;
import com.ao.network.packet.outgoing.ErrorMessagePacket;
import com.ao.security.SecurityManager;
import com.ao.service.LoginService;
import com.ao.service.login.LoginErrorException;

public class LoginNewCharacterPacket implements IncomingPacket {
	
	private static LoginService service = ApplicationContext.getInstance(LoginService.class);
	private static SecurityManager security = ApplicationContext.getInstance(SecurityManager.class);
	
	@Override
	public void handle(Connection connection) throws BufferUnderflowException,
			UnsupportedEncodingException {
		
		DataBuffer buffer = connection.getInputBuffer();
		
		String nick = buffer.getASCIIString();
		String password = buffer.getASCIIStringFixed(security.getPasswordHashLength());
		
		String version = buffer.get() + "." + buffer.get() + "." + buffer.get();
		String clientHash = buffer.getASCIIStringFixed(security.getClientHashLength());
		
		
		byte race = buffer.get();
		byte gender = buffer.get();
		byte archetype = buffer.get();
		int head = buffer.get();
		String mail = buffer.getASCIIString();
		byte homeland = buffer.get();
		
		try {
			service.connectNewCharacter((ConnectedUser) connection.getUser(), 
					nick, password, race, gender, archetype, head, mail,
					homeland, clientHash, version);
			
		} catch (LoginErrorException e) {
			loginError(connection, e.getMessage());
		}
		
	}

	/**
	 * Notifies the client about the error and closes the connection.
	 * @param connection The connection where the login error has occurred.
	 * @param cause 	 The error cause.
	 * @throws UnsupportedEncodingException
	 */
	private void loginError(Connection connection, String cause) throws UnsupportedEncodingException {
		ServerPacketsManager.write(new ErrorMessagePacket(cause), connection.getOutputBuffer());
		connection.disconnect();
	}
}
