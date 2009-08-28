package ao;

import java.util.concurrent.ExecutorService;

/**
 * The server application.
 * @author jsotuyod
 */
public class AOXPServer {

	private ExecutorService threadPool;

	/**
	 * Starts running the AOXPServer. It MUST be properly initialized beforehand.
	 */
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Sets the server's thread pool.
	 * @param threadPool The thread pool to be used.
	 */
	public void setThreadPool(ExecutorService threadPool) {
		this.threadPool = threadPool;
	}

}
