package ao.ioc;

import ao.ioc.module.BootstrapModule;
import ao.ioc.module.ConfigurationModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * General Application Context. Capable of loading common application classes.
 */
public class ApplicationContext {

	/**
	 * The application's injector. All general use modules should be loaded here.
	 */
	private static Injector injector = Guice.createInjector(new ConfigurationModule(),
										new BootstrapModule());
	
	/**
	 * Retrieves an instance of the requested class.
	 * @param <T> Type of the object being requested.
	 * @param clazz The class of the object being requested.
	 * @return An instance of the requested class.
	 */
	public static <T> T getInstance(Class<T> clazz) {
		return injector.getInstance(clazz);
	}
}
