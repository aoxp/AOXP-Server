package ao.ioc;

import ao.config.ArchetypeConfigurationIni;
import ao.domain.character.archetype.Archetype;
import ao.ioc.module.ArchetypeModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class ArchetypeLocator {

	private static Injector injector;
	
	public ArchetypeLocator() throws Exception {
		injector = Guice.createInjector(new ArchetypeModule(new ArchetypeConfigurationIni()));
	}
	
	/**
	 * Locates archetype instances.
	 * @param archetype Class of the archetype to be located.
	 * @return The archetype instance.
	 */
	public static Archetype getArchetype(Class<?extends Archetype> archetype) {
		return injector.getInstance(archetype);
	}
}
