package ao.network.packet;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.network.Connection;
import ao.network.DataBuffer;

public class LoginPacket implements Packet {

	@Override
	public void handle(Connection connection) throws BufferUnderflowException, UnsupportedEncodingException {
		DataBuffer buffer = connection.getInputBuffer();
		
		// TODO: Check if is valid
		String username = buffer.getASCIIString();
		// TODO: Check if the account exists.
		
		// TODO : The pass is fixed length!!
		String password = buffer.getASCIIString();
		
		int minor = buffer.getShort();
		int major = buffer.getShort();
		int revision = buffer.getShort();
		
		// TODO: Check if the client is out of date.
		short[] versions = new short[7];
		for (int i = 0; i < 7; i++) {
			versions[i] = buffer.getShort();
		}
		
		// TODO: Check for ban.
		// TODO: Log him in!
	}

}
