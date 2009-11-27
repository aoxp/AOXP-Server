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
