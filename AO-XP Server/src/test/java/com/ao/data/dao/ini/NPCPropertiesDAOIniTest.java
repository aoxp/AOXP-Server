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


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.ao.data.dao.exception.DAOException;
import com.ao.model.character.NPCType;
import com.ao.model.character.npc.properties.CreatureNPCProperties;
import com.ao.model.character.npc.properties.GovernorNPCProperties;
import com.ao.model.character.npc.properties.GuardNPCProperties;
import com.ao.model.character.npc.properties.NPCProperties;
import com.ao.model.character.npc.properties.NobleNPCProperties;
import com.ao.model.character.npc.properties.TrainerNPCProperties;

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

	protected NPCPropertiesDAOIni dao;

	@Before
	public void setUp() throws Exception {
		dao = new NPCPropertiesDAOIni(TEST_NPCS_DAT);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testRetrieveAll() {
		NPCProperties[] npcProperties = null;
		try {
			npcProperties = dao.retrieveAll();
		} catch (DAOException e) {
			fail("Loading of npcs failed with message " + e.getMessage());
		}

		NPCProperties snake = npcProperties[COMMON_NPC_INDEX];
		assertTrue(snake instanceof CreatureNPCProperties);
		assertEquals(NPCType.COMMON, snake.getType());

		NPCProperties dragon = npcProperties[DRAGON_NPC_INDEX];
		assertTrue(dragon instanceof CreatureNPCProperties);
		assertEquals(NPCType.DRAGON, dragon.getType());

		NPCProperties trainer = npcProperties[TRAINER_NPC_INDEX];
		assertTrue(trainer instanceof TrainerNPCProperties);
		assertEquals(NPCType.TRAINER, trainer.getType());

		NPCProperties governor = npcProperties[GOVERNOR_NPC_INDEX];
		assertTrue(governor instanceof GovernorNPCProperties);
		assertEquals(NPCType.GOVERNOR, governor.getType());

		NPCProperties royalGuard = npcProperties[ROYAL_GUARD_NPC_INDEX];
		assertTrue(royalGuard instanceof GuardNPCProperties);
		assertEquals(NPCType.ROYAL_GUARD, royalGuard.getType());

		NPCProperties chaosGuard = npcProperties[CHAOS_GUARD_NPC_INDEX];
		assertTrue(chaosGuard instanceof GuardNPCProperties);
		assertEquals(NPCType.CHAOS_GUARD, chaosGuard.getType());

		NPCProperties newbieResucitator = npcProperties[NEWBIE_RESUCITATOR_NPC_INDEX];
		assertTrue(newbieResucitator instanceof NPCProperties);
		assertEquals(NPCType.NEWBIE_RESUCITATOR, newbieResucitator.getType());

		NPCProperties resucitator = npcProperties[RESUCITATOR_NPC_INDEX];
		assertTrue(resucitator instanceof NPCProperties);
		assertEquals(NPCType.RESUCITATOR, resucitator.getType());

		NPCProperties gambler = npcProperties[GAMBLER_NPC_INDEX];
		assertTrue(gambler instanceof NPCProperties);
		assertEquals(NPCType.GAMBLER, gambler.getType());

		NPCProperties banker = npcProperties[BANKER_NPC_INDEX];
		assertTrue(banker instanceof NPCProperties);
		assertEquals(NPCType.BANKER, banker.getType());

		NPCProperties noble = npcProperties[NOBLE_NPC_INDEX];
		assertTrue(noble instanceof NobleNPCProperties);
		assertEquals(NPCType.NOBLE, noble.getType());
	}

}
