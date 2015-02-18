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

/**
 * A user account.
 */
public class AccountImpl implements Account {

	private String name;
	private String password;
	private String mail;

	protected Set<String> characters;
	private boolean banned;

	/**
	 * Creates a new account instance.
	 * @param name The name of the account.
	 * @param password The account's password.
	 * @param mail The account's email.
	 * @param characters The account's characters.
	 * @param banned Wether the account is banned or not.
	 */
	public AccountImpl(String name, String password, String mail, Set<String> characters, boolean banned) {
		this.name = name;
		this.password = password;
		this.mail = mail;
		this.characters = characters;
		this.banned = banned;
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#getMail()
	 */
	@Override
	public String getMail() {
		return mail;
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#getCharacters()
	 */
	@Override
	public Set<String> getCharacters() {
		return characters;
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#hasCharacter(java.lang.String)
	 */
	@Override
	public boolean hasCharacter(String name) {
		String nameLower = name.toLowerCase();

		for (String character : characters) {
			if (nameLower.equals(character.toLowerCase())) {
				return true;
			}
		}

		return false;
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#isBanned()
	 */
	@Override
	public boolean isBanned() {
		return banned;
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#authenticate(java.lang.String)
	 */
	@Override
	public boolean authenticate(String password) {
		return this.password.toLowerCase().equals(password.toLowerCase());
	}

	/* (non-Javadoc)
	 * @see com.ao.model.user.Account#addCharacter(java.lang.String)
	 */
	@Override
	public void addCharacter(String name) {
		characters.add(name);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.user.Account#setBanned(boolean)
	 */
	@Override
	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final AccountImpl other = (AccountImpl) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
}
