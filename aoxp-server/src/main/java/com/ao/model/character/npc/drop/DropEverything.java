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
package com.ao.model.character.npc.drop;

import java.util.LinkedList;
import java.util.List;

import com.ao.model.character.npc.Drop;
import com.ao.model.inventory.Inventory;
import com.ao.model.worldobject.Item;
import com.ao.model.worldobject.WorldObject;
import com.ao.model.worldobject.factory.WorldObjectFactoryException;

/**
 * A Drop strategy that simply drops everything the NPC holds.
 * @author itirabasso
 */
public class DropEverything implements Drop {

	protected Inventory inventory;

	/**
	 * Create a new DropEverything instance.
	 * @param inventory An inventory of items to be dropped.
	 */
	public DropEverything(Inventory inventory) {
		this.inventory = inventory;
	}

	@Override
	public List<WorldObject> getDrops() throws WorldObjectFactoryException {
		List<WorldObject> items = new LinkedList<WorldObject>();

		for (final Item item : inventory) {
			if (item != null) {
				items.add(item);
			}
		}

		return items;
	}
}
