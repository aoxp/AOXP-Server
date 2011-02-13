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

public class PetBehavior implements Behavior {

	private MovementStrategy movement;
	private Character character;
	private AttackStrategy attack;
	private Character target;
	
	public PetBehavior(MovementStrategy movement, AttackStrategy attack, Character character) {
		this.movement = movement;
		this.character = character;
		this.attack = attack;
	}

	@Override
	public void attackedBy(Character character) {
		// TODO: Don't attack npcs if the attack is magic
		if (target != character) {
			movement.setTarget(character);
		}
	}

	@Override
	public void takeAction() {
		
		if (target != null && character.getPosition().inVisionRange(target.getPosition())) {
			attack.attack(target);
		} else {
			// Follow the pet master.
			movement.setTarget(character);
		}
		
		// Move, move!
		character.moveTo(movement.move(character.getPosition()));
	}

}
