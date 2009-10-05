package ao.network.packet;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.network.Connection;

public interface IncomingPacket {

	/**
	 * Handles the received packet.
	 * @param connection The packet's connection container.
	 */
	void handle(Connection connection) throws BufferUnderflowException, UnsupportedEncodingException;
	
}
