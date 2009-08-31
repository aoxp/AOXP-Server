package ao.ioc.module;

import ao.config.ServerConfig;
import ao.config.ini.ServerConfigIni;
import ao.network.ConnectionManager;
import ao.network.ConnectionManagerImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

/**
 * Module for application bootstrapping.
 */
public class BootstrapModule extends AbstractModule {

	@Override
	protected void configure() {
		
		// General server configuration
		bind(ServerConfig.class).to(ServerConfigIni.class);
		
		// Connection Manager
		bind(ConnectionManager.class).to(ConnectionManagerImpl.class).in(Singleton.class);
	}
}
