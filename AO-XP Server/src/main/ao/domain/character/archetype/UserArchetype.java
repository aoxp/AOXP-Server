package ao.domain.character.archetype;

import ao.ioc.ArchetypeLocator;

/**
 * User Archetype enumerator. Wraps archetype classes in an enum.
 */
public enum UserArchetype {
	MAGE(ArchetypeLocator.getArchetype(MageArchetype.class)),
	CLERIC(ArchetypeLocator.getArchetype(ClericArchetype.class)),
	WARRIOR(ArchetypeLocator.getArchetype(WarriorArchetype.class)),
	ASSASIN(ArchetypeLocator.getArchetype(AssasinArchetype.class)),
	THIEF(ArchetypeLocator.getArchetype(ThiefArchetype.class)),
	BARD(ArchetypeLocator.getArchetype(BardArchetype.class)),
	DRUID(ArchetypeLocator.getArchetype(DruidArchetype.class)),
	BANDIT(ArchetypeLocator.getArchetype(BanditArchetype.class)),
	PALADIN(ArchetypeLocator.getArchetype(PaladinArchetype.class)),
	HUNTER(ArchetypeLocator.getArchetype(HunterArchetype.class)),
	FISHER(ArchetypeLocator.getArchetype(FisherArchetype.class)),
	BLACKSMITH(ArchetypeLocator.getArchetype(BlacksmithArchetype.class)),
	LUMBERJACK(ArchetypeLocator.getArchetype(LumberjackArchetype.class)),
	MINER(ArchetypeLocator.getArchetype(MinerArchetype.class)),
	CARPENTER(ArchetypeLocator.getArchetype(CarpenterArchetype.class)),
	PIRATE(ArchetypeLocator.getArchetype(PirateArchetype.class));
	
	private Archetype archetype;
	
	/**
	 * Create a new UserArchetype
	 * @param archetype The Archetype class corresponding to this UserArchetype.
	 */
	private UserArchetype(Archetype archetype) {
		this.archetype = archetype;
	}

	/**
	 * Retrieves the Archetype related to this UserArchetype.
	 * @return The Archetype related to this UserArchetype.
	 */
	public Archetype getArchetype() {
		return archetype;
	}
	
	/**
	 * Allows to retrieve the enum back from the Archetype.
	 * @param archetype The Archetype to look up in the enum.
	 * @return The UserArchetype matching the given Archetype, null if none matches.
	 */
	public static UserArchetype valueOf(Archetype archetype) {
		String archetypeClassName = archetype.getClass().getSimpleName();
		
		for (UserArchetype arch : UserArchetype.values()) {
			if (arch.getArchetype().getClass().getSimpleName() == archetypeClassName) {
				return arch;
			}
		}
		
		return null;
	}
}
