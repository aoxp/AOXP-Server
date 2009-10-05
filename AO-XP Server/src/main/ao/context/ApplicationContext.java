package ao.context;

import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

import ao.ioc.InjectorFactory;

import com.google.inject.Injector;

/**
 * General Application Context. Capable of loading common application classes.
 */
public class ApplicationContext {

	public static final boolean SECURTY_ENABLED = false;
	
	private static final Logger logger = Logger.getLogger(ApplicationContext.class);
	private static Properties properties;
	private static Injector injector;
	
	static {
		try {
			properties.load(new FileInputStream("project.properties"));
		} catch (Exception e) {
			logger.fatal("Error initializing application context", e);
			e.printStackTrace();
		}
		
		injector = InjectorFactory.get(properties);
	}

	/**
	 * @return the properties
	 */
	public static Properties getProperties() {
		return properties;
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
