package ao;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
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
			// TODO This should really end when the app is somehow interrupted...
			while (true) {
				// Wait for any activity (either an incoming connection or incoming data on an existing connection)
				int num = selector.select();
				
				if (num == 0) {
					continue;	// No activity, continue
				}
				
				// Process detected events
				Set keys = selector.selectedKeys();
				Iterator it = keys.iterator();
				while (it.hasNext()) {
					SelectionKey key = (SelectionKey)it.next();
					
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
