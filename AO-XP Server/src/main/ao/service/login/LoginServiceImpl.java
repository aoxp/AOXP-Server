/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ao.service.login;

import ao.config.ServerConfig;
import ao.context.ApplicationContext;
import ao.data.dao.AccountDAO;
import ao.data.dao.DAOException;
import ao.data.dao.UserCharacterDAO;
import ao.model.character.Attribute;
import ao.model.character.Gender;
import ao.model.character.Race;
import ao.model.character.UserCharacter;
import ao.model.character.archetype.UserArchetype;
import ao.model.user.Account;
import ao.model.user.ConnectedUser;
import ao.service.LoginService;

public class LoginServiceImpl implements LoginService {
	
	public static final String DAO_ERROR_MESSAGE = "Ocurrió un error, intentá de nuevo.";
	public static final String CHARACTER_NOT_FOUND_ERROR_MESSAGE = "El personaje no existe.";
	public static final String CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT = "Esta versión del juego es obsoleta, la versión correcta es %s. La misma se encuentra disponible en http://www.argentumonline.com.ar/.";
	public static final String CORRUPTED_CLIENT_ERROR_MESSAGE = "El cliente está dañado, por favor descárguelo nuevamente desde http://www.argentumonline.com.ar/";
	public static final String BANNED_CHARACTER_ERROR_MESSAGE = "Se te ha prohibido la entrada a Argentum debido a tu mal comportamiento. Puedes consultar el reglamento y el sistema de soporte desde www.argentumonline.com.ar";
	public static final String INCORRECT_PASSWORD_ERROR_MESSAGE = "Contraseña incorrecta.";
	public static final String CHARACTER_CREATION_DISABLED_ERROR = "La creación de personajes en este servidor se ha deshabilitado.";
	public static final String ONLY_ADMINS_ERROR = "Servidor restringido a administradores. Consulte la página oficial o el foro oficial para mas información.";
	public static final String MUST_THROW_DICES_BEFORE = "Debe tirar los dados antes de poder crear un personaje.";
	
	private String[] clientHashes;
	private final AccountDAO accDAO = ApplicationContext.getInstance(AccountDAO.class);
	private final UserCharacterDAO charDAO = ApplicationContext.getInstance(UserCharacterDAO.class);
	private final ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
	private String currentClientVersion = config.getVersion();
	
	@Override
	public void connectNewCharacter(ConnectedUser user, String username, String password, byte race,
			byte gender, byte archetype, byte[] skills, String mail, 
			byte homeland, String clientHash,
			String version) throws LoginErrorException {
		
		// TODO: Maybe this can be done outside the service to avoid the useless buffer read?
		checkClient(clientHash, version);
		
		if (!config.isCharacterCreationEnabled()) {
			throw new LoginErrorException(CHARACTER_CREATION_DISABLED_ERROR);
		}
		
		if (config.isRestrictedToAdmins()) {
			throw new LoginErrorException(ONLY_ADMINS_ERROR);
		}
		
		if (user.getAttribute(Attribute.AGILITY) == null) {
			throw new LoginErrorException(MUST_THROW_DICES_BEFORE);
		}
		
		// TODO: Check for too much created characters?
		// TODO: Check for valid homeland, race, archetype, gender, name and mail.
		
		// First, we have to create de new account.
		Account acc;
		
		try {
			acc = accDAO.create(username, password, mail);
		} catch (DAOException e) {
			accDAO.delete(username);
			
			throw new LoginErrorException(DAO_ERROR_MESSAGE);
		}
		
		// Once we have the account, lets create the character itself!
		try {
			UserCharacter chara = charDAO.create(username, Race.get(race), Gender.get(gender),
					UserArchetype.get(archetype), skills, homeland, user.getAttribute(Attribute.STRENGTH), 
					user.getAttribute(Attribute.AGILITY), user.getAttribute(Attribute.INTELLIGENCE),
					user.getAttribute(Attribute.CHARISMA), user.getAttribute(Attribute.CONSTITUTION) );
		} catch (DAOException e) {
			accDAO.delete(username);
			
			throw new LoginErrorException(DAO_ERROR_MESSAGE);
		}
		
		// Everything it's okay, associate the character with the account and the account with the user.
		acc.addCharacter(username);
		user.setAccount(acc);
		
		// TODO: Put it in the world!
		
	}
	
	@Override
	public void connectExistingCharacter(ConnectedUser user, String name, String password, String version,
			String clientHash) throws LoginErrorException {
		
		checkClient(clientHash, version);
		
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
	 * Checks if the given hash matches any of the valid hashes and if the given version is up to date.
	 * @param hash 	The hash to check.
	 * @param version The client's version.
	 * @throws LoginErrorException
	 */
	private void checkClient(String hash, String version) throws LoginErrorException {
		
		if (!currentClientVersion.equals(version)) {
			throw new LoginErrorException(String.format(CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT, currentClientVersion));
		}
		
		if (hash.equals("")) {
			return;
		}
		
		if (clientHashes == null) {
			clientHashes = config.getValidClientHashes();
		}
		
		for (String validHash : clientHashes) {
			if (hash.equals(validHash)) {
				return;
			}
		}
		
		throw new LoginErrorException(CORRUPTED_CLIENT_ERROR_MESSAGE);
	}
	
}
