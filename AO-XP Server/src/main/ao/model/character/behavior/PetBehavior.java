package ao.model.character.behavior;

import ao.model.character.AIType;
import ao.model.character.Character;
import ao.model.character.UserCharacter;
import ao.model.character.attack.AttackStrategy;
import ao.model.character.movement.MovementStrategy;

public class PetBehavior implements Behavior {

	private MovementStrategy movement;
	private Character character;
	private UserCharacter attacker;
	private AttackStrategy attack;
	
	public PetBehavior(Character character, MovementStrategy movement, AttackStrategy attack) {
		this.movement = movement;
		this.character = character;
		this.attack = attack;
	}

	@Override
	public void attackedBy(Character character) {
		// TODO: Don't attack npcs if the attack is magic
		movement.setTarget(character);
	}

	@Override
	public AIType getAIType() {
		return AIType.PET;
	}

	@Override
	public void takeAction() {
		
		if (attacker != null && character.getPosition().inVisionRange(attacker.getPosition())) {
			attack.attack(attacker);
		} else {
			// Follow the pet master.
			movement.setTarget(character);
		}
		
		// Move, move!
		character.moveTo(movement.move(character.getPosition()));
	}

}
