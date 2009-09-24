package ao.model.character.behavior;

import ao.model.character.AIType;
import ao.model.character.Character;
import ao.model.character.attack.AttackStrategy;
import ao.model.character.movement.MovementStrategy;

public class PetBehavior implements Behavior {

	private MovementStrategy movement;
	private Character character;
	private AttackStrategy attack;
	private Character target;
	
	public PetBehavior(Character character, MovementStrategy movement, AttackStrategy attack) {
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
	public AIType getAIType() {
		return AIType.PET;
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
