package ao.network.packet.incoming;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.context.ApplicationContext;
import ao.network.Connection;
import ao.network.DataBuffer;
import ao.network.ServerPacketsManager;
import ao.network.packet.IncomingPacket;
import ao.network.packet.outgoing.ErrorMessagePacket;
import ao.security.Hashing;
import ao.service.LoginService;
import ao.service.LoginService.LoginErrorException;

public class LoginExistingCharacterPacket implements IncomingPacket {

	@Override
	public void handle(Connection connection) throws BufferUnderflowException, UnsupportedEncodingException {
		DataBuffer buffer = connection.getInputBuffer();
		
		String username = buffer.getASCIIString();
		
		String password;
		
		if (ApplicationContext.SECURITY_ENABLED) {
			password = buffer.getASCIIStringFixed(Hashing.MD5_ASCII_LENGTH);
		} else {
			password = buffer.getASCIIString();
		}
		
		String version = buffer.getShort() + "." + buffer.getShort() + "." + buffer.getShort();
		String clientHash = "";
		
		if (ApplicationContext.SECURITY_ENABLED) {
			clientHash = buffer.getASCIIStringFixed(Hashing.MD5_BINARY_LENGTH);
		}
		
		try {
			LoginService.existingCharacter(username, password, version, clientHash);
		} catch(LoginErrorException e) {
			loginError(connection, e.getMessage());
		}
		
	}

	/**
	 * Notifies the client about the error and closes the connection.
	 * @param connection The connection where the login error has occurred.
	 * @param cause 	 The error cause.
	 * @throws UnsupportedEncodingException
	 */
	private void loginError(Connection connection, String cause) throws UnsupportedEncodingException {
		ServerPacketsManager.write(new ErrorMessagePacket(cause), connection.getOutputBuffer());
		connection.disconnect();
	}

}
