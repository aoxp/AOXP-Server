package ao.config.ini;

import java.io.FileInputStream;
import java.io.IOException;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

import ao.config.ServerConfig;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * General server configuration, backed by a INI file.
 */
public class ServerConfigIni implements ServerConfig {

	private static final String INIT_HEADER = "INIT";

	private static final String BACKLOG_KEY = "Backlog";
	private static final String MAXUSERS_KEY = "MaxUsers";
	private static final String LISTENING_PORT_KEY = "StartPort";
	private static final String VERSION_KEY = "Version";
	
	private static final String HASHES_HEADER = "MD5Hush";
	private static final String HASHES_ACTIVATED_KEY = "Activado";
	private static final String HASHES_AMOUNT_KEY = "MD5Aceptados";
	private static final String HASHES_ACCEPTED_KEY_FORMAT = "Md5Aceptado%d";
	
	private Ini config;
	
	/**
	 * Creates a new ServerConfigIni instance.
	 * @throws IOException
	 */
	@Inject
	public ServerConfigIni(@Named("ServerConfigIni") String serverConfigIni) throws IOException {
		config = new Ini(new FileInputStream(serverConfigIni));
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

	@Override
	public String getVersion() {
		return config.get(INIT_HEADER, VERSION_KEY);
	}
	
	@Override
	public String[] getValidClientHashes() {
		
		Section hashesConfig = config.get(HASHES_HEADER);
		if (Integer.valueOf(hashesConfig.get(HASHES_ACTIVATED_KEY)) == 0) {
			return null;
		}
		
		String[] clientHashes = new String[Integer.valueOf(hashesConfig.get(HASHES_AMOUNT_KEY))];
		
		for (int i = 0; i < clientHashes.length; i++) {
			clientHashes[i] = hashesConfig.get(String.format(HASHES_ACCEPTED_KEY_FORMAT, i));
		}
		
		return clientHashes;
	}
}
