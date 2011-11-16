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

package com.ao.model.worldobject.properties;

import java.util.List;

import com.ao.model.character.Race;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.worldobject.WorldObjectType;

/**
 * Defines an Item that modifies a user's stats's properties and is reloadable.
 * Allows a lightweight pattern implementation.
 */
public class RefillableStatModifyingItemProperties extends
		StatModifyingItemProperties {

	protected boolean filled;
	protected RefillableStatModifyingItemProperties otherStateProperties;

	/**
	 * Creates a new StatModifyingItemProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param value The item's value.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param forbiddenRaces List of Races not allowed to use this item.
	 * @param newbie Whether the item is newbie or not.
	 * @param noLog Whether this item should be logged or not.
	 * @param falls Whether this item falls or not.
	 * @param respawnable Whether this item respawns or not when in a merchant NPC's inventory.
	 * @param minModifier The minimum amount by which the stats is to be modified.
	 * @param maxModifier The maximum amount by which the stats is to be modified.
	 * @param filled True if the object is filled, false if empty.
	 * @param otherStateProperties The object properties to be used in the other state.
	 */
	public RefillableStatModifyingItemProperties(WorldObjectType type, int id, String name, int graphic,
			int value, List<UserArchetype> forbiddenArchetypes,
			List<Race> forbiddenRaces, boolean newbie,
			boolean noLog, boolean falls, boolean respawnable, int minModifier, int maxModifier,
			boolean filled, RefillableStatModifyingItemProperties otherStateProperties) {
		super(type, id, name, graphic, value, forbiddenArchetypes, forbiddenRaces, newbie, noLog, falls,
				respawnable, minModifier, maxModifier);

		this.filled = filled;

		// If the other state is set, link them together
		if (otherStateProperties != null) {
			setOtherStateProperties(otherStateProperties);
		}
	}

	/**
	 * Links this object with the one for the other state (empty or filled)
	 * @param props The properties of the object to which to link it.
	 */
	private void setOtherStateProperties(RefillableStatModifyingItemProperties props) {
		otherStateProperties = props;
		props.otherStateProperties = this;
	}

	/**
	 * @return the other state object properties.
	 */
	public RefillableStatModifyingItemProperties getOtherStateProperties() {
		return otherStateProperties;
	}

	/**
	 * @return whether the object is filled or empty.
	 */
	public boolean isFilled() {
		return filled;
	}
}
