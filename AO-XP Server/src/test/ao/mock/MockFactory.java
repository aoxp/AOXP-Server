package ao.mock;

import org.easymock.classextension.EasyMock;

import ao.model.user.ConnectedUser;
import ao.network.Connection;
import ao.network.DataBuffer;

/**
 * Centralizes the common mocks creation.
 */
public class MockFactory {

	/**
	 * Creates a new Connection mock.
	 * The created mock will also return mocks in the functions getInputBuffer,
	 * getOutputBuffer and getUser.
	 * 
	 * @return The mock.
	 */
	public static Connection mockConnection() {
		Connection conn = EasyMock.createMock(Connection.class);
		
		EasyMock.expect(conn.getInputBuffer()).andReturn(EasyMock.createMock(DataBuffer.class)).anyTimes();
		EasyMock.expect(conn.getOutputBuffer()).andReturn(EasyMock.createMock(DataBuffer.class)).anyTimes();
		EasyMock.expect(conn.getUser()).andReturn(EasyMock.createMock(ConnectedUser.class)).anyTimes();
		conn.disconnect();
		
		EasyMock.replay(conn);
		
		return conn;
	}
}
