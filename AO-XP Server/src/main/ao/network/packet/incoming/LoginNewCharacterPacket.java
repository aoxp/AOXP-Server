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

package ao.network.packet.incoming;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.config.ServerConfig;
import ao.context.ApplicationContext;
import ao.network.Connection;
import ao.network.DataBuffer;
import ao.network.ServerPacketsManager;
import ao.network.packet.IncomingPacket;
import ao.network.packet.outgoing.ErrorMessagePacket;
import ao.security.Hashing;

public class LoginNewCharacterPacket implements IncomingPacket {

	public static final String CHARACTER_CREATION_DISABLE_ERROR = "La creación de personajes en este servidor se ha deshabilitado.";
	public static final String ONLY_ADMINS_ERROR = "Servidor restringido a administradores. Consulte la página oficial o el foro oficial para mas información.";
	public static final String CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT = "Esta versión del juego es obsoleta, la versión correcta es %s. La misma se encuentra disponible en http://www.argentumonline.com.ar/.";
	public static final String CORRUPTED_CLIENT_ERROR_MESSAGE = "El cliente está dañado, por favor descárguelo nuevamente desde http://www.argentumonline.com.ar/";
	
	private static ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
	private static String correctVersion = config.getVersion();
	
	@Override
	public void handle(Connection connection) throws BufferUnderflowException,
			UnsupportedEncodingException {
		
		if (!config.isCharacterCreationEnabled()) {
			loginError(connection, CHARACTER_CREATION_DISABLE_ERROR);
			return;
		}
		
		if (config.isRestrictedToAdmins()) {
			loginError(connection, ONLY_ADMINS_ERROR);
			return;
		}
		
		// TODO: Check for too much created characters.
		
		DataBuffer buffer = connection.getInputBuffer();
		
		String nick = buffer.getASCIIString();
		String password;
		
		if (ApplicationContext.SECURITY_ENABLED) {
			password = buffer.getASCIIStringFixed(Hashing.MD5_ASCII_LENGTH);
		} else {
			password = buffer.getASCIIString();
		}
		
		String version = buffer.get() + "." + buffer.get() + "." + buffer.get();

		if (ApplicationContext.SECURITY_ENABLED) {
			if (!LoginExistingCharacterPacket.checkClientHash(buffer.getASCIIStringFixed(Hashing.MD5_BINARY_LENGTH))) {
				loginError(connection, CORRUPTED_CLIENT_ERROR_MESSAGE);
				return;
			}
		}
		
		if (!correctVersion.equals(version)) {
			loginError(connection, String.format(CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT, correctVersion));
			return;
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
