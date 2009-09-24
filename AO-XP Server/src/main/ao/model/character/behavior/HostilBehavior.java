package ao.model.character.behavior;

import ao.model.character.AIType;
import ao.model.character.Character;
import ao.model.character.NPCCharacter;
import ao.model.character.UserCharacter;
import ao.model.character.attack.AttackStrategy;
import ao.model.character.movement.MovementStrategy;

public class HostilBehavior implements Behavior {
	
	private MovementStrategy movement;
	private AttackStrategy attack;
	
	private Character character;
	private Character target;
	
	public HostilBehavior(MovementStrategy movement, AttackStrategy attack, Character character) {
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
