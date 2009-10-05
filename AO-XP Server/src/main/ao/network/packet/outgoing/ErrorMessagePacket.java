package ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import ao.network.DataBuffer;
import ao.network.packet.OutgoingPacket;

public class ErrorMessagePacket implements OutgoingPacket {

	protected String message;
	
	/**
	 * Creates a new error message packet.
	 * @param message The message content.
	 */
	public ErrorMessagePacket(String message) {
		this.message = message;
	}

	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putASCIIStringFixed(message);
	}

}
