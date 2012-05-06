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
package com.ao.model.character.behavior;

import com.ao.model.character.Character;
import com.ao.model.character.attack.AttackStrategy;
import com.ao.model.character.movement.MovementStrategy;

/**
 * Behavior that just does nothing.
 * @author jsotuyod
 */
public class NullBehavior implements Behavior {

	/**
	 * Create a new null behavior.
	 * @param movement The movement behavior to be used.
	 * @param attack The attack strategy to be used.
	 * @param character The character on which the behavior is applied.
	 */
	public NullBehavior(MovementStrategy movement, AttackStrategy attack, Character character) {
	}
	
	@Override
	public void attackedBy(Character character) {
		// We don't care
	}

	@Override
	public void takeAction() {
		// Do nothing
	}
}
