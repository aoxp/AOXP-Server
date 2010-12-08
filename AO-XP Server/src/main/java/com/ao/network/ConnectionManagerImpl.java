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

package com.ao.network;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.ao.AOXPServer;
import com.ao.config.ServerConfig;
import com.ao.context.ApplicationContext;
import com.ao.model.user.User;
import com.ao.security.SecurityManager;

import com.google.inject.Inject;

/**
 * Default connection manager
 */
public class ConnectionManagerImpl implements ConnectionManager {

	private static final Logger logger = Logger.getLogger(AOXPServer.class);
	
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private ConcurrentMap<SocketChannel, Connection> scToConnection;
	private ConcurrentMap<User, Connection> userToConnection;
	private SecurityManager security = ApplicationContext.getInstance(SecurityManager.class);
	
	/**
	 * Creates a new ConnectionManagerImpl
	 * @param config The server's configuration.
	 */
	@Inject
	public ConnectionManagerImpl(ServerConfig config) {
		int concurrencyLevel = Runtime.getRuntime().availableProcessors();
		int maxExpectedUsers = config.getMaximumConcurrentUsers();
		
		scToConnection = new ConcurrentHashMap<SocketChannel, Connection>(maxExpectedUsers, DEFAULT_LOAD_FACTOR, concurrencyLevel);
		userToConnection = new ConcurrentHashMap<User, Connection>(maxExpectedUsers, DEFAULT_LOAD_FACTOR, concurrencyLevel);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ao.network.ConnectionManager#getInputBuffer(java.nio.channels.SocketChannel)
	 */
	@Override
	public DataBuffer getInputBuffer(SocketChannel sc) {
		return scToConnection.get(sc).inputBuffer;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.network.ConnectionManager#handleIncomingData(java.nio.channels.SocketChannel)
	 */
	@Override
	public void handleIncomingData(SocketChannel sc) {

		synchronized (sc) {
			Connection connection = scToConnection.get(sc);
			ByteBuffer buffer = connection.inputBuffer.buffer;
			
			try {
				try {
					// Handle every packet we may
					while (buffer.hasRemaining()) {
						// Mark the current position to revert it if there is not enough data for this packet.
						buffer.mark();
						
						ClientPacketsManager.handle(connection);
					}
				} catch (BufferUnderflowException e) {
					// Not enough data received, restore the buffer to the last mark of a completed packet.
					buffer.reset();
				} catch (ArrayIndexOutOfBoundsException e) {
					// Whooa, what are you doing? The packet doesn't exists, close the connection!
					logger.error("Seems an unexpected packet id was received.", e);
					connection.disconnect();
					return;
				}
				
				// Delete all bytes read in handled packets, leave the pointer at the end for the next incoming message.
				buffer.compact();
				
				// Send all outgoing data pending in the buffer
				flushOutputBuffer(connection.getUser());
			} catch (IOException e) {
				logger.fatal("Unexpected IO error handlingincoming data.", e);
				connection.disconnect();
				return;
			}
		}
	}
	
	@Override
	public boolean readAllData(SocketChannel sc) {
		// Everything is synchronized over the socket. This allows to keep only one running thread per client at a time.
		synchronized (sc) {
			Connection connection = scToConnection.get(sc);
			
			// Check if the connection wasn't removed by an executing thread handling packages.
			if (null == connection) {
				return false;
			}
			
			ByteBuffer buffer = connection.inputBuffer.buffer;
			
			try {
				if (sc.read(buffer) == -1) {
					// Connection closed
					unregisterConnection(sc);
				} else {
					// Limit the buffer to what we currently have, and move pointer to the beginning
					buffer.flip();
					
					// Decrypt the data before trying to handle the packet.
					security.decrypt(buffer, sc);
				}
			} catch (IOException e) {
				logger.error("Unexpected error reading data from socket.", e);
				connection.disconnect();
				return false;
			}
		}
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.network.ConnectionManager#registerConnection(java.nio.channels.SocketChannel)
	 */
	@Override
	public void registerConnection(SocketChannel sc) {
		Connection connection = new Connection(sc);
		
		scToConnection.put(sc, connection);
		userToConnection.put(connection.user, connection);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.ao.network.ConnectionManager#unregisterConnection(java.nio.channels.SocketChannel)
	 */
	@Override
	public void unregisterConnection(SocketChannel sc) {
		
		// TODO : Apply any other internal logic before definitely removing the user
		
		Connection connection = scToConnection.remove(sc);
		userToConnection.remove(connection.user);
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.network.ConnectionManager#flushOutputBuffer(ao.model.user.User)
	 */
	@Override
	public void flushOutputBuffer(User user) throws IOException {
		
		Connection connection = userToConnection.get(user);
		connection.outputBuffer.buffer.flip();
		
		// Encrypt the data before sending it.
		security.encrypt(connection.outputBuffer.buffer, connection.socketChannel);
		
		connection.socketChannel.write(connection.outputBuffer.buffer);
		connection.outputBuffer.buffer.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.network.ConnectionManager#getOutputBuffer(ao.model.user.User)
	 */
	@Override
	public DataBuffer getOutputBuffer(User user) {
		
		return userToConnection.get(user).outputBuffer;
	}

}
