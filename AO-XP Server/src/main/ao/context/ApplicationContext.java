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

package ao.context;

import java.io.File;
import java.io.FileInputStream;
import java.net.URI;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ao.ioc.InjectorFactory;

import com.google.inject.Injector;

/**
 * General Application Context. Capable of loading common application classes.
 */
public class ApplicationContext {

	public static final boolean SECURITY_ENABLED = false;
	
	private static final Logger logger = Logger.getLogger(ApplicationContext.class);
	private static Properties properties = new Properties();
	private static Injector injector;

	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		// Load global.properties
		try {
			URI fileUri = loader.getResource("global.properties").toURI();
			Properties globalProperties = new Properties();
			File propFile = new File(fileUri);
			
			if (propFile.exists()) {
				globalProperties.load(new FileInputStream(propFile));
				
				for (Entry<Object, Object> property : globalProperties.entrySet()) {
					 System.setProperty((String) property.getKey(), (String) property.getValue());
				}
			}
		} catch (Exception e) {
			logger.fatal("Error initializing application context", e);
			e.printStackTrace();
		}
		
		// Load project.properties
		try {
			URI fileUri = loader.getResource("project.properties").toURI();
			properties.load(new FileInputStream(new File(fileUri)));
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
