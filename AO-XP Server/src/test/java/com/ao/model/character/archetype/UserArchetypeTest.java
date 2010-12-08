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

package com.ao.model.character.archetype;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.ao.ioc.ArchetypeLocator;

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
