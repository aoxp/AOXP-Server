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

import com.ao.context.ApplicationContext;
import com.ao.model.character.UserCharacter;
import com.ao.service.UserService;

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
	 * @see com.ao.model.user.Account#getCharacter(java.lang.String)
	 */
	@Override
	public UserCharacter getCharacter(String name) {
		return ApplicationContext.getInstance(UserService.class).getCharacter(name);
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
}
