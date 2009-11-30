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

package ao.model.character.movement;

import ao.model.character.Character;
import ao.model.map.Heading;
import ao.model.map.Position;

/**
 * Dummy movement strategy, just stay quiet.
 */
public class QuietMovementStrategy implements MovementStrategy {

	@Override
	public Heading move(Position pos) {
		// No, I will not.
		
		return null;
	}

	@Override
	public void setTarget(Character target) {
		// I don't care the target, I will never move!
	}

	@Override
	public void setTarget(Position pos) {
		// I don't care the target, I will never move!
	}

}
