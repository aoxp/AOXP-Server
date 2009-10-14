package ao.data.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.ini4j.Ini;

import com.google.inject.Inject;
import com.google.inject.name.Named;

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
