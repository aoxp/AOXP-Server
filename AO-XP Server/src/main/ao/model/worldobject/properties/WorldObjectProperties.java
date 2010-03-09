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
 * Defines a WorldObject's properties. Allows a lightweight pattern implementation.
 */
public class WorldObjectProperties {

	protected int id;
	protected String name;
	protected int graphic;
	protected WorldObjectType type;
	
	/**
	 * Creates a new WorldObjectProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 */
	public WorldObjectProperties(WorldObjectType type, int id, String name, int graphic) {
		this.id = id;
		this.name = name;
		this.graphic = graphic;
		this.type = type;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the graphic
	 */
	public int getGraphic() {
		return graphic;
	}

	/**
	 * @return the type
	 */
	public WorldObjectType getType() {
		return type;
	}
}
