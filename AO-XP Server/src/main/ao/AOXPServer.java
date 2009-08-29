package ao;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 * The server application.
 * @author jsotuyod
 */
public class AOXPServer {

	private ExecutorService threadPool;
	private ServerSocketChannel ssc;
	private Selector selector;

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
						// Accept the incoming connection.
						SocketChannel sc = ssc.accept();
						
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
					} else if ((key.readyOps() & SelectionKey.OP_READ) == SelectionKey.OP_READ) {
						SocketChannel sc = (SocketChannel)key.channel();
						
						// TODO : Handle input.... if it's not a closed connection, grab a thread from the thread pool and process data!
					}

					// TODO : deal with SelectionKey
				}
				
				keys.clear();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

}
