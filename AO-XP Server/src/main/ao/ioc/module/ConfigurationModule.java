package ao.ioc.module;

import java.util.Properties;

import ao.config.ArchetypeConfiguration;
import ao.config.ini.ArchetypeConfigurationIni;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;

/**
 * Module for game specific configuration.
 */
public class ConfigurationModule extends AbstractModule {

	protected Properties properties;
	
	/**
	 * Creates a new ConfigurationModule.
	 * @param properties The general project properties.
	 */
	public ConfigurationModule(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	protected void configure() {
		
		// Bind game specific configuration
		bind(ArchetypeConfiguration.class).to(ArchetypeConfigurationIni.class);
		bind(String.class).annotatedWith(Names.named("ArchetypeConfigIni")).toInstance(properties.getProperty("config.path.archetype"));
	}

}
