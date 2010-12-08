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

import com.ao.model.character.AIType;
import com.ao.model.character.Character;
import com.ao.model.character.NPCCharacter;
import com.ao.model.character.UserCharacter;
import com.ao.model.character.attack.AttackStrategy;
import com.ao.model.character.movement.MovementStrategy;

public class HostileBehavior implements Behavior {
	
	private MovementStrategy movement;
	private AttackStrategy attack;
	
	private Character character;
	private Character target;
	
	public HostileBehavior(MovementStrategy movement, AttackStrategy attack, Character character) {
		this.movement = movement;
		this.attack = attack;
		this.character = character;
	}
	
	@Override
	public void attackedBy(Character character) {
		// Don't care, anyway this behavior will attack everyone in his range.
	}

	@Override
	public AIType getAIType() {
		return AIType.FOLLOW_CHAR;
	}

	@Override
	public void takeAction() {
		for (Character chara : character.getPosition().getCharactersNearby()) {
			if (chara != character && (chara instanceof UserCharacter || ((NPCCharacter) chara).getMaster() != null)) {
				
				// Is the same last target?
				if (target != chara) {
					movement.setTarget(chara);
				}
					
				attack.attack(chara);
				target = chara;
				
				break;
			}
		}
		
		// Move, move!
		character.moveTo(movement.move(character.getPosition()));
	}

}
