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

import java.util.List;
import java.util.Properties;

import com.ao.service.LoginService;
import com.ao.service.MapService;
import com.ao.service.TimedEventsService;
import com.ao.service.UserService;
import com.ao.service.WorldObjectService;
import com.ao.service.login.LoginServiceImpl;
import com.ao.service.map.MapServiceImpl;
import com.ao.service.timedevents.TimedEventsServiceImpl;
import com.ao.service.user.UserServiceImpl;
import com.ao.service.worldobject.WorldObjectServiceImpl;
import com.ao.service.CharacterBodyService;
import com.ao.service.CharacterBodyServiceImpl;
import com.ao.utils.RangeParser;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
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
		bind(TimedEventsService.class).to(TimedEventsServiceImpl.class).in(Singleton.class);
		bind(WorldObjectService.class).to(WorldObjectServiceImpl.class).in(Singleton.class);
		bind(UserService.class).to(UserServiceImpl.class).in(Singleton.class);
		bind(CharacterBodyService.class).to(CharacterBodyServiceImpl.class).in(Singleton.class);
		
		bind(Integer.class).annotatedWith(Names.named("initialAvailableSkills")).toInstance(Integer.parseInt(properties.getProperty("config.loginservice.initialavailableskills")));
		
		
		
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsDarkelfMale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.darkelf.male")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsDarkelfFemale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.darkelf.female")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsDwarfMale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.dwarf.male")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsDwarfFemale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.dwarf.female")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsElfMale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.elf.male")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsElfFemale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.elf.female")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsGnomeMale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.gnome.male")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsGnomeFemale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.gnome.female")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsHumanMale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.human.male")));
		bind(new TypeLiteral<List<Integer>>(){}).annotatedWith(Names.named("headsHumanFemale")).toInstance(RangeParser.parseIntegers(properties.getProperty("config.heads.human.female")));	
		
	}

}
