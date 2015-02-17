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

package com.ao.model.map;

import com.ao.model.character.Character;
import com.ao.model.worldobject.WorldObject;

/**
 * A map tile.
 */
public class Tile {
	private Character character;
	private WorldObject worldObject;
	private Trigger trigger;
	private boolean blocked;
	private boolean isWater;
	private boolean isLava;
	private Position tileExit;

	/**
	 * Creates a new tile.
	 * @param blocked Whether the tile is blocked or not.
	 * @param isWater Whether the tile is water or not.
	 * @param isLava Whether the tile is lava or not.
	 * @param trigger The trigger ruling over this tile.
	 * @param tileExit Position to which this tile leads, if any.
	 * @param character The character currently standing on this position, if any.
	 * @param worldObject The object laying in this position, if any.
	 */
	public Tile(final boolean blocked, final boolean isWater, final boolean isLava,
			final Trigger trigger, final Position tileExit, final Character character,
			final WorldObject worldObject) {
		this.blocked = blocked;
		this.isWater = isWater;
		this.isLava = isLava;
		this.trigger = trigger;
		this.tileExit = tileExit;
		this.worldObject = worldObject;
		this.character = character;
	}

	/**
	 * Retrieves the position to which this tile leads.
	 * @return The tile exit.
	 */
	public Position getTileExit() {
		return tileExit;
	}

	/**
	 * Retrieves the block status.
	 * @return True if the tile is blocked, false otherwise.
	 */
	public boolean isBlocked() {
		return blocked;
	}

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
	/*package*/ void setCharacter(final Character character) {
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
	 * @param worldObject The worldObject to set at the tile.
	 */
	/*package*/ void setWorldObject(final WorldObject worldObject) {
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
	 * Checks if the tile is water.
	 * @return True if this tile is water, false otherwise.
	 */
	public boolean isWater() {
		return isWater;
	}

	/**
	 * Checks if the tile is lava.
	 * @return True if this tile is lava, false otherwise.
	 */
	public boolean isLava() {
		return isLava;
	}

	/**
	 * Checks if the tile is under roof.
	 * @return True if this tile is under roof, false otherwise.
	 */
	public boolean isUnderRoof() {
		return trigger == Trigger.UNDER_ROOF;
	}

	/**
	 * Checks if the tile is a safe zone.
	 * @return True if this tile is safe zone, false otherwise.
	 */
	public boolean isSafeZone() {
		return trigger == Trigger.SAFE_ZONE;
	}

	@Override
	public String toString() {
		return "Tile [character=" + character + ", worldObject=" + worldObject
				+ ", trigger=" + trigger + ", blocked=" + blocked
				+ ", isWater=" + isWater + ", isLava=" + isLava + ", tileExit="
				+ tileExit + "]";
	}

}
