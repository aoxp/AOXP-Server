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

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import com.ao.model.character.Character;

/**
 * A game's map.
 */
public class WorldMap {

	public static final int MAP_WIDTH = 100;
	public static final int MAP_HEIGHT = 100;
	public static final int VISIBLE_AREA_WIDTH = 8;
	public static final int VISIBLE_AREA_HEIGHT = 6;
	public static final int MAX_DISTANCE = 12;

	public static final int MAX_X = 99;
	public static final int MAX_Y = 99;
	public static final int MIN_X = 0;
	public static final int MIN_Y = 0;

	protected String name;
	protected int id;
	protected short version;

	// We don't use jagged arrays, they are inefficient in Java!
	protected Tile[] tiles;

	/**
	 * Creates a new Map.
	 * @param name The name of the map.
	 * @param id The unique id of the map.
	 * @param version The map's version.
	 * @param tiles The array of tiles composing the map.
	 */
	public WorldMap(String name, int id, short version, Tile[] tiles) {
		super();
		this.name = name;
		this.id = id;
		this.version = version;
		this.tiles = tiles;
	}

	/**
	 * Creates a new Map.
	 * @param id The unique id of the map.
	 */
	public WorldMap(int id) {
		super();
		this.id = id;
	}

	/**
	 * Sets the tiles of the map
	 * @param tiles The array of tiles composing the map
	 */
	public void setTiles(Tile[] tiles) {
		this.tiles = tiles;
	}

	/**
	 * Sets the name of the map
	 * @param name The name of the map
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the map's version.
	 * @param version The map's version
	 */
	public void setVersion(short version) {
		this.version = version;
	}

	/**
	 * Retrieves the map's version.
	 * @return The map's version.
	 */
	public short getVersion() {
		return version;
	}

	/**
	 * Retrieves the tile at the given coordinates.
	 * @param x The coordinate along the x vertex (zero is at the left).
	 * @param y The coordinate along the y vertex (zero is at the top).
	 * @return The tile at the given coordinates.
	 */
	public Tile getTile(int x, int y) {
		return tiles[WorldMap.getTileKey(x, y)];
	}

	/**
	 * Retrieves the tile index at the given coordinates.
	 * @param x The coordinate along the x vertex (zero is at the left).
	 * @param y The coordinate along the y vertex (zero is at the top).
	 * @return The tile index at the given coordinates.
	 */
	public static int getTileKey(int x, int y) {
		return y * MAP_WIDTH + x;
	}

	/**
	 * Retrieves the map's name.
	 * @return The map's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the map's unique id.
	 * @return The map's unique id.
	 */
	public int getId() {
		return id;
	}

	/**
	 * Retrieves a list with the characters in the given position vision range.
	 * @param x The coordinate along the x vertex (zero is at the left).
	 * @param y The coordinate along the y vertex (zero is at the top).
	 * @return A list of the characters found without the character in the given position if there is any.
	 */
	public List<Character> getCharactersNearby(int x, int y) {
		List<Character> charList = new LinkedList<Character>();
		Character character;

		int yy;
		int toX = x + WorldMap.VISIBLE_AREA_WIDTH * 2;
		int toY = y + WorldMap.VISIBLE_AREA_HEIGHT * 2;
		int fromY = y - WorldMap.VISIBLE_AREA_HEIGHT;

		for (int xx = x - WorldMap.VISIBLE_AREA_WIDTH; xx < toX; xx++) {
			if (xx > MAX_X || xx < MIN_X) {
				continue;
			}

			for (yy = fromY; yy < toY; yy++) {
				if (yy > MAX_Y || yy < MIN_Y) {
					continue;
				}
				character = getTile(xx, yy).getCharacter();

				if (character != null && (xx != x && yy != y)) {
					charList.add(character);
				}

			}
		}

		return charList;
	}

	/**
	 * Puts the given character at the given position.
	 * If the tile is unavailable this will look around for an available one.
	 * @param chara The character to put.
	 * @param pos The position where to put it.
	 */
	public void putCharacterAtPos(Character chara, byte x, byte y) {
		synchronized (tiles) {
			Tile tile = getTile(x, y);

			if (tile.getCharacter() != null) {
				tile = getEmptyTileAround(x, y, chara.canWalkInWater(), !chara.canWalkInWater());
			}

			tile.setCharacter(chara);
		}
	}

	/**
	 * Searchs for an empty tile around the given one and retrieves it.
	 * If there is no empty tile around the given one this will continue
	 * searching in the next "row" of tiles, starting at top left.
	 * This method should be called from a synchronized block.
	 * @param lookAroundX The tile's X value.
	 * @param lookAroundY The tile's Y value.
	 * @param canBeWater Whether the found tile can be water, or not.
	 * @param canBeLand Whether the found tile can be land, or not.
	 * @return The empty tile.
	 */
	private Tile getEmptyTileAround(byte lookAroundX, byte lookAroundY, boolean canBeWater, boolean canBeLand) {
		byte distance = 1;
		Tile t;

		while (distance < MAX_DISTANCE) {
			for (int x = Math.max(lookAroundX - distance, 1); x < lookAroundX + distance && x <= MAP_HEIGHT; x++) {
				for (int y = Math.max(lookAroundY - distance, 1); y < lookAroundY + distance && y <= MAP_WIDTH; y++) {

					// Don't look on the tile we're supposed to look around or on tiles already checked.
					if (Math.abs(x - lookAroundX) != distance && Math.abs(y - lookAroundY) != distance) {
						continue;
					}

					t = getTile(x, y);

					if (t.getCharacter() == null && !t.isBlocked() && t.getTileExit() == null
							&& ((t.isWater() && canBeWater) || (!t.isWater() && canBeLand))) {
						return t; // Found it!
					}
				}
			}

			distance++;
		}

		return null;
	}

	@Override
 	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		WorldMap other = (WorldMap) obj;
		if (id != other.id) {
			return false;
		}
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		if (!Arrays.equals(tiles, other.tiles)) {
			return false;
		}
		return true;
	}

	public Tile[] dame()
	{
		return tiles;
	}
}
