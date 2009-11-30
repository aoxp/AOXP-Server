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
					if ((key.readyOps() & SelectionKey.OP_ACCEPT) != 0) {
						logger.debug("New connection created");
						
						// Accept the incoming connection.
						SocketChannel sc = ssc.accept();
						
						sc.configureBlocking(false);
						sc.register(selector, SelectionKey.OP_READ);
						
						connectionManager.registerConnection(sc);
					} else if ((key.readyOps() & SelectionKey.OP_READ) != 0) {
						final SocketChannel sc = (SocketChannel) key.channel();
						
						// Handle data
						threadPool.execute(new Runnable() {
							
							@Override
							public void run() {
								connectionManager.handleIncomingData(sc);
							}
						});
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
