package ao.network.packet;

import java.nio.BufferUnderflowException;

import ao.network.Connection;

public interface Packet {

	/**
	 * Handles the received packet.
	 * @param connection The packet's connection container.
	 */
	void handle(Connection connection) throws BufferUnderflowException;
	
}
