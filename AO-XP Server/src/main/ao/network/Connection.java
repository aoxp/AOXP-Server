package ao.network;

import java.nio.channels.SocketChannel;

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
	
	public DataBuffer getInputBuffer() {
		return inputBuffer;
	}

	public DataBuffer getOutputBuffer() {
		return outputBuffer;
	}

	public SocketChannel getSocketChannel() {
		return socketChannel;
	}

	public User getUser() {
		return user;
	}
	
	public Connection(SocketChannel socketChannel) {
		this.socketChannel = socketChannel;
		
		// TODO : Evaluate the usage of a direct buffer instead.... may have performance impact...
		this.inputBuffer = new DataBuffer(BUFFER_CAPACITY);
		this.outputBuffer = new DataBuffer(BUFFER_CAPACITY);
		
		// TODO : Create user instance? receive it in the constructor?
	}
}