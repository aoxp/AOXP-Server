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
	private static final String CHARACTER_CREATION_KEY = "PuedeCrearPersonajes";
	private static final String RESTRICTED_TO_ADMINS_KEY = "ServerSoloGMs";
	
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

	@Override
	public boolean isCharacterCreationEnabled() {
		return config.get(INIT_HEADER, CHARACTER_CREATION_KEY).equals("1");
	}

	@Override
	public void setCharacterCreationEnabled(boolean enabled) {
		config.put(INIT_HEADER, CHARACTER_CREATION_KEY, enabled ? "1" : "0");
		
	}

	
	@Override
	public boolean isRestrictedToAdmins() {
		return config.get(INIT_HEADER, RESTRICTED_TO_ADMINS_KEY).equals("1");
	}

	@Override
	public void setRestrictedToAdmins(boolean restricted) {
		config.put(INIT_HEADER, CHARACTER_CREATION_KEY, restricted ? "1" : "0");
	}
	
}
