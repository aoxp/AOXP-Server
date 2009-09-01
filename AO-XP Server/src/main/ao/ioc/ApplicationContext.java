package ao.ioc;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import ao.ioc.module.BootstrapModule;
import ao.ioc.module.ConfigurationModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * General Application Context. Capable of loading common application classes.
 */
public class ApplicationContext {

	private static final Logger logger = Logger.getLogger(ApplicationContext.class);
	
	/**
	 * The application's injector. All general use modules should be loaded here.
	 */
	private static Injector injector;
	
	
	static {
		Properties properties = new Properties();
		
		try {
			properties.load(new FileInputStream("project.properties"));
		} catch (Exception e) {
			logger.fatal("Error initializing application context", e);
			e.printStackTrace();
		}
		
		injector = Guice.createInjector(new BootstrapModule(properties),
				new ConfigurationModule(properties));
	}
	
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
