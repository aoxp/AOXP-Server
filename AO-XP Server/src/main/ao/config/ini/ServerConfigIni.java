package ao.config.ini;

import java.io.FileInputStream;
import java.io.IOException;

import org.ini4j.Ini;

import ao.config.ServerConfig;

/**
 * General server configuration, backed by a INI file.
 */
public class ServerConfigIni implements ServerConfig {

	// TODO : Externalize this to a .properties file (with all other paths)
	private static final String CONFIG_FILE_PATH = "Server.ini";

	private static final String INIT_HEADER = "INIT";

	private static final String BACKLOG_KEY = "Backlog";
	private static final String MAXUSERS_KEY = "MaxUsers";
	private static final String LISTENING_PORT_KEY = "StartPort";
	
	private Ini config;
	
	/**
	 * Creates a new ServerConfigIni instance.
	 * @throws IOException
	 */
	public ServerConfigIni() throws IOException {
		config = new Ini(new FileInputStream(CONFIG_FILE_PATH));
	}
	
	@Override
	public int getListeningBacklog() {
		String backlog = config.get(INIT_HEADER, BACKLOG_KEY);
		return backlog == null ? DEFAULT_BACKLOG : Integer.valueOf(backlog);
	}

	@Override
	public int getMaximumConcurrentUsers() {
		return Integer.valueOf(config.get(INIT_HEADER, MAXUSERS_KEY));
	}

	@Override
	public int getServerListeningPort() {
		String listeningPort = config.get(INIT_HEADER, LISTENING_PORT_KEY);
		return listeningPort == null ? DEFAULT_LISTENING_PORT : Integer.valueOf(listeningPort);
	}

}
