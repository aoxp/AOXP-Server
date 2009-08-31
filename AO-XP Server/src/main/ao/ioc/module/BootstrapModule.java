package ao.ioc.module;

import java.util.Properties;

import ao.config.ServerConfig;
import ao.config.ini.ServerConfigIni;
import ao.network.ConnectionManager;
import ao.network.ConnectionManagerImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

/**
 * Module for application bootstrapping.
 */
public class BootstrapModule extends AbstractModule {

	protected Properties properties;
	
	/**
	 * Creates a new BootstrapModule.
	 * @param properties The general project properties.
	 */
	public BootstrapModule(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	protected void configure() {
		
		// General server configuration
		bind(ServerConfig.class).to(ServerConfigIni.class);
		bind(String.class).annotatedWith(Names.named("ServerConfigIni")).toInstance(properties.getProperty("config.path.server"));
		
		// Connection Manager
		bind(ConnectionManager.class).to(ConnectionManagerImpl.class).in(Singleton.class);
	}
}
