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

package com.ao.model.character.movement;

import com.ao.model.character.Character;
import com.ao.model.map.Heading;
import com.ao.model.map.Position;

public interface MovementStrategy {

	/**
	 * Applies the movement strategy
	 * @param pos The current position.
	 * 
	 * @return null if there is no need to move.
	 */
	Heading move(Position pos);
	
	/**
	 * Targets a new character
	 * @param target	The character to target
	 */
	void setTarget(Character target);
	
	/**
	 * Targets a position
	 * @param pos	The final destination position 
	 */
	void setTarget(Position pos);
}
