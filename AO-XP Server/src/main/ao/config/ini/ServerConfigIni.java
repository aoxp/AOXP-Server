package ao.config.ini;

import ao.config.ServerConfig;

/**
 * General server configuration, backed by a INI file.
 */
public class ServerConfigIni implements ServerConfig {

	@Override
	public int getListeningBacklog() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaximumConcurrentUsers() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getServerListeningPort() {
		// TODO Auto-generated method stub
		return 0;
	}

}
