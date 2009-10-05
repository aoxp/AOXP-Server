package ao.network;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.network.packet.IncomingPacket;
import ao.network.packet.incoming.LoginPacket;

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