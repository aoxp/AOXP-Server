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

import ao.model.worldobject.WorldObjectType;

/**
 * Defines a ResourceSourceProperties properties. Allows a lightweight pattern implementation.
 */
public class ResourceSourceProperties extends WorldObjectProperties {

	protected int resourceWorldObjctId;
	
	/**
	 * Creates a new ResourceSourceProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param resourceWorldObjctId The id of the world object being produced by this resources source.
	 */
	public ResourceSourceProperties(WorldObjectType type, int id, String name, int graphic, int resourceWorldObjctId) {
		super(type, id, name, graphic);
		
		this.resourceWorldObjctId = resourceWorldObjctId;
	}

	/**
	 * @return the resourceWorldObjctId
	 */
	public int getResourceWorldObjctId() {
		return resourceWorldObjctId;
	}
}
