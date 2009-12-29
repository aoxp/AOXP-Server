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

package ao.config;

/**
 * General server configuration loader.
 */
public interface ServerConfig {

	public static final int DEFAULT_LISTENING_PORT = 7666;
	public static final int DEFAULT_BACKLOG = 5;
	
	/**
	 * Retrieves the port in which to listen for incoming connections.
	 * @return The port in which to listen for incoming connections.
	 */
	int getServerListeningPort();
	
	/**
	 * Retrieves the maximum amount of users expected to be logged in at the same time.
	 * @return The maximum amount of users expected to be logged in at the same time
	 */
	int getMaximumConcurrentUsers();
	
	/**
	 * The backlog for the listening socket.
	 * @return The backlog for the listening socket.
	 */
	int getListeningBacklog();
	
	/**
	 * Retrieves the valid client version.
	 * @return The version.
	 */
	String getVersion();
	
	/**
	 * Retrieves all the valid client hashes.
	 * @return The hashes.
	 */
	String[] getValidClientHashes();
	
	/**
	 * Checks if the character creation is enabled.
	 * @return True if the character creation is enabled, false otherwise.
	 */
	boolean isCharacterCreationEnabled();
	
	/**
	 * Sets whether the character creation is enabled or not.
	 * @param enabled The character creation status.
	 */
	void setCharacterCreationEnabled(boolean enabled);
	
	/**
	 * Checks if the server is restricted only to administrators.
	 * @return True if the server is restricted, false otherwise.
	 */
	boolean isRestrictedToAdmins();
	
	/**
	 * Sets whether the server is restricted to administrators or not.
	 * @param restricted The restriction status.
	 */
	void setRestrictedToAdmins(boolean restricted);
}
