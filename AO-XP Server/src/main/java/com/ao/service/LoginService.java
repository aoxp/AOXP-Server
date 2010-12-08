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

package com.ao.service;

import com.ao.model.user.ConnectedUser;
import com.ao.service.login.LoginErrorException;

public interface LoginService {
	
	/**
	 * Attempts to connect the user using the given name and password.
	 * 
	 * @param user			The user trying to log in.
	 * @param name 			The character's name.
	 * @param password 		The character's password.
	 * @param version 		The client's version.
	 * @param clientHash 	The client's integrity check hash.
	 * @throws LoginErrorException
	 */
	void connectExistingCharacter(ConnectedUser user, String name, String password, String version,
			String clientHash) throws LoginErrorException;
	
	/**
	 * Attempts to connect a new character creating it with the given data.
	 * 
	 * @param user			The user trying to log in.
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
	 * @return 
	 * @throws LoginErrorException
	 */
	void connectNewCharacter(ConnectedUser user, String username, String password, byte race,
			byte gender, byte archetype, byte[] skills, String mail, 
			byte homeland, String clientHash,
			String version) throws LoginErrorException;
	
}
