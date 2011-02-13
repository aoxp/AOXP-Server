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

import com.ao.model.character.Alignment;
import com.ao.model.character.NPCType;
import com.ao.model.character.attack.AttackStrategy;
import com.ao.model.character.behavior.Behavior;
import com.ao.model.map.Heading;
import com.ao.model.spell.Spell;

/**
 * Defines a NPC's properties. Allows a lightweight pattern implementation.
 */
public class CreatureNPCProperties extends AbstractNPCProperties {

	protected int experience;
	protected int gold;
	protected int minHP; // TODO : Esto es realmente necesario? Me parece una pelotudez cargar la vida inicial.
	protected int maxHP;
	protected int minDamage;
	protected int maxDamage;
	protected short defense;
	protected short magicDefense;
	protected short accuracy;
	protected short dodge;
	protected List<Spell> spells;
	protected boolean canSwim;
	protected boolean canWalk;
	protected boolean attackable;
	protected boolean poison;
	protected boolean paralyzable;
	protected boolean hostile;
	protected boolean tameable;

	/**
	 * Creates a new CreatureNPCProperties instance.
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
	 */
	public CreatureNPCProperties(NPCType type, int id, String name, short body, short head,
			Heading heading, boolean respawn, String description, Class<? extends Behavior> behavior,
			Class<? extends AttackStrategy> attackStrategy, Alignment alignment, 
			int experience, int gold, int minHP, int maxHP, 
			int minDamage, int maxDamage, short defense, short magicDefense, short accuracy, 
			short dodge, List<Spell> spells, boolean canSwim, boolean canWalk, boolean attackable,
			boolean poison, boolean paralyzable, boolean hostile, boolean tameable) {
		super(type, id, name, body, head, heading, respawn, description, behavior, attackStrategy, alignment);
		
		this.experience = experience;
		this.gold = gold;
		this.minHP = minHP;
		this.maxHP = maxHP;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.defense = defense;
		this.magicDefense = magicDefense;
		this.accuracy = accuracy;
		this.dodge = dodge;
		this.spells = spells;
		this.canSwim = canSwim;
		this.canWalk = canWalk;
		this.attackable = attackable;
		this.poison = poison;
		this.paralyzable = paralyzable;
		this.hostile = hostile;
		this.tameable = tameable;
	}
	
	/**
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}
	
	/**
	 * @return the experience
	 */
	public int getExperience() {
		return experience;
	}
	
	/**
	 * @return the minHP
	 */
	public int getMinHP() {
		return minHP;
	}
	
	/**
	 * @return the maxHP
	 */
	public int getMaxHP() {
		return maxHP;
	}
	
	/**
	 * @return the minDamage
	 */
	public int getMinDamage() {
		return minDamage;
	}
	
	/**
	 * @return the maxDamage
	 */
	public int getMaxDamage() {
		return maxDamage;
	}
	
	/**
	 * @return the defense
	 */
	public short getDefense() {
		return defense;
	}
	
	/**
	 * @return the magicDefense
	 */
	public short getMagicDefense() {
		return magicDefense;
	}
	
	/**
	 * @return the accuracy
	 */
	public short getAccuracy() {
		return accuracy;
	}
	/**
	 * @return the dodge
	 */
	public short getDodge() {
		return dodge;
	}
	
	/**
	 * @return True if the NPC can swim.
	 */
	public boolean canSwim() {
		return canSwim;
	}

	/**
	 * @return True if the NPC can walk on earth.
	 */
	public boolean canWalk() {
		return canWalk;
	}

	/**
	 * @return True if the NPC is attackable.
	 */
	public boolean isAttackable() {
		return attackable;
	}

	/**
	 * @return True if the NPC can poison.
	 */
	public boolean canPoison() {
		return poison;
	}

	/**
	 * @return True if the NPC is paralyzable.
	 */
	public boolean isParalyzable() {
		return paralyzable;
	}
	
	/**
	 * @return True if the NPC is paralyzable.
	 */
	public boolean isHostile() {
		return hostile;
	}
	
	/**
	 * @return True if the NPC is paralyzable.
	 */
	public boolean isTameable() {
		return tameable;
	}
	
	
	/**
	 * @return The amount of spells.
	 */
	public List<Spell> getSpells() {
		return spells;
	}
	
}