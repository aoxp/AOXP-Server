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

package com.ao.ioc.module;

import java.util.Properties;

import com.ao.data.dao.AccountDAO;
import com.ao.data.dao.UserCharacterDAO;
import com.ao.data.dao.WorldMapDAO;
import com.ao.data.dao.WorldObjectPropertiesDAO;
import com.ao.data.dao.CityDAO;
import com.ao.data.dao.ini.NPCPropertiesDAOIni;
import com.ao.data.dao.ini.UserDAOIni;
import com.ao.data.dao.ini.WorldObjectPropertiesDAOIni;
import com.ao.data.dao.ini.CityDAOIni;
import com.ao.data.dao.map.WorldMapDAOImpl;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.name.Names;

public class DaoModule extends AbstractModule {

	protected Properties properties;
	
	/**
	 * Creates a new DaoModule.
	 * @param properties The general project properties.
	 */
	public DaoModule(Properties properties) {
		this.properties = properties;
	}
	
	@Override
	protected void configure() {
		bind(AccountDAO.class).to(UserDAOIni.class).in(Singleton.class);
		bind(UserCharacterDAO.class).to(UserDAOIni.class).in(Singleton.class);
		bind(WorldMapDAO.class).to(WorldMapDAOImpl.class).in(Singleton.class);
		
		bind(String.class).annotatedWith(Names.named("mapsPath")).toInstance(properties.getProperty("config.path.maps"));
		bind(Integer.class).annotatedWith(Names.named("mapsAmount")).toInstance(Integer.parseInt(properties.getProperty("config.maps.amount")));
		bind(String.class).annotatedWith(Names.named("mapsConfigFile")).toInstance("resources/maps.properties");
		
		bind(String.class).annotatedWith(Names.named("CharfilesPath")).toInstance(properties.getProperty("config.path.charfiles"));
		
		bind(WorldObjectPropertiesDAO.class).to(WorldObjectPropertiesDAOIni.class).in(Singleton.class);
		bind(String.class).annotatedWith(Names.named("objectsFilePath")).toInstance(properties.getProperty("config.path.objsdat"));
		bind(Integer.class).annotatedWith(Names.named("itemsPerRow")).toInstance(Integer.parseInt(properties.getProperty("config.inventory.itemperrow")));
	
		bind(CityDAO.class).to(CityDAOIni.class).in(Singleton.class);
		bind(String.class).annotatedWith(Names.named("citiesFilePath")).toInstance(properties.getProperty("config.path.citiesdat"));
		
		bind(NPCPropertiesDAOIni.class).to(NPCPropertiesDAOIni.class).in(Singleton.class);
		bind(String.class).annotatedWith(Names.named("npcsFilePath")).toInstance(properties.getProperty("config.path.npcsdat"));
	}
}
