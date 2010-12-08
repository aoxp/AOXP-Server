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
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import com.ao.config.ServerConfig;
import com.ao.context.ApplicationContext;
import com.ao.data.dao.exception.DAOException;
import com.ao.network.ConnectionManager;
import com.ao.service.MapService;
import com.ao.service.WorldObjectService;

/**
 * Bootstraps the application.
 */
public class Bootstrap {

	private static final Logger logger = Logger.getLogger(Bootstrap.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		AOXPServer server = null;
		
		try {
			server = Bootstrap.bootstrap();
		} catch (Exception e) {
			logger.fatal("Server bootstraping failed!", e);
			
			System.exit(-1);
		}
		
		logger.info("AOXP Server ready. Enjoy it!");
		server.run();
	}

	/**
	 * Bootstraps the application.
	 * @return The application.
	 * @throws IOException 
	 * @throws DAOException 
	 */
	private static AOXPServer bootstrap() throws IOException, DAOException {
		
		AOXPServer server = new AOXPServer();
		
		long timeMillis = System.currentTimeMillis();
		
		logger.info("Initializing AOXP Server...");
		loadApplicationContext(server);
		startWorkers(server);
		startTimers(server);
		startNetworking(server);
		
		logger.info("AOXP Server initialized. Took " + (System.currentTimeMillis() - timeMillis) + "ms.");
		
		return server;
	}

	/**
	 * Initializes the workers on the given server.
	 * @param server The server on which to start the workers.
	 */
	private static void startWorkers(AOXPServer server) {
		
		logger.info("Starting up worker thread pool...");
		
		// Create one thread per core.
		int threadCount = Runtime.getRuntime().availableProcessors();
		
		server.setThreadPool(Executors.newFixedThreadPool(threadCount));
	}

	/**
	 * Starts networking on the given server.
	 * @param server The server on which to start networking.
	 * @throws IOException
	 */
	private static void startNetworking(AOXPServer server) throws IOException {
		
		logger.info("Initializing server socket...");
		
		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		
		ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
		InetSocketAddress endpoint = new InetSocketAddress(InetAddress.getLocalHost(), config.getServerListeningPort());
		
		serverSocketChannel.socket().bind(endpoint, config.getListeningBacklog());
		
		server.setServerSocketChannel(serverSocketChannel);
	}

	/**
	 * Starts the game timers on the given server.
	 * @param server The server on which to start the timers.
	 */
	private static void startTimers(AOXPServer server) {
		
		logger.info("Starting up game timers...");
		
		// TODO : Go over this... use a Timer instead with all scheduled tasks??
		// TODO : repeat for each timer
		ExecutorService timer = Executors.newSingleThreadScheduledExecutor();
		
		// TODO : get runnable for each timer class (use the app context) and the interval for execution
	}

	/**
	 * Loads the application context on the given server.
	 * @param server The server on which to load the application context.
	 */
	private static void loadApplicationContext(AOXPServer server) throws DAOException {
		
		logger.info("Loading application context...");
		
		logger.info("Setting up connection manager...");
		server.setConnectionManager(ApplicationContext.getInstance(ConnectionManager.class));
		
		logger.info("Loading maps...");
		MapService mapService = ApplicationContext.getInstance(MapService.class);
		mapService.loadMaps();
		
		logger.info("Loading world objects...");
		WorldObjectService objectService = ApplicationContext.getInstance(WorldObjectService.class);
		objectService.loadObjects();
		
		// TODO : Load other services and classes from application context
	}

}
