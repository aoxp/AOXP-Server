package ao.domain.character.archetype;


/**
 * User Archetype enumerator. Wraps archetype classes in an enum.
 * @author jsotuyod
 */
public enum UserArchetype {
	MAGE(new MageArchetype()),
//	CLERIC(new ClericArchetype()),
//	WARRIOR(new WarriorArchetype()),
//	ASSASIN(new AssasinArchetype()),
//	THIEF(new ThiefArchetype()),
//	BARD(new BardArchetype()),
//	DRUID(new DruidArchetype()),
//	BANDIT(new BanditArchetype()),
//	PALADIN(new PaladinArchetype()),
//	HUNTER(new HunterArchetype()),
	FISHER(new FisherArchetype()),
//	BLACKSMITH(new BlacksmithArchetype()),
	LUMBERJACK(new LumberjackArchetype()),
//	MINER(new MinerArchetype()),
//	CARPENTER(new CarpenterArchetype()),
	PIRAT(new PiratArchetype());
	
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
