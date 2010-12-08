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
 * Defines a Teleport's properties. Allows a lightweight pattern implementation.
 */
public class ForumProperties extends WorldObjectProperties {
	
	protected String forumName;
	
	/**
	 * Creates a new ForumProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param bigGraphic The big graphic for the item.
	 * @param text The text for the item.
	 */
	public ForumProperties(WorldObjectType type, int id, String name, int graphic, String forumName) {
		super(type, id, name, graphic);
		
		this.forumName = forumName;
	}

	/**
	 * @return the forum name.
	 */
	public String getForumName() {
		return forumName;
	}
}
