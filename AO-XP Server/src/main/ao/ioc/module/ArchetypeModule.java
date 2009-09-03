package ao.ioc.module;

import ao.config.ArchetypeConfiguration;
import ao.model.character.archetype.Archetype;
import ao.model.character.archetype.BardArchetype;
import ao.model.character.archetype.ClericArchetype;
import ao.model.character.archetype.DruidArchetype;
import ao.model.character.archetype.FisherArchetype;
import ao.model.character.archetype.LumberjackArchetype;
import ao.model.character.archetype.MageArchetype;
import ao.model.character.archetype.MinerArchetype;
import ao.model.character.archetype.PirateArchetype;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ArchetypeModule extends AbstractModule {

	private ArchetypeConfiguration config;
	
	@Override
 	protected void configure() {
	}
	
	public ArchetypeModule(ArchetypeConfiguration config) {
		this.config = config;
	}
	
	/**
	 * Retrieves a new instance of the given archetype class.
	 * @param archetype The archetype class.
	 * @return A new instance of the archetype.
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private Archetype getArchetype(Class <?extends Archetype> archetype) throws Exception {
		Class[] args = new Class[] {
				float.class, float.class, float.class,
				float.class, float.class, float.class,
				float.class
			};
		
		return archetype.getConstructor(args)
			.newInstance(
				config.getEvasionModifier(archetype),
				config.getMeleeAccuracyModifier(archetype),
				config.getRangedAccuracyModifier(archetype),
				config.getMeleeDamageModifier(archetype),
				config.getRangedDamageModifier(archetype),
				config.getWrestlingDamageModifier(archetype),
				config.getBlockPowerModifier(archetype)
				);
	}
	
	@Provides
	MageArchetype provideMageArchetype() throws Exception {
		return (MageArchetype) getArchetype(MageArchetype.class);
	}
	
	@Provides
	ClericArchetype provideClericArchetype() throws Exception {
		return (ClericArchetype) getArchetype(ClericArchetype.class);
	}
	
	@Provides
	BardArchetype provideBardArchetype() throws Exception {
		return (BardArchetype) getArchetype(BardArchetype.class);
	}
	
	@Provides
	DruidArchetype provideDruidArchetype() throws Exception {
		return (DruidArchetype) getArchetype(DruidArchetype.class);
	}
	
	@Provides
	FisherArchetype provideFisherArchetype() throws Exception {
		return (FisherArchetype) getArchetype(FisherArchetype.class);
	}
	
	@Provides
	LumberjackArchetype provideLumberjackArchetype() throws Exception {
		return (LumberjackArchetype) getArchetype(LumberjackArchetype.class);
	}
	
	@Provides
	MinerArchetype provideMinerArchetype() throws Exception {
		return (MinerArchetype) getArchetype(MinerArchetype.class);
	}

	@Provides
	PirateArchetype providePirateArchetype() throws Exception {
		return (PirateArchetype) getArchetype(PirateArchetype.class);
	}
}
