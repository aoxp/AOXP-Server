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

package com.ao.context;

import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * General properties for the application.
 */
public class ApplicationProperties {

	private static final Logger logger = Logger.getLogger(ApplicationProperties.class);
	
	private static Properties properties = new Properties();
	
	static {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		// Load global.properties
		try {
			Properties globalProperties = new Properties();
			globalProperties.load(loader.getResourceAsStream("global.properties"));
			System.getProperties().putAll(globalProperties);
		} catch (Exception e) {
			logger.fatal("Error global loading application properties", e);
			e.printStackTrace();
		}
		
		loadProperties("project.properties");
	}


	/**
	 * @return the properties
	 */
	public static Properties getProperties() {
		return properties;
	}

	/**
	 * Loads all properties from the given file. It's automatically searched as a resource.
	 * @param fileName The name of the file from which to load properties.
	 */
	public static void loadProperties(String fileName) {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		
		Properties props = new Properties();
		
		// Load project.properties
		try {
			props.load(loader.getResourceAsStream(fileName));
		} catch (Exception e) {
			logger.fatal("Error loading " + fileName + " properties file.", e);
			e.printStackTrace();
		}
		
		properties.putAll(props);
	}
}
