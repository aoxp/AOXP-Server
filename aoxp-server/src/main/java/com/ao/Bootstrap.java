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

package com.ao;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ao.config.ServerConfig;
import com.ao.context.ApplicationContext;
import com.ao.data.dao.exception.DAOException;
import com.ao.service.MapService;
import com.ao.service.NPCService;
import com.ao.service.WorldObjectService;

/**
 * Bootstraps the application.
 */
public class Bootstrap {

	private static final Logger LOGGER = LoggerFactory.getLogger(Bootstrap.class);

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		final AOXPServer server;

		try {
			server = Bootstrap.bootstrap();
		} catch (final Exception e) {
			LOGGER.error("Server bootstraping failed!", e);

			System.exit(-1);
			return;
		}

		LOGGER.info("AOXP Server ready. Enjoy it!");
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

		LOGGER.info("Initializing AOXP Server...");
		loadApplicationContext(server);
		startTimers(server);
		configureNetworking(server);

		LOGGER.info("AOXP Server initialized. Took " + (System.currentTimeMillis() - timeMillis) + "ms.");

		return server;
	}

	/**
	 * Configures networking on the given server.
	 * @param server The server on which to configure networking.
	 * @throws IOException
	 */
	private static void configureNetworking(AOXPServer server) throws IOException {
		byte[] addr = {0,0,0,0};

		LOGGER.info("Initializing server socket configuration...");
		ServerConfig config = ApplicationContext.getInstance(ServerConfig.class);
		InetSocketAddress endpoint = new InetSocketAddress(Inet4Address.getByAddress(addr), config.getServerListeningPort());
		server.setListeningAddr(endpoint);
		server.setBacklog(config.getListeningBacklog());
	}

	/**
	 * Starts the game timers on the given server.
	 * @param server The server on which to start the timers.
	 */
	private static void startTimers(AOXPServer server) {

		LOGGER.info("Starting up game timers...");

		Timer timer = new Timer(true);

		// TODO : get task for each timer class (use the app context) and the interval for execution
	}

	/**
	 * Loads the application context on the given server.
	 * @param server The server on which to load the application context.
	 */
	private static void loadApplicationContext(AOXPServer server) throws DAOException {

		LOGGER.info("Loading application context...");

		LOGGER.info("Loading maps...");
		MapService mapService = ApplicationContext.getInstance(MapService.class);
		mapService.loadMaps();

		LOGGER.info("Loading cities...");
		mapService.loadCities();

		LOGGER.info("Loading world objects...");
		WorldObjectService objectService = ApplicationContext.getInstance(WorldObjectService.class);
		objectService.loadObjects();

		LOGGER.info("Loading NPCs...");
		NPCService npcService = ApplicationContext.getInstance(NPCService.class);
		npcService.loadNPCs();

		// TODO : Load other services and classes from application context
	}

}
