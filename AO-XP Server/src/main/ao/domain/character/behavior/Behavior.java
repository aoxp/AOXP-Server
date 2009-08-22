package ao.domain.character.behavior;

import ao.domain.character.AIType;
import ao.domain.character.Character;

public interface Behavior {

	/**
	 * Follows the NPC's target.
	 */
	void followTarget();
	
	/**
	 * Sets the NPC's target.
	 * @param character		The character to be target.
	 */
	void setTarget(Character character);
	
	/**
	 * Retrieves the NPC's target.
	 * @return	The NPC's target.
	 */
	Character getTarget();
	
	/**
	 * Checks if the NPC can attack.
	 * @return True if it can, false otherwise
	 */
	boolean canAttack();
	
	/**
	 * Retrieves the NPC's AI type.
	 * @return The NPC's AI type.
	 */
	AIType getAIType();
	
	/**
	 * Sets the NPCto stay quiet
	 */
	void stayQuiet(); 

}
