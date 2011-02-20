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

import java.util.List;

import com.ao.model.character.NPCType;
import com.ao.model.character.attack.AttackStrategy;
import com.ao.model.character.behavior.Behavior;
import com.ao.model.character.movement.MovementStrategy;
import com.ao.model.map.Heading;
import com.ao.model.spell.Spell;

/**
 * Defines a Guard NPC's properties. Allows a lightweight pattern implementation.
 */
public class GuardNPCProperties extends CreatureNPCProperties {

	protected boolean originalPosition;

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
	 * @param movementStrategy The npc's movement strategy.
	 * @param experience the npc's experience
	 * @param gold the npc's gold.
	 * @param minHP the npc's min hp.
	 * @param maxHP the npc's max hp.
	 * @param minDamage the npc's min damage.
	 * @param maxDamage the npc's max damage.
	 * @param defense the npc's defense
	 * @param magicDefense the npc's magic defense
	 * @param accuracy the npc's accuracy
	 * @param dodge the npc's dodge
	 * @param spells the npc's spells
	 * @param canSwim Whether the npc is can swim or not.
	 * @param canWalk Whether the npc is can walk or not.
	 * @param attackable Whether the npc is attackable or not.
	 * @param poison Whether the npc is poison or not.
	 * @param paralyzable Whether the npc is paralyzable or not.
	 * @param hostile Whether the npc is hostile or not.
	 * @param tameable Whether the npc is tameable or not.
	 * @param originalPosition Whether the npc is original position or not.
	 */
	public GuardNPCProperties(NPCType type, int id, String name, short body,
			short head, Heading heading, boolean respawn, String description,
			Class<? extends Behavior> behavior,
			Class<? extends AttackStrategy> attackStrategy, Class<? extends MovementStrategy> movementStrategy,
			int experience, int gold, int minHP, int maxHP, int minDamage,
			int maxDamage, short defense, short magicDefense, short accuracy,
			short dodge, List<Spell> spells, boolean canSwim, boolean canWalk,
			boolean attackable, boolean poison, boolean paralyzable,
			boolean hostile, boolean tameable, boolean originalPosition) {
		super(type, id, name, body, head, heading, respawn, description,
				behavior, attackStrategy, movementStrategy, experience, gold, minHP, maxHP, minDamage,
				maxDamage, defense, magicDefense, accuracy, dodge, spells,
				canSwim, canWalk, attackable, poison, paralyzable, hostile,
				tameable);

		this.originalPosition = originalPosition;
	}

	/**
	 * @return
	 */
	public boolean hasOriginalPosition() {
		return originalPosition;
	}

}