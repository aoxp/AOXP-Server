package ao.network;

import ao.network.packet.LoginPacket;
import ao.network.packet.Packet;

/**
 * Enumerates server packets.
 */
public enum ServerPackets {
	LOGIN(LoginPacket.class);
	
	private Packet handler;
	
	/**
	 * Creates a new server packet.
	 * @param handler The handler for the given packet.
	 */
	private ServerPackets(Class<? extends Packet> handler) {
		try {
			this.handler = handler.getConstructor().newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * Retrieves the packet's handler.
	 * @return The packet's handler.
	 */
	public Packet getHandler() {
		return handler;
	}


}
