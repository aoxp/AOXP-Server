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
package com.ao.data.dao.ini;


import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.WorldObjectPropertiesDAO;
import com.ao.data.dao.exception.DAOException;
import com.ao.model.character.NPCType;
import com.ao.model.character.npc.properties.CreatureNPCProperties;
import com.ao.model.character.npc.properties.GovernorNPCProperties;
import com.ao.model.character.npc.properties.GuardNPCProperties;
import com.ao.model.character.npc.properties.NPCProperties;
import com.ao.model.character.npc.properties.NobleNPCProperties;
import com.ao.model.character.npc.properties.TrainerNPCProperties;
import com.ao.model.worldobject.AbstractItem;
import com.ao.model.worldobject.factory.WorldObjectFactory;
import com.ao.model.worldobject.properties.WorldObjectProperties;

/**
 * Test for NPCPropertiesDAOIni
 * @author jsotuyod
 */
public class NPCPropertiesDAOIniTest {

	private static final int COMMON_NPC_INDEX = 503;
	private static final int DRAGON_NPC_INDEX = 541;
	private static final int TRAINER_NPC_INDEX = 59;
	private static final int GOVERNOR_NPC_INDEX = 12;
	private static final int ROYAL_GUARD_NPC_INDEX = 5;
	private static final int CHAOS_GUARD_NPC_INDEX = 126;
	private static final int NEWBIE_RESUCITATOR_NPC_INDEX = 118;
	private static final int RESUCITATOR_NPC_INDEX = 4;
	private static final int GAMBLER_NPC_INDEX = 103;
	private static final int BANKER_NPC_INDEX = 23;
	private static final int NOBLE_NPC_INDEX = 71;

	private static final String TEST_NPCS_DAT = "src/test/resources/NPCs.dat";

	private NPCPropertiesDAOIni dao;

	@Before
	public void setUp() throws Exception {
		final WorldObjectProperties woProperties = mock(WorldObjectProperties.class);
		final AbstractItem item = mock(AbstractItem.class);

		final WorldObjectPropertiesDAO woDao = mock(WorldObjectPropertiesDAO.class);
		when(woDao.getWorldObjectProperties(anyInt())).thenReturn(woProperties);

		final WorldObjectFactory woFactory = mock(WorldObjectFactory.class);
		when(woFactory.getWorldObject(eq(woProperties), anyInt())).thenReturn(item);

		dao = new NPCPropertiesDAOIni(TEST_NPCS_DAT, woDao, woFactory);
	}

	@Test
	public void testRetrieveAll() {
		final NPCProperties[] npcProperties;
		try {
			npcProperties = dao.retrieveAll();
		} catch (final DAOException e) {
			fail("Loading of npcs failed with message " + e.getMessage());
			return;
		}

		final NPCProperties snake = npcProperties[COMMON_NPC_INDEX];
		assertThat(snake, instanceOf(CreatureNPCProperties.class));
		assertEquals(NPCType.COMMON, snake.getType());

		final NPCProperties dragon = npcProperties[DRAGON_NPC_INDEX];
		assertThat(dragon, instanceOf(CreatureNPCProperties.class));
		assertEquals(NPCType.DRAGON, dragon.getType());

		final NPCProperties trainer = npcProperties[TRAINER_NPC_INDEX];
		assertThat(trainer, instanceOf(TrainerNPCProperties.class));
		assertEquals(NPCType.TRAINER, trainer.getType());

		final NPCProperties governor = npcProperties[GOVERNOR_NPC_INDEX];
		assertThat(governor, instanceOf(GovernorNPCProperties.class));
		assertEquals(NPCType.GOVERNOR, governor.getType());

		final NPCProperties royalGuard = npcProperties[ROYAL_GUARD_NPC_INDEX];
		assertThat(royalGuard, instanceOf(GuardNPCProperties.class));
		assertEquals(NPCType.ROYAL_GUARD, royalGuard.getType());

		final NPCProperties chaosGuard = npcProperties[CHAOS_GUARD_NPC_INDEX];
		assertThat(chaosGuard, instanceOf(GuardNPCProperties.class));
		assertEquals(NPCType.CHAOS_GUARD, chaosGuard.getType());

		final NPCProperties newbieResucitator = npcProperties[NEWBIE_RESUCITATOR_NPC_INDEX];
		assertThat(newbieResucitator, instanceOf(NPCProperties.class));
		assertEquals(NPCType.NEWBIE_RESUCITATOR, newbieResucitator.getType());

		final NPCProperties resucitator = npcProperties[RESUCITATOR_NPC_INDEX];
		assertThat(resucitator, instanceOf(NPCProperties.class));
		assertEquals(NPCType.RESUCITATOR, resucitator.getType());

		final NPCProperties gambler = npcProperties[GAMBLER_NPC_INDEX];
		assertThat(gambler, instanceOf(NPCProperties.class));
		assertEquals(NPCType.GAMBLER, gambler.getType());

		final NPCProperties banker = npcProperties[BANKER_NPC_INDEX];
		assertThat(banker, instanceOf(NPCProperties.class));
		assertEquals(NPCType.BANKER, banker.getType());

		final NPCProperties noble = npcProperties[NOBLE_NPC_INDEX];
		assertThat(noble, instanceOf(NobleNPCProperties.class));
		assertEquals(NPCType.NOBLE, noble.getType());
	}

}
