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
	
	@Override
	public DataBuffer getInputBuffer(SocketChannel sc) {
		return this.scToConnection.get(sc).inputBuffer;
	}

	@Override
	public void handleIncomingData(SocketChannel sc) {

		// Everything is synchronized over the socket. This allows to keep only one running thread per client at a time.
		synchronized (sc) {
			Connection connection = this.scToConnection.get(sc);
			
			try {
				if (sc.read(connection.inputBuffer.buffer) == -1) {
					// Connection closed
					unregisterConnection(sc);
				} else {
					// Mark the current position to revert it if there is not enough data.
					connection.inputBuffer.buffer.mark();
					
					int	packetHeader = (int) connection.inputBuffer.get();
					
					// TODO: Save this array? It will NEVER change.
					ServerPackets[] packetHandlers = ServerPackets.values();
					
					try {
						// TODO: Check if the packet exists in the array!!
						packetHandlers[packetHeader].getHandler().handle(connection);
					} catch (BufferUnderflowException e) {
						// Not enough data received, restore the buffer.
						connection.inputBuffer.buffer.reset();
					}
					
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
		connection.socketChannel.write(connection.outputBuffer.buffer);
		connection.outputBuffer.clear();
	}

	@Override
	public DataBuffer getOutputBuffer(User user) {
		
		return this.userToConnection.get(user).outputBuffer;
	}

}
