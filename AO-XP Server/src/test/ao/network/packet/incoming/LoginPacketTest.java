package ao.network.packet.incoming;

import java.nio.channels.SocketChannel;
import junit.framework.Assert;

import org.easymock.classextension.EasyMock;
import org.junit.Before;
import org.junit.Test;

import ao.context.ApplicationContext;
import ao.network.Connection;
import ao.network.DataBuffer;
import ao.network.packet.IncomingPacket;

public class LoginPacketTest {

	private static final String BANNED_CHARACTER_NAME = "banned";
	private static final String BANNED_CHARACTER_PASSWORD = "a";
	private static final String CHARACTER_NAME = "test";
	private static final String CHARACTER_PASSWORD = "a";
	private static final int CLIENT_MAJOR = 0;
	private static final int CLIENT_MINOR = 12;
	private static final int CLIENT_VERSION = 2;
	
	Connection connection;
	IncomingPacket packet;
	
	@Before
	public void setUp() throws Exception {
		packet = new LoginPacket();
		connection = EasyMock.createMock(Connection.class);
		
		EasyMock.expect(connection.getInputBuffer()).andReturn(EasyMock.createMock(DataBuffer.class)).anyTimes();
		EasyMock.expect(connection.getOutputBuffer()).andReturn(EasyMock.createMock(DataBuffer.class)).anyTimes();
		connection.disconnect();
		
		EasyMock.replay(connection);
	}

	private void writeLogin(String charName, String password, int major, int minor, int version, String hash, String error) throws Exception {
		DataBuffer buffer = connection.getInputBuffer();
		DataBuffer outBuffer = connection.getOutputBuffer();

		EasyMock.expect(buffer.getASCIIString()).andReturn(charName).once();
		EasyMock.expect(buffer.getASCIIString()).andReturn(password).once();
		EasyMock.expect(buffer.getShort()).andReturn((short) major).once();
		EasyMock.expect(buffer.getShort()).andReturn((short) minor).once();
		EasyMock.expect(buffer.getShort()).andReturn((short) version).once();
		
		// Deprecated versions.
		EasyMock.expect(buffer.getShort()).andReturn((short) 1).times(7);
		
		if (ApplicationContext.SECURITY_ENABLED) {
			EasyMock.expect(buffer.getASCIIStringFixed(LoginPacket.HASH_BINARY_LENGTH)).andReturn(hash).once();
		}
		
		EasyMock.replay(buffer);
		
		if (error.length() > 0) {
			EasyMock.expect(outBuffer.put(EasyMock.anyByte())).andReturn(outBuffer).once();
			EasyMock.expect(outBuffer.putASCIIStringFixed(error)).andReturn(outBuffer).once();
		}
		
		EasyMock.replay(outBuffer);
	}
	
	@Test
	public void testHandleCharacterNotFound() throws Exception {
		writeLogin("foo", "foo", 0, 0, 0, "", LoginPacket.CHARACTER_NOT_FOUND_ERROR_MESSAGE);
		packet.handle(connection);
		
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void testHandleIncorrectPassword() throws Exception {
		writeLogin(CHARACTER_NAME, "foo", CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "", LoginPacket.INCORRECT_PASSWORD_ERROR_MESSAGE);
		packet.handle(connection);
		
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void testHandleOutOfDateClient() throws Exception {
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD, 0, 0, 0, "", String.format(LoginPacket.CLIENT_OUT_OF_DATE_ERROR_MESSAGE_FORMAT, CLIENT_MAJOR + "." + CLIENT_MINOR + "." + CLIENT_VERSION));
		packet.handle(connection);
		
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void testHandleCorruptedClient() throws Exception {
		if (!ApplicationContext.SECURITY_ENABLED) {
			return;
		}
		
		writeLogin(CHARACTER_NAME, CHARACTER_PASSWORD,
				CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "foo", LoginPacket.BANNED_CHARACTER_ERROR_MESSAGE);
		packet.handle(connection);
		
		EasyMock.verify(connection.getOutputBuffer());
	}
	
	@Test
	public void testHandleBannedCharacter() throws Exception {
		writeLogin(BANNED_CHARACTER_NAME, BANNED_CHARACTER_PASSWORD,
						CLIENT_MAJOR, CLIENT_MINOR, CLIENT_VERSION, "", LoginPacket.BANNED_CHARACTER_ERROR_MESSAGE);
		packet.handle(connection);
		
		EasyMock.verify(connection.getOutputBuffer());
	}

}
