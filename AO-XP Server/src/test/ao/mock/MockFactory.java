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

package ao.mock;

import org.easymock.Capture;
import org.easymock.IAnswer;
import org.easymock.classextension.EasyMock;

import ao.model.character.Attribute;
import ao.model.user.Account;
import ao.model.user.ConnectedUser;
import ao.model.user.User;
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
	 * @param user The User object to be retrieved by getUser.
	 * @return The mock.
	 */
	public static Connection mockConnection(User user) {
		Connection conn = EasyMock.createMock(Connection.class);
		
		EasyMock.expect(conn.getInputBuffer()).andReturn(EasyMock.createMock(DataBuffer.class)).anyTimes();
		EasyMock.expect(conn.getOutputBuffer()).andReturn(EasyMock.createMock(DataBuffer.class)).anyTimes();
		EasyMock.expect(conn.getUser()).andReturn(user).anyTimes();
		conn.disconnect();
		
		EasyMock.replay(conn);
		
		return conn;
	}
	
	/**
	 * Creates a new Connection mock.
	 * The created mock will return default mocks in getInputBuffer, getOutputBuffer and getUser.
	 * 
	 * @return The mock.
	 */
	public static Connection mockConnection() {
		return mockConnection(EasyMock.createMock(ConnectedUser.class));
	}
	
	/**
	 * Creates a new ConnectedUser mock.
	 * 
	 * @return The created mock.
	 */
	public static ConnectedUser mockConnectedUser() {
		ConnectedUser user = EasyMock.createMock(ConnectedUser.class);
		
		EasyMock.expect(user.getAttribute(Attribute.AGILITY)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.CHARISMA)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.CONSTITUTION)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.INTELLIGENCE)).andReturn((byte) 18).anyTimes();
		EasyMock.expect(user.getAttribute(Attribute.STRENGTH)).andReturn((byte) 18).anyTimes();
		
		// Capture the received Account object in setAccount to later return it on getAccount.
		final Capture<Account> account = new Capture<Account>();
		
		user.setAccount(EasyMock.capture(account));
		
		EasyMock.expect(user.getAccount()).andAnswer(new IAnswer<Account>() {

			@Override
			public Account answer() throws Throwable {
				return account.getValue();
			}
			
		});
		
		EasyMock.replay(user);
		
		return user;
	}
	
}
