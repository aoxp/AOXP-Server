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

package com.ao.model.character.npc;

import java.util.List;

import com.ao.model.worldobject.WorldObject;
import com.ao.model.worldobject.factory.WorldObjectFactoryException;

/**
 * An interface to cope with the task of choosing what items to drop, if any.
 * @author itirabasso
 */
public interface Drop {

	/**
	 * Retrieve the list of objects to be dropped.
	 * @return a list with the drops.
	 * @throws WorldObjectFactoryException
	 */
	List<WorldObject> getDrops() throws WorldObjectFactoryException;

}
