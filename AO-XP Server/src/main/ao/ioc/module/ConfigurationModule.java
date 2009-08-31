package ao.ioc.module;

import ao.config.ArchetypeConfiguration;
import ao.config.ini.ArchetypeConfigurationIni;

import com.google.inject.AbstractModule;

/**
 * Module for game specific configuration.
 */
public class ConfigurationModule extends AbstractModule {

	@Override
	protected void configure() {
		
		// Bind game specific configuration
		bind(ArchetypeConfiguration.class).to(ArchetypeConfigurationIni.class);
	}

}
