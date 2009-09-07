package ao.model.character.behavior;

import ao.model.character.AIType;
import ao.model.character.Character;

/**
 * Defines a character's behavior (AI)
 */
public interface Behavior {

	/**
	 * Tells the behavior that the character was attacked by another.
	 * @param character The character that attacked.
	 */
	void attackedBy(Character character);
	
	/**
	 * Retrieves the NPC's AI type.
	 * @return The NPC's AI type.
	 */
	AIType getAIType();
	
	/**
	 * Performs an action according to the behavior.
	 */
	void takeAction();
	
	/**
	 * Sets the listener for behavior actions.
	 * @param listener The listener of behavior actions
	 */
	void setActionListener(BehaviorActionListener listener);
	
}
