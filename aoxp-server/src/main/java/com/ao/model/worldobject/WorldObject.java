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

package com.ao.model.worldobject;

/**
 * An object within the world.
 */
public interface WorldObject {

	/**
	 * Retrieves the item's unique id.
	 * @return The item's unique id.
	 */
	int getId();

	/**
	 * Retrieves the object's graphic index.
	 * @return The object's graphic index.
	 */
	int getGraphic();

	/**
	 * Retrieves the object's name.
	 * @return The object's name.
	 */
	String getName();

	/**
	 * Retrieves the object's type.
	 * @return The object's type.
	 */
	WorldObjectType getObjectType();

	/**
	 * Checks whether the object is fixed in the map or not.
	 * @return Whether the object is ixed in the map.
	 */
	boolean isFixed();
}
