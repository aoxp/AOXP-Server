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
 * Defines an Item's properties. Allows a lightweight pattern implementation.
 */
public class ItemProperties extends WorldObjectProperties {

	protected int value;
	protected int manufactureDifficulty;
	protected boolean newbie;
	
	protected List<UserArchetype> forbiddenArchetypes;
	protected List<Race> forbiddenRaces;
	
	protected boolean noLog;
	protected boolean falls;
	protected boolean respawnable;
	
	/**
	 * Creates a new ItemProperties instance.
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
	 */
	public ItemProperties(WorldObjectType type, int id, String name, int graphic,
			int value,
			int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, List<Race> forbiddenRaces, boolean newbie, 
			boolean noLog, boolean falls, boolean respawnable) {
		
		super(type, id, name, graphic);
		
		this.value = value;
		this.manufactureDifficulty = manufactureDifficulty;
		this.forbiddenArchetypes = forbiddenArchetypes;
		this.forbiddenRaces = forbiddenRaces;
		this.newbie = newbie;
		this.noLog = noLog;
		this.falls = falls;
		this.respawnable = respawnable;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/**
	 * @return the manufactureDifficulty
	 */
	public int getManufactureDifficulty() {
		return manufactureDifficulty;
	}

	/**
	 * @return the newbie
	 */
	public boolean isNewbie() {
		return newbie;
	}

	/**
	 * @return the forbiddenArchetypes
	 */
	public List<UserArchetype> getForbiddenArchetypes() {
		return forbiddenArchetypes;
	}

	/**
	 * @return the forbiddenRaces
	 */
	public List<Race> getForbiddenRaces() {
		return forbiddenRaces;
	}
	
	/**
	 * @return the noLog.
	 */
	public boolean isNoLog() {
		return noLog;
	}
	/**
	 * @return the falls.
	 */
	public boolean isFalls() {
		return falls;
	}
	/**
	 * @return the respawn.
	 */
	public boolean isRespawnable() {
		return respawnable;
	}
}
