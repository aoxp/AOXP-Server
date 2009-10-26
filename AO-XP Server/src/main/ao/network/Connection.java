package ao.network;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import ao.context.ApplicationContext;
import ao.model.user.LoggedUser;
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
		
		// TODO : If the differentiation between connected and logged user is to take part, this should be replaced by a connected user
		this.user = new LoggedUser();
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
