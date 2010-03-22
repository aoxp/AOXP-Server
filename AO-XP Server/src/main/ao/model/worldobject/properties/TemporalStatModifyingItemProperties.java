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

package ao.model.worldobject.properties;

import java.util.List;

import ao.model.character.Race;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.WorldObjectType;

/**
 * Defines an Item that modifies a user's stats's properties for a given period of time. Allows a lightweight pattern implementation.
 */
public class TemporalStatModifyingItemProperties extends
		StatModifyingItemProperties {
	
	protected int effectTime;

	/**
	 * Creates a new StatModifyingItemProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param value The item's value.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param forbiddenRaces List of Races not allowed to use this item.
	 * @param newbie Whether the item is newbie or not.
	 * @param minModifier The minimum amount by which the stats is to be modified.
	 * @param maxModifier The maximum amount by which the stats is to be modified.
	 * @param effectTime The time for which the effect is valid.
	 */
	public TemporalStatModifyingItemProperties(WorldObjectType type, int id,
			String name, int graphic, int value,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes,
			List<Race> forbiddenRaces, boolean newbie,
			boolean noLog, boolean falls, boolean respawnable, 
			int minModifier, int maxModifier, int effectTime) {
		super(type, id, name, graphic, value, manufactureDifficulty,
				forbiddenArchetypes, forbiddenRaces, newbie, noLog, falls, respawnable, minModifier, maxModifier);
		
		this.effectTime = effectTime;
	}

	/**
	 * @return the effectTime
	 */
	public int getEffectDuration() {
		return effectTime;
	}
}
