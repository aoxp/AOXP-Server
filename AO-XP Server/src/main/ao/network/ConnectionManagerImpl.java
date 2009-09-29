package ao.network;

import java.io.IOException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import ao.AOXPServer;
import ao.config.ServerConfig;
import ao.model.user.User;

import com.google.inject.Inject;

/**
 * Default connection manager
 */
public class ConnectionManagerImpl implements ConnectionManager {

	private static final Logger logger = Logger.getLogger(AOXPServer.class);
	
	private static final float DEFAULT_LOAD_FACTOR = 0.75f;
	
	private ConcurrentMap<SocketChannel, Connection> scToConnection;
	private ConcurrentMap<User, Connection> userToConnection;

	/**
	 * Creates a new ConnectionManagerImpl
	 * @param config The server's configuration.
	 */
	@Inject
	public ConnectionManagerImpl(ServerConfig config) {
		int concurrencyLevel = Runtime.getRuntime().availableProcessors();
		int maxExpectedUsers = config.getMaximumConcurrentUsers();
		
		this.scToConnection = new ConcurrentHashMap<SocketChannel, Connection>(maxExpectedUsers, DEFAULT_LOAD_FACTOR, concurrencyLevel);
		this.userToConnection = new ConcurrentHashMap<User, Connection>(maxExpectedUsers, DEFAULT_LOAD_FACTOR, concurrencyLevel);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.network.ConnectionManager#getInputBuffer(java.nio.channels.SocketChannel)
	 */
	@Override
	public DataBuffer getInputBuffer(SocketChannel sc) {
		return this.scToConnection.get(sc).inputBuffer;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.network.ConnectionManager#handleIncomingData(java.nio.channels.SocketChannel)
	 */
	@Override
	public void handleIncomingData(SocketChannel sc) {

		// Everything is synchronized over the socket. This allows to keep only one running thread per client at a time.
		synchronized (sc) {
			Connection connection = this.scToConnection.get(sc);
			ByteBuffer buffer = connection.inputBuffer.buffer;
			
			try {
				if (sc.read(buffer) == -1) {
					// Connection closed
					unregisterConnection(sc);
				} else {
					// Limit the buffer to what we currently have, and move pointer to the beginning
					buffer.flip();
					
					// TODO: Save this array? It will NEVER change.
					ServerPackets[] packetHandlers = ServerPackets.values();
					
					try {
						// Handle every packet we may
						while (buffer.hasRemaining()) {
							// Mark the current position to revert it if there is not enough data for this packet.
							buffer.mark();
							
							int	packetHeader = (int) buffer.get();
							
							// TODO: Handle exception if the packet doesn't exist in the array!!
							packetHandlers[packetHeader].getHandler().handle(connection);
						}
					} catch (BufferUnderflowException e) {
						// Not enough data received, restore the buffer to the last mark of a completed packet.
						buffer.reset();
					}
					
					// Delete all bytes read in handled packets, leave the pointer at the end for the next incoming message.
					buffer.compact();
				}
			} catch (IOException e) {
				logger.error("Unexpected error reading data from socket.", e);
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ao.network.ConnectionManager#registerConnection(java.nio.channels.SocketChannel)
	 */
	@Override
	public void registerConnection(SocketChannel sc) {
		Connection connection = new Connection(sc);
		
		this.scToConnection.put(sc, connection);
		this.userToConnection.put(connection.user, connection);
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.network.ConnectionManager#unregisterConnection(java.nio.channels.SocketChannel)
	 */
	@Override
	public void unregisterConnection(SocketChannel sc) {
		
		// TODO : Apply any other internal logic before definitely removing the user
		
		Connection connection = this.scToConnection.remove(sc);
		this.userToConnection.remove(connection.user);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.network.ConnectionManager#flushOutputBuffer(ao.model.user.User)
	 */
	@Override
	public void flushOutputBuffer(User user) throws IOException {
		
		Connection connection = this.userToConnection.get(user);
		connection.outputBuffer.buffer.flip();
		connection.socketChannel.write(connection.outputBuffer.buffer);
		connection.outputBuffer.buffer.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.network.ConnectionManager#getOutputBuffer(ao.model.user.User)
	 */
	@Override
	public DataBuffer getOutputBuffer(User user) {
		
		return this.userToConnection.get(user).outputBuffer;
	}

}
