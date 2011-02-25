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

package com.ao.service;

import com.ao.data.dao.exception.DAOException;
import com.ao.model.character.npc.properties.NPCProperties;
import com.ao.model.worldobject.WorldObject;
import com.ao.model.worldobject.WorldObjectType;
import com.ao.model.worldobject.properties.WorldObjectProperties;

public interface WorldObjectService {

	/**
	 * Loads all objects.
	 * @throws DAOException
	 */
	void loadObjects() throws DAOException;
	
	/**
	 * Retrieves an WorldObjectType Properties with the given id.
	 * @param id the object's id.
	 * @return the object's properties.
	 */
	WorldObjectProperties getWorldObjectPropertiesById(int id);
	
	/**
	 * Retrieves the object type
	 * @param id the object's id.
	 * @return the object's properties.
	 */
	WorldObjectType getWorldObjectTypeById(int id);
	
	/**
	 * Retrieves a world object with the givenid.
	 * @param id the id of the object.
	 * @param amount the amount of objects.
	 * @return a world object.
	 */
	WorldObject getWorldObject(int id, int amount);
}
