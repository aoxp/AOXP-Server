package ao.ioc.module;

import ao.domain.character.archetype.BardArchetype;
import ao.domain.character.archetype.ClericArchetype;
import ao.domain.character.archetype.DruidArchetype;
import ao.domain.character.archetype.FisherArchetype;
import ao.domain.character.archetype.LumberjackArchetype;
import ao.domain.character.archetype.MageArchetype;
import ao.domain.character.archetype.MinerArchetype;
import ao.domain.character.archetype.PirateArchetype;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ArchetypeModule extends AbstractModule {

	@Override
	protected void configure() {
	}
	
	@Provides
	MageArchetype provideMageArchetype() {
		return new MageArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Provides
	ClericArchetype provideClericArchetype() {
		return new ClericArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Provides
	BardArchetype provideBardArchetype() {
		return new BardArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Provides
	DruidArchetype provideDruidArchetype() {
		return new DruidArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Provides
	FisherArchetype provideFisherArchetype() {
		return new FisherArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Provides
	LumberjackArchetype provideLumberjackArchetype() {
		return new LumberjackArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
	
	@Provides
	MinerArchetype provideMinerArchetype() {
		return new MinerArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}

	@Provides
	PirateArchetype providePirateArchetype() {
		return new PirateArchetype(1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f);
	}
}
