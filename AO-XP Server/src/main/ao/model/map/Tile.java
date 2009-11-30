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

package ao.model.map;

import ao.model.character.Character;
import ao.model.worldobject.WorldObject;

/**
 * A map's tile.
 */
public class Tile {

	protected Character character;
	protected WorldObject worldObject;
	protected Trigger trigger;
	
	/**
	 * Retrieves the character at the tile.
	 * @return The character at the tile.
	 */
	public Character getCharacter() {
		return character;
	}
	
	/**
	 * Sets the character at the tile.
	 * @param character The character to be set at the tile.
	 */
	public void setCharacter(Character character) {
		this.character = character;
	}
	
	/**
	 * Retrieves the world object at the tile.
	 * @return The world object at the tile.
	 */
	public WorldObject getWorldObject() {
		return worldObject;
	}
	
	/**
	 * Sets the world object at the tile.
	 * @param worldObject The world object to set at the tile.
	 */
	public void setWorldObject(WorldObject worldObject) {
		this.worldObject = worldObject;
	}
	
	/**
	 * Retrieves the trigger at the tile.
	 * @return The trigger at the tile.
	 */
	public Trigger getTrigger() {
		return trigger;
	}
	
	/**
	 * Sets the trigger at the tile.
	 * @param trigger The trigger to be set at the tile.
	 */
	public void setTrigger(Trigger trigger) {
		this.trigger = trigger;
	}
}
