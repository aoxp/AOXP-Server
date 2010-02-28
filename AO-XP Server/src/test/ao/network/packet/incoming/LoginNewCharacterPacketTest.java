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

package ao.network.packet.incoming;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.easymock.classextension.EasyMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ao.config.ServerConfig;
import ao.context.ApplicationContext;
import ao.context.ApplicationProperties;
import ao.data.dao.AccountDAO;
import ao.mock.MockFactory;
import ao.model.character.Gender;
import ao.model.character.Race;
import ao.model.character.Skill;
import ao.model.character.archetype.UserArchetype;
import ao.model.user.Account;
import ao.model.user.ConnectedUser;
import ao.network.Connection;
import ao.network.DataBuffer;
import ao.network.packet.IncomingPacket;
import ao.security.SecurityManager;
import ao.service.LoginService;
import ao.service.login.LoginServiceImpl;

public class LoginNewCharacterPacketTest {

	private static final String CHARACTER_NAME = "testnew";
	private static final String CHARACTER_PASSWORD = "a";
	private static final String CHARACTER_MAIL = "thistestisnotgoingtofail@aoxp.com";
	private static final byte CHARACTER_ARCHETYPE = (byte) UserArchetype.ASSASIN.ordinal();
	private static final byte CHARACTER_RACE = (byte) Race.DARK_ELF.ordinal();
	private static final byte CHARACTER_GENDER = (byte) Gender.FEMALE.ordinal();
	private static final byte CHARACTER_HOMELAND = 1;
	private static final byte[] CHARACTER_SKILLS = {10, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	private static final byte CLIENT_MAJOR = 0;
	private static final byte CLIENT_MINOR = 12;
	private static final byte CLIENT_VERSION = 2;
	
	Connection connection;
	IncomingPacket packet;
	ServerConfig config;
	SecurityManager security;
	
	static {
		ApplicationProperties.loadProperties("test.properties");
		ApplicationContext.reload();
	}
	
	@Before
	public void setUp() throws Exception {
		packet = new LoginNewCharacterPacket();
		connection = MockFactory.mockConnection(MockFactory.mockConnectedUser());
		
		config = ApplicationContext.getInstance(ServerConfig.class);
		security = ApplicationContext.getInstance(SecurityManager.class);
		
		config.setRestrictedToAdmins(false);
		config.setCharacterCreationEnabled(true);
	}
	
	@After
	public void tearDown() {
		ApplicationContext.getInstance(AccountDAO.class).delete(CHARACTER_NAME);
	}
	
	@Test
	public void invalidEmailTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, "foo", CHARACTER_HOMELAND, LoginServiceImpl.INVALID_EMAIL_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void invalidNameTest() throws Exception {
		
		writeLogin("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa", CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.INVALID_NAME_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void successfulCharacterCreation() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND);
		
		packet.handle(connection);
		
		Account account = ((ConnectedUser) connection.getUser()).getAccount();
		
		assertTrue(account.hasCharacter(CHARACTER_NAME));
		assertEquals(account.getName(), CHARACTER_NAME);
		assertEquals(account.getMail(), CHARACTER_MAIL);
		
		// TODO: Check if the charfile was created.
	}
	
 	@Test
	public void clientOutOfDateTest() throws Exception {
		LoginServiceImpl service = (LoginServiceImpl) ApplicationContext.getInstance(LoginService.class);
		service.setCurrentClientVersion(CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION);
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, (byte) 0, (byte) 0, 
				(byte) 0, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, String.format(LoginServiceImpl.CLIENT_OUT_OF_DATE_ERROR_FORMAT, CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION));
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void nameTakenTest() throws Exception {
		ApplicationContext.getInstance(AccountDAO.class).create(CHARACTER_NAME, CHARACTER_PASSWORD, CHARACTER_MAIL);
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.ACCOUNT_NAME_TAKEN_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void skillsHackingTest() throws Exception {
		
		byte[] skills = CHARACTER_SKILLS.clone();
		skills[1] = 20;
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				skills, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.INVALID_SKILLS_POINTS_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void skillsHacking2Test() throws Exception {
		
		byte[] skills = CHARACTER_SKILLS.clone();
		skills[2] += 50;
		skills[3] -= 50;
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				skills, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.INVALID_SKILLS_POINTS_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void dicesThrewTest() throws Exception {

		// Oops, I forgot to throw the dices!
		EasyMock.reset(connection.getUser());
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.MUST_THROW_DICES_BEFORE_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void invalidRaceTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", (byte) -1, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.INVALID_RACE_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
		
	}
	
	@Test
	public void invalidGenderTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, (byte) -1, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.INVALID_GENDER_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
		
	}
	
	@Test
	public void invalidArchetypeTest() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, (byte) -1,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.INVALID_ARCHETYPE_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
		
	}
	
	@Test
	public void characterCreationDisabledTest() throws Exception {
		
		config.setCharacterCreationEnabled(false);
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.CHARACTER_CREATION_DISABLED_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}

	@Test
	public void restrictedToAdminsTest() throws Exception {
		config.setRestrictedToAdmins(true);
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, CLIENT_MAJOR, CLIENT_MINOR,
				CLIENT_VERSION, "", CHARACTER_RACE, CHARACTER_GENDER, CHARACTER_ARCHETYPE,
				CHARACTER_SKILLS, CHARACTER_MAIL, CHARACTER_HOMELAND, LoginServiceImpl.ONLY_ADMINS_ERROR);
		
		packet.handle(connection);
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	private void writeLogin(String charName, String password, byte major,
			byte minor, byte version, String hash, byte race, byte gender,
			byte archetype, byte[] skills, String mail, byte homeland) throws Exception {
		writeLogin(charName, password, major, minor, version, hash, race, gender,
				archetype, skills, mail, homeland, "");
	}
	
	private void writeLogin(String charName, String password, byte major,
			byte minor, byte version, String hash, byte race, byte gender,
			byte archetype, byte[] skills, String mail, byte homeland, String error) throws Exception {
		DataBuffer buffer = connection.getInputBuffer();
		DataBuffer outBuffer = connection.getOutputBuffer();

		EasyMock.expect(buffer.getASCIIString()).andReturn(charName).once();
		
		EasyMock.expect(buffer.getASCIIStringFixed(security.getPasswordHashLength())).andReturn(password).once();
		
		EasyMock.expect(buffer.get()).andReturn(major).once();
		EasyMock.expect(buffer.get()).andReturn(minor).once();
		EasyMock.expect(buffer.get()).andReturn(version).once();
		EasyMock.expect(buffer.getASCIIStringFixed(security.getClientHashLength())).andReturn(hash).once();
		EasyMock.expect(buffer.get()).andReturn(race).once();
		EasyMock.expect(buffer.get()).andReturn(gender).once();
		EasyMock.expect(buffer.get()).andReturn(archetype).once();
		EasyMock.expect(buffer.getBlock(Skill.AMOUNT)).andReturn(skills).once();
		EasyMock.expect(buffer.getASCIIString()).andReturn(mail).once();
		EasyMock.expect(buffer.get()).andReturn(homeland).once();
		
		EasyMock.replay(buffer);
		
		if (error.length() > 0) {
			EasyMock.expect(outBuffer.put(EasyMock.anyByte())).andReturn(outBuffer).once();
			EasyMock.expect(outBuffer.putASCIIString(error)).andReturn(outBuffer).once();
		}
		
		EasyMock.replay(outBuffer);
	}
}
