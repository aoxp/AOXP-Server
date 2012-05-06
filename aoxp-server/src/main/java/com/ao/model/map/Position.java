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

import java.util.List;
import com.ao.model.character.Character;

public class Position {

	/**
	 * The position in the X axis.
	 */
	private byte x;
	
	/**
	 * The position in the Y axis.
	 */
	private byte y;
	
	/**
	 * The position's map.
	 */
	private WorldMap map;
	
	/**
	 * Creates a new position with the given data
	 * @param x	The position in the X axis.
	 * @param y	The position in the Y axis.
	 * @param map The position's map.
	 */
	public Position(byte x, byte y, WorldMap map) {
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	/**
	 * Retrieves the position in the X axis.
	 * @return The position in the X axis.
	 */
	public byte getX() {
		return x;
	}
	
	/**
	 * Sets the position in the X axis.
	 * @param x	The new position in the X axis.
	 */
	public void setX(byte x) {
		this.x = x;
	}
	
	/**
	 * Retrieves the position in the Y axis.
	 * @return	The position in the Y axis.
	 */
	public byte getY() {
		return y;
	}
	
	/**
	 * Sets the position in the Y axis.
	 * @param y	The new position in the Y axis.
	 */
	public void setY(byte y) {
		this.y = y;
	}
	
	/**
	 * Retrieves the position's map.
	 * @return The position's map.
	 */
	public WorldMap getMap() {
		return map;
	}
	
	/**
	 * Sets the position's map.
	 * @param map The new position's map. 
	 */
	public void setMap(WorldMap map) {
		this.map = map;
	}
	
	/**
	 * Adds (or substract if the given number is negative) positions to the X axis.
	 * @param positions The positions to add.
	 */
	public void addToX(int positions) {
		// TODO: Chequear que el número no se vaya fuera de los rangos?
		x += positions;
	}
	
	/**
	 * Adds (or substract if the given number is negative) positions to the Y axis.
	 * @param positions The positions to add.
	 */
	public void addToY(int positions) {
		// TODO: Chequear que el número no se vaya fuera de los rangos?
		y += positions;
	}
	
	/**
	 * Calculates the Manhattan distance to the given Position.
	 * @param pos The other position to calculate the distance.
	 * @return The distance to the other position.
	 */
	public int getDistance(Position pos) {
		return Math.abs(x - pos.x) + Math.abs(y - pos.y);
	}

	/**
	 * Checks if the given position is in our vision range.
	 * @param pos The position to check.
	 * @return True if the given position is in the vision range, false otherwise.
	 */
	public boolean inVisionRange(Position pos) {
		
		if (map != pos.map ||
			Math.abs(x - pos.x) > WorldMap.VISIBLE_AREA_WIDTH ||
			Math.abs(y - pos.y) > WorldMap.VISIBLE_AREA_HEIGHT) {
				return false;
		}
		
		return true;
	}

	/**
	 * Looks for characters in the vision range.
	 * @return A list of the characters found.
	 */
	public List<Character> getCharactersNearby() {
		return map.getCharactersNearby(x, y);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
        if (map == null) {
            if (other.map != null)
                return false;
        } else if (!map.equals(other.map))
            return false;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
	}

}