package ao.model.character.archetype;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ao.ioc.ArchetypeLocator;

public class UserArchetypeTest {

	@Test
	public void testGetArchetype() {
		assertTrue(UserArchetype.ASSASIN.getArchetype() instanceof AssasinArchetype);
		assertTrue(UserArchetype.BANDIT.getArchetype() instanceof BanditArchetype);
		assertTrue(UserArchetype.BARD.getArchetype() instanceof BardArchetype);
		assertTrue(UserArchetype.BLACKSMITH.getArchetype() instanceof BlacksmithArchetype);
		assertTrue(UserArchetype.CARPENTER.getArchetype() instanceof CarpenterArchetype);
		assertTrue(UserArchetype.CLERIC.getArchetype() instanceof ClericArchetype);
		assertTrue(UserArchetype.DRUID.getArchetype() instanceof DruidArchetype);
		assertTrue(UserArchetype.FISHER.getArchetype() instanceof FisherArchetype);
		assertTrue(UserArchetype.HUNTER.getArchetype() instanceof HunterArchetype);
		assertTrue(UserArchetype.LUMBERJACK.getArchetype() instanceof LumberjackArchetype);
		assertTrue(UserArchetype.MAGE.getArchetype() instanceof MageArchetype);
		assertTrue(UserArchetype.MINER.getArchetype() instanceof MinerArchetype);
		assertTrue(UserArchetype.PALADIN.getArchetype() instanceof PaladinArchetype);
		assertTrue(UserArchetype.PIRATE.getArchetype() instanceof PirateArchetype);
		assertTrue(UserArchetype.THIEF.getArchetype() instanceof ThiefArchetype);
		assertTrue(UserArchetype.WARRIOR.getArchetype() instanceof WarriorArchetype);
	}

	@Test
	public void testValueOf() {
		Archetype arch = ArchetypeLocator.getArchetype(AssasinArchetype.class);
		
		assertTrue(UserArchetype.valueOf(arch) == UserArchetype.ASSASIN);
	}

}
