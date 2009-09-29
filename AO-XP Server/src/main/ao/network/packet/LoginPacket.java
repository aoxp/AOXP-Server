package ao.network.packet;

import ao.network.Connection;
import ao.network.DataBuffer;

public class LoginPacket implements Packet {

	@Override
	public void handle(Connection connection) {
		DataBuffer buffer = connection.getInputBuffer();
		
		// TODO: Check if is valid
		String username = buffer.getASCIIString();
		// TODO: Check if the account exists.
		
		String password = buffer.getASCIIString();
		int minor = buffer.getInt();
		int major = buffer.getInt();
		int elotro = buffer.getInt();
		
		// TODO: Check if the client is out of date.
		int[] versions = new int[7];
		for (int i = 0; i < 7; i++) {
			versions[i] = buffer.getInt();
		}
		
		// TODO: Check for ban.
		// TODO: Log him in!
	}

}
