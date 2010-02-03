package ao.model.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class AccountImplTest {

	private static final String ACCOUNT_NAME = "an account";
	private static final String ACCOUNT_PASSWORD = "a password";
	private static final String ACCOUNT_EMAIL = "anemail@address.com";
	private static final boolean ACCOUNT_BANNED = false;
	private static final List ACCOUNT_CHARACTERS = new LinkedList<String>();
	private static final String TEST_CHARACTER_NAME = "a character";
	
	AccountImpl account;
	
	@Before
	public void setUp() throws Exception {
		account = new AccountImpl(ACCOUNT_NAME, ACCOUNT_PASSWORD, ACCOUNT_EMAIL, ACCOUNT_CHARACTERS, ACCOUNT_BANNED);
		account.characters.add(TEST_CHARACTER_NAME);
	}

	@Test
	public void testGetName() {
		assertEquals(account.getName(), ACCOUNT_NAME);
	}

	@Test
	public void testGetMail() {
		assertEquals(account.getMail(), ACCOUNT_EMAIL);
	}

	@Test
	public void testGetCharacters() {
		assertEquals(account.getCharacters(), ACCOUNT_CHARACTERS);
	}

	@Test
	public void testHasCharacter() {
		assertTrue(account.hasCharacter(TEST_CHARACTER_NAME));
		assertFalse(account.hasCharacter(TEST_CHARACTER_NAME + "aa"));
	}

	@Test
	public void testIsBanned() {
		assertEquals(account.isBanned(), ACCOUNT_BANNED);
		
		account.setBanned(!ACCOUNT_BANNED);
		
		assertEquals(account.isBanned(), !ACCOUNT_BANNED);
	}

	@Test
	public void testGetCharacter() {
		fail("Not yet implemented");
	}

	@Test
	public void testAuthenticate() {
		assertTrue(account.authenticate(ACCOUNT_PASSWORD));
	}

	@Test
	public void testAddCharacter() {
		String charr = "foo";
		
		account.addCharacter(charr);
		
		assertTrue(account.characters.contains(charr));
	}

}
