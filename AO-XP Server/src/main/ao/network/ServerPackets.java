package ao.network;

import ao.network.packet.LoginPacket;
import ao.network.packet.Packet;

public enum ServerPackets {
	LOGIN(LoginPacket.class);
	
	private Packet handler;
	
	private ServerPackets(Class<? extends Packet> handler) {
		try {
			this.handler = handler.getConstructor().newInstance();
		} catch(Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public Packet getHandler() {
		return handler;
	}


}
