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

import com.ao.model.character.NPCType;
import com.ao.model.map.Heading;

/**
 * Defines a Resucitator NPC's properties. Allows a lightweight pattern implementation.
 */
public class ResucitatorNPCProperties extends NPCProperties {

	protected boolean newbie;
	
	/**
	 * Creates a new GuardNPCProperties instance.
	 * @param type the npc's type.
	 * @param id the npc's id.
	 * @param name the npc's name.
	 * @param body the npc's body.
	 * @param head the npc's head.
	 * @param heading the npc's heading.
	 * @param respawn the npc's respawn.
	 * @param newbie Whether the npc is newbie or not.
	 */
	public ResucitatorNPCProperties(NPCType type, int id, String name, short body, short head,
		Heading heading, boolean respawn, boolean newbie) {
		super(type, id, name, body, head, heading, respawn);
		
		this.newbie = newbie;
	}
	
	/**
	 * @return True if is an newbie resucitator, false otherwise.
	 */
	public boolean isNewbie() {
		return newbie;
	}

}