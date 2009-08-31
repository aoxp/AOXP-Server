package ao;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;

import ao.network.ConnectionManager;

/**
 * The server application.
 */
public class AOXPServer {

	private static final Logger logger = Logger.getLogger(AOXPServer.class);
	
	private ExecutorService threadPool;
	private ServerSocketChannel ssc;
	private Selector selector;
	private ConnectionManager connectionManager;

	/**
	 * Starts running the AOXPServer. It MUST be properly initialized beforehand.
	 */
	public void run() {
		try {
			// TODO This should really end when the application is somehow interrupted...
			while (true) {
				// Wait for any activity (either an incoming connection or incoming data on an existing connection)
				if (selector.select() == 0) {
					continue;	// No activity, continue
				}
				
				// Process detected events
				Set<SelectionKey> keys = selector.selectedKeys();
				for (SelectionKey key : keys) {
					if ((key.readyOps() & SelectionKey.OP_ACCEPT) == SelectionKey.OP_ACCEPT) {
						logger.debug("New connection created");
						
						// Accept the incoming connection.
						SocketChannel sc = ssc.accept();
						
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
						
						connectionManager.registerConnection(sc);
					} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
						final SocketChannel sc = (SocketChannel) key.channel();
						
						if (sc.read(connectionManager.getInputBuffer(sc)) == -1) {
							// Connection closed
							connectionManager.unregisterConnection(sc);
						} else {
							// Handle data
							threadPool.execute(new Runnable() {
								
								@Override
								public void run() {
									connectionManager.handleIncomingData(sc);
								}
							});
						}
					}
				}
				
				keys.clear();
			}
		} catch (IOException e) {
			logger.fatal("Unexpected error in main loop.", e);
			
			e.printStackTrace();
		}
	}
	
	/**
	 * Sets the server's thread pool.
	 * @param threadPool The thread pool to be used.
	 */
	public void setThreadPool(ExecutorService threadPool) {
		this.threadPool = threadPool;
	}

	/**
	 * Sets the server's socket channel.
	 * @param ssc The channel to be used by the server.
	 * @throws IOException
	 */
	public void setServerSocketChannel(ServerSocketChannel ssc) throws IOException {
		// Reset selector
		this.selector = Selector.open();
		
		this.ssc = ssc;
		
		// Set general configuration
		this.ssc.configureBlocking(false);
		this.ssc.register(selector, SelectionKey.OP_ACCEPT);
	}

	/**
	 * Set's the server's connection manager.
	 * @param connectionManager The connection manager to be used by the server.
	 */
	public void setConnectionManager(ConnectionManager connectionManager) {
		
		this.connectionManager = connectionManager;
	}
}
