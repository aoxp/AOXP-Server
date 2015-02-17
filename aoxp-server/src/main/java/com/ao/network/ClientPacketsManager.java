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

package com.ao.network;

import java.io.UnsupportedEncodingException;

import com.ao.network.packet.IncomingPacket;
import com.ao.network.packet.incoming.LoginExistingCharacterPacket;
import com.ao.network.packet.incoming.LoginNewCharacterPacket;
import com.ao.network.packet.incoming.ThrowDicesPacket;
import com.ao.network.packet.incoming.WalkPacket;

/**
 * Manager for client-side packets.
 */
public class ClientPacketsManager {

	/**
	 * Enumerates client packets.
	 */
	private enum ClientPackets {
		LOGIN_EXISTING_CHARACTER(LoginExistingCharacterPacket.class),
		THROW_DICE(ThrowDicesPacket.class),
		LOGIN_NEW_CHARACTER(LoginNewCharacterPacket.class),
		TALK(null),
		YELL(null),
		WHISPER(null),
		WALK(WalkPacket.class);

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
	 * @param buffer The buffer from which to read data.
	 * @return True if a packet could be processed, false otherwise.
	 * @throws UnsupportedEncodingException
	 * @throws ArrayIndexOutOfBoundsException
	 */
	public static boolean handle(DataBuffer buffer, Connection connection) throws UnsupportedEncodingException,
		ArrayIndexOutOfBoundsException {

		return packets[buffer.get()].handler.handle(buffer, connection);
	}
}