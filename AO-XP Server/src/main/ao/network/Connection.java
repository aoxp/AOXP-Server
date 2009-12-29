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

package ao.network;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import ao.context.ApplicationContext;
import ao.model.user.ConnectedUser;
import ao.model.user.User;

/**
 * Connection class. Simple DTO for connections.
 */
public class Connection {
	
	private static final int BUFFER_CAPACITY = 8192;
	
	protected DataBuffer inputBuffer;
	protected DataBuffer outputBuffer;
	protected SocketChannel socketChannel;
	protected User user;
	private static final ConnectionManager manager = ApplicationContext.getInstance(ConnectionManager.class);
	
	/**
	 * Creates a new Connection.
	 * @param socketChannel The channel over which to communicate with the client.
	 */
	public Connection(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		
		// TODO : Evaluate the usage of a direct buffer instead.... may have performance impact...
		this.inputBuffer = new DataBuffer(BUFFER_CAPACITY);
		this.outputBuffer = new DataBuffer(BUFFER_CAPACITY);
		
		this.user = new ConnectedUser();
	}
	
	/**
	 * Retrieves the input buffer.
	 * @return The input buffer.
	 */
	public DataBuffer getInputBuffer() {
		return inputBuffer;
	}
	
	/**
	 * Retrieves the output buffer.
	 * @return The output buffer.
	 */
	public DataBuffer getOutputBuffer() {
		return outputBuffer;
	}
	
	/**
	 * Retrieves the socket channel.
	 * @return The socket channel.
	 */
	public SocketChannel getSocketChannel() {
		return socketChannel;
	}
	
	/**
	 * Retrieves the user.
	 * @return The user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Closes the connection.
	 */
	public void disconnect() {
		
		try {
			manager.flushOutputBuffer(user);
		} catch (IOException e) {
			// I don't care, I'm closing the connection..
		}
		
		manager.unregisterConnection(socketChannel);
	}
}
