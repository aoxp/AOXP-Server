package ao.ioc;

import java.util.Properties;

import ao.ioc.module.BootstrapModule;
import ao.ioc.module.ConfigurationModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class InjectorFactory {

	/**
	 * Retrieves a new injector with the given properties.
	 * @param properties The injector properties.
	 * @return The injector.
	 */
	public static Injector get(Properties properties) {
		return Guice.createInjector(new BootstrapModule(properties), new ConfigurationModule(properties));
	}
}
