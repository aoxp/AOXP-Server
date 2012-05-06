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

import com.ao.model.worldobject.WorldObjectType;

/**
 * Defines a door's properties. Allows a lightweight pattern implementation.
 */
public class DoorProperties extends WorldObjectProperties {

	protected boolean open;
	protected boolean locked;
	protected int code;
	protected DoorProperties otherStateProperties;

	/**
	 * Creates a new DoorProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param open Whether this door is open.
	 * @param locked Whether this door is locked.
	 * @param code The code used to unlock this door.
	 * @param otherStateProperties The object properties for the other state.
	 */
	public DoorProperties(WorldObjectType type, int id, String name, int graphic,
			boolean open, boolean locked, int code, DoorProperties otherStateProperties) {
		super(type, id, name, graphic);

		this.open = open;
		this.locked = locked;
		this.code = code;

		if (otherStateProperties != null) {
			setOtherStateProperties(otherStateProperties);
		}
	}

	/**
	 * Links this object with the one for the other state (open or closed)
	 * @param props The properties of the object to which to link it.
	 */
	private void setOtherStateProperties(DoorProperties props) {
		otherStateProperties = props;
		props.otherStateProperties = this;
	}

	/**
	 * @return the open.
	 */
	public boolean getOpen() {
		return open;
	}

	/**
	 * @return the locked.
	 */
	public boolean getLocked() {
		return locked;
	}

	/**
	 * @return the code.
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @return the properties for the other state.
	 */
	public DoorProperties getOtherStateProperties() {
		return otherStateProperties;
	}

}