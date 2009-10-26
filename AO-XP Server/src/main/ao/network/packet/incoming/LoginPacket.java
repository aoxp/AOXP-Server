package ao.network.packet.incoming;

import java.io.UnsupportedEncodingException;
import java.nio.BufferUnderflowException;

import ao.config.ServerConfig;
import ao.context.ApplicationContext;
import ao.data.dao.AccountDAO;
import ao.data.dao.DAOException;
import ao.model.user.Account;
import ao.network.Connection;
import ao.network.DataBuffer;
import ao.network.ServerPacketsManager;
import ao.network.packet.IncomingPacket;
import ao.network.packet.outgoing.ErrorMessagePacket;

public class LoginPacket implements IncomingPacket {

	protected static final int HASH_ASCII_LENGTH = 32;
	protected static final int HASH_BINARY_LENGTH = 16;
	
	public static final String DAO_ERROR_MESSAGE = "Ocurrió un error, intentá de nuevo.";
	public static final String CHARACTER_NOT_FOUND_ERROR_MESSAGE = "El personaje no existe.";
	public static final String CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT = "Esta versión del juego es obsoleta, la versión correcta es %s. La misma se encuentra disponible en http://www.argentumonline.com.ar/.";
	public static final String CORRUPTED_CLIENT_ERROR_MESSAGE = "El cliente está dañado, por favor descárguelo nuevamente desde http://www.argentumonline.com.ar/";
	public static final String BANNED_CHARACTER_ERROR_MESSAGE = "Se te ha prohibido la entrada a Argentum debido a tu mal comportamiento. Puedes consultar el reglamento y el sistema de soporte desde www.argentumonline.com.ar";
	public static final String INCORRECT_PASSWORD_ERROR_MESSAGE = "Contraseña incorrecta.";
	
	private static String[] clientHashes;
	private static final AccountDAO accDAO = ApplicationContext.getInstance(AccountDAO.class);
	private static String correctVersion = ApplicationContext.getInstance(ServerConfig.class).getVersion();
	
	@Override
	public void handle(Connection connection) throws BufferUnderflowException, UnsupportedEncodingException {
		DataBuffer buffer = connection.getInputBuffer();
		
		String username = buffer.getASCIIString();
		Account acc;
		
		try {
			acc = accDAO.retrieve(username);
		} catch (DAOException e) {
			loginError(connection, DAO_ERROR_MESSAGE);
			return;
		}
		
		if (acc == null) {
			loginError(connection, CHARACTER_NOT_FOUND_ERROR_MESSAGE);
			return;
		}
		
		String password;
		if (ApplicationContext.SECURITY_ENABLED) {
			password = buffer.getASCIIStringFixed(HASH_ASCII_LENGTH);
		} else {
			password = buffer.getASCIIString();
		}
		
		String version = buffer.getShort() + "." + buffer.getShort() + "." + buffer.getShort();
		
		if (!correctVersion.equals(version)) {
			loginError(connection, String.format(CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT, correctVersion));
			return;
		}
		
		// TODO: This versions are deprecated, delete this when the client don't send them anymore.
		for (int i = 0; i < 7; i++) {
			buffer.getShort();
		}
		
		if (ApplicationContext.SECURITY_ENABLED) {
			if (!checkClientHash(buffer.getASCIIStringFixed(HASH_BINARY_LENGTH))) {
				loginError(connection, CORRUPTED_CLIENT_ERROR_MESSAGE);
				return;
			}
		}
		
		if (acc.isBanned(username)) {
			loginError(connection, BANNED_CHARACTER_ERROR_MESSAGE);
			return;
		}
		
		if (!acc.authenticate(password)) {
			loginError(connection, INCORRECT_PASSWORD_ERROR_MESSAGE);
			return;
		}
		
		// TODO: Log him in!
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
	
	/**
	 * Checks if the given hash matches any of the valid hashes.
	 * @param hash The hash to check.
	 * @return True if the hash match, false otherwise.
	 */
	private boolean checkClientHash(String hash) {
		if (clientHashes == null) {
			clientHashes = ApplicationContext.getInstance(ServerConfig.class).getValidClientHashes();
		}
		
		for (String validHash : clientHashes) {
			if (hash.equals(validHash)) {
				return true;
			}
		}
		
		return false;
	}
}
