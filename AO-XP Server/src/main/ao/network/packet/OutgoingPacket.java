package ao.network.packet;

import java.io.UnsupportedEncodingException;

import ao.network.DataBuffer;

public interface OutgoingPacket {

	/**
	 * Writes the packet in the given buffer.
	 * @param buffer The buffer to write in.
	 */
	void write(DataBuffer buffer) throws UnsupportedEncodingException;
}
