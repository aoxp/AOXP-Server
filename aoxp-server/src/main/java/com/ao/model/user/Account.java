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

package com.ao.model.user;

import java.util.Set;

import com.ao.model.character.UserCharacter;

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
	Set<String> getCharacters();

	/**
	 * Checks if the a character with the given name exists in this account. Check is case insensitive.
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
	 * Sets the account's ban status.
	 * @param banned Whether the account is banned, or not.
	 */
	void setBanned(boolean banned);

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