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

package ao.ioc.module;

import java.util.Properties;

import ao.service.LoginService;
import ao.service.MapService;
import ao.service.login.LoginServiceImpl;
import ao.service.map.MapServiceImpl;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class ServiceModule extends AbstractModule {

	protected Properties properties;
	
	/**
	 * Creates a new ServiceModule.
	 * @param properties The general project properties.
	 */
	public ServiceModule(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	protected void configure() {
		bind(LoginService.class).to(LoginServiceImpl.class).in(Singleton.class);
		
		bind(MapService.class).to(MapServiceImpl.class).in(Singleton.class);

		bind(String.class).annotatedWith(Names.named("MapsPath")).toInstance(properties.getProperty("config.path.maps"));
		bind(Integer.class).annotatedWith(Names.named("MapAmount")).toInstance(Integer.parseInt(properties.getProperty("config.maps.amount")));
	}

}
