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

import com.ao.model.map.Position;
import com.ao.model.map.WorldMap;
import com.ao.model.character.Character;

public interface MapService {

	/**
	 * Loads all maps.
	 */
	void loadMaps();
	
	/**
	 * Retrieves the map with the given id.
	 * @param id The map's id.
	 * @return The loaded map.
	 */
	WorldMap getMap(int id);

	/**
	 * Puts a character at the given position
	 * @param chara The character to be put.
	 * @param pos The position where to put the character.
	 */
	void putCharacterAtPos(Character chara, Position pos);
}
