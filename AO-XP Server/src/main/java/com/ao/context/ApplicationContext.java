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

import com.ao.ioc.InjectorFactory;

import com.google.inject.Injector;

/**
 * General Application Context. Capable of loading common application classes.
 */
public class ApplicationContext {

	private static Injector injector;

	static {
		reload();
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

	/**
	 * Reloads all modules and associations.
	 * BEWARE, all previously created objects are not longer attached!
	 */
	public static void reload() {
		Properties properties = ApplicationProperties.getProperties();
		
		injector = InjectorFactory.get(properties);
	}
}
