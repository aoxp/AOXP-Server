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
public class AccountImpl implements Account {

	private String name;
	private String password;
	private String mail;
	
	// In this Account implementation only can exist one character per account.
	private String character;
	private boolean banned;
	
	/**
	 * Creates a new account instance.
	 * @param name The name of the account.
	 * @param password The account's password.
	 * @param mail The account's email.
	 * @param character The account's character.
	 * @param banned Wether the account is banned or not.
	 */
	public AccountImpl(String name, String password, String mail, String character, boolean banned) {
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.character = character;
		this.banned = banned;
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#getName()
	 */
	public String getName() {
		return name;
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#getMail()
	 */
	public String getMail() {
		return mail;
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#getCharacters()
	 */
	public String[] getCharacters() {
		return new String[] {character};
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#hasCharacter(java.lang.String)
	 */
	public boolean hasCharacter(String name) {
		return name.toLowerCase().equals(character.toLowerCase());
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#isBanned()
	 */
	public boolean isBanned() {
		return banned;
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#getCharacter(java.lang.String)
	 */
	public UserCharacter getCharacter(String name) {
		// TODO : Fill this in!
		return null;
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#authenticate(java.lang.String)
	 */
	public boolean authenticate(String password) {
		return this.password.toLowerCase().equals(password.toLowerCase());
	}
	
	/* (non-Javadoc)
	 * @see ao.model.user.Account#addCharacter(java.lang.String)
	 */
	public void addCharacter(String name) {
		character = name;
	}
}
