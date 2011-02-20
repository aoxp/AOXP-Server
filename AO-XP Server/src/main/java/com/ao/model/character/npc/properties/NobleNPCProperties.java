/**
 *
 */
package com.ao.model.character.npc.properties;

import com.ao.model.character.Alignment;
import com.ao.model.character.NPCType;
import com.ao.model.character.attack.AttackStrategy;
import com.ao.model.character.behavior.Behavior;
import com.ao.model.character.movement.MovementStrategy;
import com.ao.model.map.Heading;

/**
 * @author jsotuyod
 *
 */
public class NobleNPCProperties extends NPCProperties {

	protected Alignment alignment;

	/**
	 * Creates a new NobleNPCProperties instance.
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
	 * @param movementStrategy the npc's movement strategy.
	 * @param alignment The NPC's alignment.
	 */
	public NobleNPCProperties(NPCType type, int id, String name, short body,
			short head, Heading heading, boolean respawn, String description,
			Class<? extends Behavior> behavior,
			Class<? extends AttackStrategy> attackStrategy,
			Class<? extends MovementStrategy> movementStrategy, Alignment alignment) {
		super(type, id, name, body, head, heading, respawn, description,
				behavior, attackStrategy, movementStrategy);

		this.alignment = alignment;
	}

	/**
	 * @return the npc's alignment.
	 */
	public Alignment getAlignment() {
		return alignment;
	}
}
