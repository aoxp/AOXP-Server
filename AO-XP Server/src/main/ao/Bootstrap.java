package ao;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;

import ao.config.ServerConfig;
import ao.ioc.ApplicationContext;
import ao.network.ConnectionManager;

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
		} catch (IOException e) {
			logger.fatal("Server bootstraping failed!", e);
			
			e.printStackTrace();
			
			System.exit(-1);
		}
		
		logger.info("AOXP Server ready. Enjoy it!");
		server.run();
	}

	/**
	 * Bootstraps the application.
	 * @return The application.
	 * @throws IOException 
	 */
	private static AOXPServer bootstrap() throws IOException {
		
		AOXPServer server = new AOXPServer();
		
		logger.info("Initializing AOXP Server...");
		loadApplicationContext(server);
		startWorkers(server);
		startTimers(server);
		startNetworking(server);
		
		logger.info("AOXP Server initialized.");
		
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
		
		// TODO : repeat for each timer
		ExecutorService timer = Executors.newScheduledThreadPool(1);
		
		// TODO : get runnable for each timer class (use the app context) and the interval for execution
	}

	/**
	 * Loads the application context on the given server.
	 * @param server The server on which to load the application context.
	 */
	private static void loadApplicationContext(AOXPServer server) {
		
		logger.info("Loading application context...");
		
		logger.info("Setting up connection manager...");
		server.setConnectionManager(ApplicationContext.getInstance(ConnectionManager.class));
		
		// TODO : Load other services and classes from application context
	}

}
