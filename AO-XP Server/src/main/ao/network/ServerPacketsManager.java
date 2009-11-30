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
import java.util.HashMap;
import java.util.Map;

import ao.network.packet.OutgoingPacket;
import ao.network.packet.outgoing.ErrorMessagePacket;

/**
 * Manager for server-side packets.
 */
public class ServerPacketsManager {
	
	/**
	 * Enumerates server packets.
	 */
	private enum ServerPackets {
		ERROR_MESSAGE(ErrorMessagePacket.class);
		
		protected Class<? extends OutgoingPacket> packetClass;
		
		private ServerPackets(Class<? extends OutgoingPacket> packet) {
			this.packetClass = packet;
		}
	}
	
	/**
	 * Maps packets ids to their classes.
	 */
	protected static final ServerPackets[] packets = ServerPackets.values();
	
	/**
	 * Maps packets classes to their ids.
	 */
	protected static final Map<Class<? extends OutgoingPacket>, Integer> packetsMap = new HashMap<Class<? extends OutgoingPacket>, Integer>();
	
	static {
		for (ServerPackets packet : packets) {
			packetsMap.put(packet.packetClass, packet.ordinal());
		}
	}
	
	/**
	 * Writes the given packet in the given buffer.
	 * @param packet The packet to write.
	 * @param buffer The buffer where to write the packet.
	 * @throws UnsupportedEncodingException
	 */
	public static void write(OutgoingPacket packet, DataBuffer buffer) throws UnsupportedEncodingException {
		
		// Put the packet id before the packet itself.
		buffer.put(packetsMap.get(packet.getClass()).byteValue());
		packet.write(buffer);
	}
}
