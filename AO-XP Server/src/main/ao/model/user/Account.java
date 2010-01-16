package ao.model.user;

import ao.model.character.UserCharacter;

public interface Account {

	/**
	 * @return the name
	 */
	String getName();

	/**
	 * @return the mail
	 */
	String getMail();

	/**
	 * @return the characters
	 */
	String[] getCharacters();

	/**
	 * Checks if the a character with the given name exists in this account.
	 * @param name The name of the character for which to check.
	 * @return True if the character exists in the account, false otherwise.
	 */
	boolean hasCharacter(String name);

	/**
	 * Checks wether the account is banned or not.
	 * @return True if the account is banned, false otherwise.
	 */
	boolean isBanned();

	/**
	 * Retrieves the character with the given name.
	 * @param name The name of the character to be retrieved.
	 * @return The requested character.
	 */
	UserCharacter getCharacter(String name);

	/**
	 * Try to authenticate the account with the given password.
	 * @param password The password used to authenticate.
	 * @return True if the password matchs the account password, false otherwise.
	 */
	boolean authenticate(String password);

	/**
	 * Adds another character to the account.
	 * @param name The character's name.
	 */
	void addCharacter(String name);

}