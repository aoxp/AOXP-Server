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

import com.ao.model.character.UserCharacter;
import com.ao.model.user.ConnectedUser;

public interface UserService {
	
	/**
	 * Look if a ConnectedUser is logged in.
	 * @param user The ConnectedUser
	 * @return True if it is connected, False in another case.
	 */
	boolean isLoggedIn(ConnectedUser user);
	
	/**
	 * Log in a ConnectedUser
	 * @param user The ConnectedUser
	 */
	void logIn(ConnectedUser user);
	
	/**
	 * Log out a ConnectedUser
	 * @param user The ConnectedUser
	 */
	void logOut(ConnectedUser user);

	/**
	 * Get character with given name
	 * @param name A character name
	 * @return
	 */
	UserCharacter getCharacter(String name);
	
}
