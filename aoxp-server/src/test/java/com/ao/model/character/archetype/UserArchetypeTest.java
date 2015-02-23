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

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.ao.ioc.ArchetypeLocator;

public class UserArchetypeTest {

	@Test
	public void testGetArchetype() {
		assertThat(UserArchetype.ASSASIN.getArchetype(), instanceOf(AssasinArchetype.class));
		assertThat(UserArchetype.BANDIT.getArchetype(), instanceOf(BanditArchetype.class));
		assertThat(UserArchetype.BARD.getArchetype(), instanceOf(BardArchetype.class));
		assertThat(UserArchetype.CLERIC.getArchetype(), instanceOf(ClericArchetype.class));
		assertThat(UserArchetype.DRUID.getArchetype(), instanceOf(DruidArchetype.class));
		assertThat(UserArchetype.HUNTER.getArchetype(), instanceOf(HunterArchetype.class));
		assertThat(UserArchetype.MAGE.getArchetype(), instanceOf(MageArchetype.class));
		assertThat(UserArchetype.PALADIN.getArchetype(), instanceOf(PaladinArchetype.class));
		assertThat(UserArchetype.PIRATE.getArchetype(), instanceOf(PirateArchetype.class));
		assertThat(UserArchetype.THIEF.getArchetype(), instanceOf(ThiefArchetype.class));
		assertThat(UserArchetype.WARRIOR.getArchetype(), instanceOf(WarriorArchetype.class));
		assertThat(UserArchetype.WORKER.getArchetype(), instanceOf(WorkerArchetype.class));
	}

	@Test
	public void testValueOf() {
		final Archetype arch = ArchetypeLocator.getArchetype(AssasinArchetype.class);

		assertSame(UserArchetype.valueOf(arch), UserArchetype.ASSASIN);
	}

}
