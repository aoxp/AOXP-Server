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

package com.ao.service.login;

import com.ao.config.ServerConfig;
import com.ao.context.ApplicationContext;
import com.ao.data.dao.AccountDAO;
import com.ao.data.dao.UserCharacterDAO;
import com.ao.data.dao.exception.DAOException;
import com.ao.data.dao.exception.NameAlreadyTakenException;
import com.ao.model.character.Attribute;
import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.user.Account;
import com.ao.model.user.ConnectedUser;
import com.ao.security.SecurityManager;
import com.ao.service.LoginService;
import com.ao.service.ValidatorService;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class LoginServiceImpl implements LoginService {
	
	public static final String DAO_ERROR = "Ocurrió un error, intentá de nuevo.";
	public static final String CHARACTER_NOT_FOUND_ERROR = "El personaje no existe.";
	public static final String CLIENT_OUT_OF_DATE_ERROR_FORMAT = "Esta versión del juego es obsoleta, la versión correcta es %s. La misma se encuentra disponible en http://www.argentumonline.com.ar/.";
	public static final String CORRUPTED_CLIENT_ERROR = "El cliente está dañado, por favor descárguelo nuevamente desde http://www.argentumonline.com.ar/";
	public static final String BANNED_CHARACTER_ERROR = "Se te ha prohibido la entrada a Argentum debido a tu mal comportamiento. Puedes consultar el reglamento y el sistema de soporte desde www.argentumonline.com.ar";
	public static final String INCORRECT_PASSWORD_ERROR = "Contraseña incorrecta.";
	public static final String CHARACTER_CREATION_DISABLED_ERROR = "La creación de personajes en este servidor se ha deshabilitado.";
	public static final String ONLY_ADMINS_ERROR = "Servidor restringido a administradores. Consulte la página oficial o el foro oficial para más información.";
	public static final String MUST_THROW_DICES_BEFORE_ERROR = "Debe tirar los dados antes de poder crear un personaje.";
	public static final String INVALID_RACE_ERROR = "La raza seleccionada no es válida.";
	public static final String INVALID_GENDER_ERROR = "El género seleccionado no es válido.";
	public static final String INVALID_ARCHETYPE_ERROR = "La clase seleccionada no es válida.";
	public static final String INVALID_SKILLS_POINTS_ERROR = "Los skills asignados no son válidos.";
	public static final String ACCOUNT_NAME_TAKEN_ERROR = "Ya existe el personaje.";
	public static final String INVALID_NAME_ERROR = "El nombre ingresado no es válido.";
	public static final String INVALID_EMAIL_ERROR = "La dirección de e-mail ingresada no es válida.";
	
	private static final int INITIAL_SKILL_POINTS = 10;
	
	private String[] clientHashes;
	// FIXME : Have these injected in a constructor by Guice, not hardwired like this!
	private final AccountDAO accDAO = ApplicationContext.getInstance(AccountDAO.class);
	private final UserCharacterDAO charDAO = ApplicationContext.getInstance(UserCharacterDAO.class);
	private final ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
	private String currentClientVersion = config.getVersion();
	
	private int initialAvailableSkills;
	
	
	
	@Override
	public void connectNewCharacter(ConnectedUser user, String username, String password, byte bRace,
			byte bGender, byte bArchetype, int head, String mail, 
			byte homeland, String clientHash,
			String version) throws LoginErrorException {
		
		checkClient(clientHash, version);

		if (!config.isCharacterCreationEnabled()) {
			throw new LoginErrorException(CHARACTER_CREATION_DISABLED_ERROR);
		}
		
		if (config.isRestrictedToAdmins()) {
			throw new LoginErrorException(ONLY_ADMINS_ERROR);
		}
		
		if (user.getAttribute(Attribute.AGILITY) == null) {
			throw new LoginErrorException(MUST_THROW_DICES_BEFORE_ERROR);
		}
		
		if (!ValidatorService.validCharacterName(username)) {
			throw new LoginErrorException(INVALID_NAME_ERROR);
		}
		
		if (!ValidatorService.validEmail(mail)) {
			throw new LoginErrorException(INVALID_EMAIL_ERROR);
		}
		
		Race race;
		
		try {
			race = Race.get(bRace);
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new LoginErrorException(INVALID_RACE_ERROR);
		}
		
		Gender gender;
		
		try {
			gender = Gender.get(bGender);
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new LoginErrorException(INVALID_GENDER_ERROR);
		}
		
		UserArchetype archetype;
		
		try {
			archetype = UserArchetype.get(bArchetype);
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new LoginErrorException(INVALID_ARCHETYPE_ERROR);
		}
		
		// TODO: Check for valid homeland.
		
		// TODO: Check for valid head
		
		// First, we have to create the new account.
		Account acc;
		
		try {
			acc = accDAO.create(username, password, mail);
		} catch(NameAlreadyTakenException e) {
			throw new LoginErrorException(ACCOUNT_NAME_TAKEN_ERROR);
			
		} catch (DAOException e) {
			accDAO.delete(username);
			
			throw new LoginErrorException(DAO_ERROR);
		}
			
		
		// Once we have the account, lets create the character itself!
		try {
			UserCharacter chara = charDAO.create(username, race, gender, archetype,
					head, homeland, user.getAttribute(Attribute.STRENGTH), 
					user.getAttribute(Attribute.AGILITY), user.getAttribute(Attribute.INTELLIGENCE),
					user.getAttribute(Attribute.CHARISMA), user.getAttribute(Attribute.CONSTITUTION),
					initialAvailableSkills);
		} catch (DAOException e) {
			accDAO.delete(username);
			
			throw new LoginErrorException(e.getMessage());
		}
		
		// Everything it's okay, associate the character with the account and the account with the user.
		acc.addCharacter(username);
		user.setAccount(acc);
		
		// TODO: Put it in the world!
		
	}
	
	@Inject
	public LoginServiceImpl(@Named("initialAvailableSkills") int initialAvailableSkills) {
		super();
		this.initialAvailableSkills = initialAvailableSkills;
	}

	@Override
	public void connectExistingCharacter(ConnectedUser user, String name, String password, String version,
			String clientHash) throws LoginErrorException {
		
		checkClient(clientHash, version);
		
		if (config.isRestrictedToAdmins()) {
			throw new LoginErrorException(ONLY_ADMINS_ERROR);
		}
		
		Account acc;
		
		try {
			acc = accDAO.retrieve(name);
		} catch (DAOException e) {
			throw new LoginErrorException(DAO_ERROR);
		}
		
		if (acc == null) {
			throw new LoginErrorException(CHARACTER_NOT_FOUND_ERROR);
		}
		
		if (acc.isBanned()) {
			throw new LoginErrorException(BANNED_CHARACTER_ERROR);
		}
		
		if (!acc.authenticate(password)) {
			throw new LoginErrorException(INCORRECT_PASSWORD_ERROR);
		}
	}
	
	/**
	 * Sets the current client's version.
	 * @param version The new client version.
	 */
	public void setCurrentClientVersion(String version) {
		// TODO: Update the config!
		currentClientVersion = version;
	}
	
	/**
	 * Checks if the given hash matches any of the valid hashes and if the given version is up to date.
	 * @param hash 	The hash to check.
	 * @param version The client's version.
	 * @throws LoginErrorException
	 */
	private void checkClient(String hash, String version) throws LoginErrorException {
		
		if (!currentClientVersion.equals(version)) {
			throw new LoginErrorException(String.format(CLIENT_OUT_OF_DATE_ERROR_FORMAT, currentClientVersion));
		}
		
		if (clientHashes == null) {
			clientHashes = ApplicationContext.getInstance(SecurityManager.class).getValidClientHashes();
		}
		
		if (clientHashes.length < 1) {
			return;
		}
		
		for (String validHash : clientHashes) {
			if (hash.equals(validHash)) {
				return;
			}
		}
		
		throw new LoginErrorException(CORRUPTED_CLIENT_ERROR);
	}
	
}
