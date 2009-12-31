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

package ao.data.dao.ini;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.ini4j.Ini;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import ao.data.dao.AccountDAO;
import ao.data.dao.DAOException;
import ao.model.user.Account;

public class AccountDAOIni implements AccountDAO {
	
	private static final String FILE_EXTENSION 	= ".chr";
	private static final String INIT_HEADER 	= "INIT";
	private static final String PASSWORD_KEY 	= "Password";
	private static final String CONTACT_KEY 	= "Contacto";
	private static final String MAIL_KEY 		= "Email";
	private static final String FLAGS_HEADER	= "FLAGS";
	private static final String BANNED_KEY 		= "Ban"; 
	
	private static final Logger logger = Logger.getLogger(AccountDAOIni.class);
	private String charfilesPath;
	
	@Inject
	public AccountDAOIni(@Named("CharfilesPath") String charfilesPath) {
		this.charfilesPath = charfilesPath;
	}
	
	@Override
	public Account retrieve(String username) throws DAOException {
		Ini chara;
		
		try {
			chara = new Ini(new FileInputStream(charfilesPath + username.toUpperCase() + FILE_EXTENSION));
		} catch (FileNotFoundException e) {
			// The account doesn't exists.
			return null;
			
		} catch (IOException e) {
			logger.error("Charfile loading failed!", e);
			throw new DAOException();
		}
		
		return new Account(
				username,
				chara.get(INIT_HEADER, PASSWORD_KEY),
				chara.get(CONTACT_KEY, MAIL_KEY),
				new String[] {username},
				chara.get(FLAGS_HEADER, BANNED_KEY).equals("1")
		);
	}

}
