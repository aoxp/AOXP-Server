package ao.network.packet.incoming;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.context.ApplicationContext;
import ao.data.dao.AccountDAO;
import ao.model.user.Account;
import ao.network.Connection;
import ao.network.DataBuffer;
import ao.network.ServerPacketsManager;
import ao.network.packet.IncomingPacket;
import ao.network.packet.OutgoingPacket;
import ao.network.packet.outgoing.ErrorMessagePacket;

public class LoginPacket implements IncomingPacket {

	@Override
	public void handle(Connection connection) throws BufferUnderflowException, UnsupportedEncodingException {
		DataBuffer buffer = connection.getInputBuffer();
		
		String username = buffer.getASCIIString();
		Account acc = ApplicationContext.getInstance(AccountDAO.class).read(username);
		
		if (acc == null) {
			OutgoingPacket packet = new ErrorMessagePacket("El personaje no existe.");
			
			ServerPacketsManager.write(packet, connection.getOutputBuffer());
			connection.disconnect();
			
			return;
		}
		
		if (ApplicationContext.SECURTY_ENABLED) {
			String password = buffer.getASCIIStringFixed(32);
		} else {
			String password = buffer.getASCIIString();
		}
		
		int minor = buffer.getShort();
		int major = buffer.getShort();
		int revision = buffer.getShort();
		
		// TODO: Check if the client is out of date.
		short[] versions = new short[7];
		for (int i = 0; i < 7; i++) {
			versions[i] = buffer.getShort();
		}
		
		if (acc.isBanned(username)) {
			OutgoingPacket packet = new ErrorMessagePacket("Se te ha prohibido la entrada a Argentum debido a tu mal comportamiento. Puedes consultar el reglamento y el sistema de soporte desde www.argentumonline.com.ar");
			
			ServerPacketsManager.write(packet, connection.getOutputBuffer());
			connection.disconnect();
			return;
		}
		
		// TODO: Check the password.
		// TODO: Log him in!
	}

}
