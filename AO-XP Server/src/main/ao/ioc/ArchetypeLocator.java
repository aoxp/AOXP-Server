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

package ao.ioc;

import ao.config.ArchetypeConfiguration;
import ao.context.ApplicationContext;
import ao.ioc.module.ArchetypeModule;
import ao.model.character.archetype.Archetype;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ArchetypeLocator {

	private static Injector injector = Guice.createInjector(new ArchetypeModule(ApplicationContext.getInstance(ArchetypeConfiguration.class)));
	
	/**
	 * Locates archetype instances.
	 * @param archetype Class of the archetype to be located.
	 * @return The archetype instance.
	 */
	public static Archetype getArchetype(Class<?extends Archetype> archetype) {
		return injector.getInstance(archetype);
	}
}
