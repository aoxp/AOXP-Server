package ao.network;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import ao.AOXPServer;
import ao.config.ServerConfig;
import ao.domain.user.User;

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
	
	@Override
	public ByteBuffer getInputBuffer(SocketChannel sc) {
		return this.scToConnection.get(sc).inputBuffer;
	}

	@Override
	public void handleIncomingData(SocketChannel sc) {

		// Everything is synchronized over the socket. This allows to keep only one running thread per client at a time.
		synchronized (sc) {
			Connection connection = this.scToConnection.get(sc);
			
			try {
				if (sc.read(connection.inputBuffer) == -1) {
					// Connection closed
					unregisterConnection(sc);
				} else {
					
					// TODO : Handle incoming data
				}
			} catch (IOException e) {
				logger.error("Unexpected error reading data from socket.", e);
				
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void registerConnection(SocketChannel sc) {
		Connection connection = new Connection(sc);
		
		this.scToConnection.put(sc, connection);
		this.userToConnection.put(connection.user, connection);
	}
	
	@Override
	public void unregisterConnection(SocketChannel sc) {
		
		// TODO : Apply any other internal logic before definitely removing the user
		
		Connection connection = this.scToConnection.remove(sc);
		this.userToConnection.remove(connection.user);
	}

	@Override
	public void flushOutputBuffer(User user) throws IOException {
		
		Connection connection = this.userToConnection.get(user);
		connection.outputBuffer.flip();
		connection.socketChannel.write(connection.outputBuffer);
		connection.outputBuffer.clear();
	}

	@Override
	public ByteBuffer getOutputBuffer(User user) {
		
		return this.userToConnection.get(user).outputBuffer;
	}

	/**
	 * Connection class. Simple DTO for connections.
	 */
	private class Connection {
		
		private static final int BUFFER_CAPACITY = 8192;
		
		protected ByteBuffer inputBuffer;
		protected ByteBuffer outputBuffer;
		protected SocketChannel socketChannel;
		protected User user;
		
		public Connection(SocketChannel socketChannel) {
			this.socketChannel = socketChannel;
			
			// TODO : Evaluate the usage of a direct buffer instead.... may have performance impact...
			this.inputBuffer = ByteBuffer.allocate(BUFFER_CAPACITY);
			this.outputBuffer = ByteBuffer.allocate(BUFFER_CAPACITY);
			
			// TODO : Create user instance? receive it in the constructor?
		}
	}

}
