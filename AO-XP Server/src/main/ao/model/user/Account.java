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

package ao.model.user;

import ao.model.character.UserCharacter;

/**
 * A user account.
 */
public class Account {

	private String name;
	private String password;
	private String mail;
	private String[] characters;
	private boolean banned;
	
	/**
	 * Creates a new account instance.
	 * @param name The name of the account.
	 * @param password The account's password.
	 * @param mail The account's email.
	 * @param characters The list of characters in the account.
	 * @param banned Wether the account is banned or not.
	 */
	public Account(String name, String password, String mail, String[] characters, boolean banned) {
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.characters = characters;
		this.banned = banned;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the mail
	 */
	public String getMail() {
		return mail;
	}
	
	/**
	 * @return the characters
	 */
	public String[] getCharacters() {
		return characters;
	}
	
	/**
	 * Checks if the a character with the given name exists in this account.
	 * @param name The name of the character for which to check.
	 * @return True if the character exists in the account, false otherwise.
	 */
	public boolean hasCharacter(String name) {
		for (String charName : characters) {
			if (name.toUpperCase().equals(charName.toUpperCase())) {
				return true;
			}
		}
		
		return false;
	}
	
	/**
	 * Checks wether the account is banned or not.
	 * @return True if the account is banned, false otherwise.
	 */
	public boolean isBanned() {
		return banned;
	}
	
	/**
	 * Retrieves the character with the given name.
	 * @param name The name of the character to be retrieved.
	 * @return The requested character.
	 */
	public UserCharacter getCharacter(String name) {
		// TODO : Fill this in!
		return null;
	}
	
	/**
	 * Try to authenticate the account with the given password.
	 * @param password The password used to authenticate.
	 * @return True if the password matchs the account password, false otherwise.
	 */
	public boolean authenticate(String password) {
		return this.password.toLowerCase().equals(password.toLowerCase());
	}
	
}
