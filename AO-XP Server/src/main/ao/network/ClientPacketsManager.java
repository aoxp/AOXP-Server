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

package ao.network;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.network.packet.IncomingPacket;
import ao.network.packet.incoming.LoginPacket;

/**
 * Manager for client-side packets.
 */
public class ClientPacketsManager {

	/**
	 * Enumerates client packets.
	 */
	private enum ClientPackets {
		LOGIN(LoginPacket.class);
		
		protected IncomingPacket handler;

		private ClientPackets(Class<? extends IncomingPacket> handler) {
			try {
				this.handler = handler.getConstructor().newInstance();
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
	}
	
	/**
	 * Maps packets ids to their classes.
	 */
	protected static final ClientPackets[] packets = ClientPackets.values();
	
	/**
	 * Handles new data in the connection's incoming buffer.
	 * @param connection The connection's container.
	 * @throws BufferUnderflowException
	 * @throws UnsupportedEncodingException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static void handle(Connection connection) throws BufferUnderflowException, UnsupportedEncodingException,
		ArrayIndexOutOfBoundsException {
		
		packets[connection.inputBuffer.get()].handler.handle(connection);
	}
}