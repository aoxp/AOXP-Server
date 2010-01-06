package ao.service;

import ao.service.login.LoginErrorException;

public interface LoginService {
	
	/**
	 * Attempts to connect the user using the given name and password.
	 * 
	 * @param name 			The character's name.
	 * @param password 		The character's password.
	 * @param version 		The client's version.
	 * @param clientHash 	The client's integrity check hash.
	 * @throws LoginErrorException
	 */
	void connectExistingCharacter(String name, String password, String version,
			String clientHash) throws LoginErrorException;
	
	/**
	 * Attempts to connect a new character creating it with the given data.
	 * 
	 * @param username 		The character's name.
	 * @param password 		The character's password.
	 * @param race 			The character's race.
	 * @param gender 		The character's gender.
	 * @param archetype 	The character's archetype.
	 * @param skills 		The character's skills.
	 * @param mail 			The character's mail.
	 * @param homeland 		The character's homeland.
	 * @param clientHash 	The client's integrity check hash.
	 * @param version 		The client's version.
	 * @throws LoginErrorException
	 */
	void connectNewCharacter(String username, String password, byte race,
			byte gender, byte archetype, byte[] skills, String mail, 
			byte homeland, String clientHash, String version) throws LoginErrorException;
	
}
