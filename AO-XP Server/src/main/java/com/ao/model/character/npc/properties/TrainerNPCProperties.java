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

package com.ao.model.character.npc.properties;

import java.util.Map;

import com.ao.model.character.Alignment;
import com.ao.model.character.NPCType;
import com.ao.model.character.attack.AttackStrategy;
import com.ao.model.character.behavior.Behavior;
import com.ao.model.map.Heading;

/**
 * Defines a NPC's properties. Allows a lightweight pattern implementation.
 */
public class TrainerNPCProperties extends AbstractNPCProperties {

	protected Map<Integer, String> creatures;

	/**
	 * Creates a new GuardNPCProperties instance.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn the npc's respawn.
	 * @param description the npc's description
	 * @param behavior the npc's behavior.
	 * @param attackStrategy the npc's attack strategy.
	 * @param alignment the npc's alignment
	 * @param creatures the npc's creatures.
	 */
	public TrainerNPCProperties(NPCType type, int id, String name, short body,
			short head, Heading heading, boolean respawn, 
			String description, Class<? extends Behavior> behavior,
			Class<? extends AttackStrategy> attackStrategy, Alignment alignment,
			Map<Integer, String> creatures) {
		super(type, id, name, body, head, heading, respawn, description, behavior, attackStrategy, alignment);

		this.creatures = creatures;
	}
	
	/**
	 * @return the creatures
	 */
	public Map<Integer, String> getCreatures() {
		return creatures;
	}
	
}
