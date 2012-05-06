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

package com.ao.service.user;

import java.util.HashSet;
import java.util.Set;

import com.ao.context.ApplicationContext;
import com.ao.data.dao.UserCharacterDAO;
import com.ao.data.dao.exception.DAOException;
import com.ao.model.character.UserCharacter;
import com.ao.model.user.ConnectedUser;
import com.ao.service.UserService;

public class UserServiceImpl implements UserService {
	
	private final UserCharacterDAO charDAO = ApplicationContext.getInstance(UserCharacterDAO.class);
	
	private Set<ConnectedUser> connectedUsers = new HashSet<ConnectedUser>();
	
	@Override
	public boolean isLoggedIn(ConnectedUser user) {
		return connectedUsers.contains(user);
	}

	@Override
	public void logIn(ConnectedUser user) {
		connectedUsers.add(user);
	}

	@Override
	public void logOut(ConnectedUser user) {
		connectedUsers.remove(user);
	}

	@Override
	public UserCharacter getCharacter(String name) {
		UserCharacter userCharacter;
		try {
			userCharacter = charDAO.load(name);
		} catch (DAOException e) {
			throw new RuntimeException(e);
		}
		return userCharacter;
	}

}
