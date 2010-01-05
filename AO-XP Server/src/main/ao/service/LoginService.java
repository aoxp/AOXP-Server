package ao.service;

import ao.config.ServerConfig;
import ao.context.ApplicationContext;
import ao.data.dao.AccountDAO;
import ao.data.dao.DAOException;
import ao.model.character.Gender;
import ao.model.character.Race;
import ao.model.character.archetype.Archetype;
import ao.model.user.Account;
import ao.security.Hashing;

public class LoginService {
	
	public static final String DAO_ERROR_MESSAGE = "Ocurrió un error, intentá de nuevo.";
	public static final String CHARACTER_NOT_FOUND_ERROR_MESSAGE = "El personaje no existe.";
	public static final String CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT = "Esta versión del juego es obsoleta, la versión correcta es %s. La misma se encuentra disponible en http://www.argentumonline.com.ar/.";
	public static final String CORRUPTED_CLIENT_ERROR_MESSAGE = "El cliente está dañado, por favor descárguelo nuevamente desde http://www.argentumonline.com.ar/";
	public static final String BANNED_CHARACTER_ERROR_MESSAGE = "Se te ha prohibido la entrada a Argentum debido a tu mal comportamiento. Puedes consultar el reglamento y el sistema de soporte desde www.argentumonline.com.ar";
	public static final String INCORRECT_PASSWORD_ERROR_MESSAGE = "Contraseña incorrecta.";
	public static final String CHARACTER_CREATION_DISABLED_ERROR = "La creación de personajes en este servidor se ha deshabilitado.";
	public static final String ONLY_ADMINS_ERROR = "Servidor restringido a administradores. Consulte la página oficial o el foro oficial para mas información.";
	
	private static String[] clientHashes;
	private static final AccountDAO accDAO = ApplicationContext.getInstance(AccountDAO.class);
	private static final ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
	private static String currentClientVersion = config.getVersion();
	
	public static class LoginErrorException extends Exception {

		public LoginErrorException(String message) {
			super(message);
		}
	}
	
	public static void newCharacter(String username, String password, byte race,
			byte gender, byte archetype, byte[] skills, String mail, 
			byte homeland, String clientHash, String version) throws LoginErrorException {
		
		// TODO: Maybe this can be done outside the service to avoid the useless buffer read?
		
		if (!currentClientVersion.equals(version)) {
			throw new LoginErrorException(String.format(CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT, currentClientVersion));
		}
		
		if (!clientHash.equals("")) {
			if (!checkClientHash(clientHash)) {
				throw new LoginErrorException(CORRUPTED_CLIENT_ERROR_MESSAGE);
			}
		}
		
		if (!config.isCharacterCreationEnabled()) {
			throw new LoginErrorException(CHARACTER_CREATION_DISABLED_ERROR);
		}
		
		if (config.isRestrictedToAdmins()) {
			throw new LoginErrorException(ONLY_ADMINS_ERROR);
		}
		
		// TODO: Check for too much created characters?
		
		// TODO: Persist the character.
		// TODO: Put it in the world!
		
	}
	public static void existingCharacter(String name, String password,
			String version, String clientHash) throws LoginErrorException {
		
		
		if (!currentClientVersion.equals(version)) {
			throw new LoginErrorException(String.format(CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT, currentClientVersion));
		}
		
		if (!clientHash.equals("")) {
			if (!checkClientHash(clientHash)) {
				throw new LoginErrorException(CORRUPTED_CLIENT_ERROR_MESSAGE);
			}
		}
		
		Account acc;
		
		try {
			acc = accDAO.retrieve(name);
		} catch (DAOException e) {
			throw new LoginErrorException(DAO_ERROR_MESSAGE);
		}
		
		if (acc == null) {
			throw new LoginErrorException(CHARACTER_NOT_FOUND_ERROR_MESSAGE);
		}
		
		if (acc.isBanned()) {
			throw new LoginErrorException(BANNED_CHARACTER_ERROR_MESSAGE);
		}
		
		if (!acc.authenticate(password)) {
			throw new LoginErrorException(INCORRECT_PASSWORD_ERROR_MESSAGE);
		}
	}
	
	/**
	 * Checks if the given hash matches any of the valid hashes.
	 * @param hash The hash to check.
	 * @return True if the hash match, false otherwise.
	 */
	private static boolean checkClientHash(String hash) {
		if (clientHashes == null) {
			clientHashes = config.getValidClientHashes();
		}
		
		for (String validHash : clientHashes) {
			if (hash.equals(validHash)) {
				return true;
			}
		}
		
		return false;
	}
}
