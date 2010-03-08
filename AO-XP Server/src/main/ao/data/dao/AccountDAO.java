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

package ao.data.dao;

import ao.data.dao.exception.DAOException;
import ao.data.dao.exception.NameAlreadyTakenException;
import ao.model.user.Account;

public interface AccountDAO {

	/**
	 * Retrieves the account with the given name.
	 * @param username 	The account's name.
	 * @return 			The account.
	 * @throws DAOException
	 */
	Account retrieve(String username) throws DAOException;
	
	/**
	 * Creates a new account.
	 * 
	 * @param name 		The account's name.
	 * @param password 	The account's password.
	 * @param mail 		The account's mail.
	 * @return			The new created account.
	 * @throws DAOException
	 */
	Account create(String name, String password, String mail) throws DAOException, NameAlreadyTakenException;
	
	/**
	 * Deletes the account with the given name.
	 * 
	 * @param name The account to be deleted.
	 */
	void delete(String name);
	
	/**
	 * Checks if the account with the given name exists.
	 * @param name The account name.
	 * @return True if the account exists, false otherwise.
	 */
	boolean exists(String name);
}
