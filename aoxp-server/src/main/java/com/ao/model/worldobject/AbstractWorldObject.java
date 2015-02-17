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

import com.ao.model.worldobject.properties.WorldObjectProperties;

/**
 * Abstract implementation of world object, provides most functionality.
 */
public abstract class AbstractWorldObject implements WorldObject {

	protected WorldObjectProperties properties;

	/**
	 * Creates a new AbstractWorldObject instance.
	 * @param properties The object's properties.
	 */
	public AbstractWorldObject(WorldObjectProperties properties) {
		this.properties = properties;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.WorldObject#getGraphic()
	 */
	@Override
	public int getGraphic() {
		return properties.getGraphic();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.WorldObject#getId()
	 */
	@Override
	public int getId() {
		return properties.getId();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.WorldObject#getName()
	 */
	@Override
	public String getName() {
		return properties.getName();
	}

	@Override
	public WorldObjectType getObjectType() {
		return properties.getType();
	}
}
