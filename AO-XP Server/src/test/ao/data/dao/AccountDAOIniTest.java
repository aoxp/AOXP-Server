package ao.data.dao;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import ao.model.user.Account;

public class AccountDAOIniTest {

	private AccountDAOIni dao;
	private static final String CHARACTER_NICK = "test";
	private static final String CHARFILES_PATH = "src/test/resources/charfiles/";
	
	@Before
	public void setUp() throws Exception {
		dao = new AccountDAOIni(CHARFILES_PATH);
	}

	@Test
	public void testRetrieve() throws DAOException {
		Account acc = dao.retrieve(CHARACTER_NICK);
		
		Assert.assertNotNull(acc);
		
		// Enssure we get the requested character and not another one.
		Assert.assertEquals(acc.getName(), CHARACTER_NICK);
	}

}
