package ao.model.character;


public interface NPCCharacter extends Character {
	/**
	 * Retrieves the NPC's id.
	 * @return The NPC's id.
	 */
	int getId();
	
	/**
	 * Retrieves the NPC's experience.
	 * @return The NPC's experience.
	 */
	int getExperience();
	
	/**
	 * Retrieves the NPC's gold.
	 * @return The NPC's gold.
	 */
	int getGold();
	
	/**
	 * Retrieves the NPC's master if it has one.
	 * @return The NPC's master if it has one.
	 */
	Character getMaster();
	
	/**
	 * Retrieves the NPC's Type.
	 * @return The NPC's Type.
	 */
	NPCType getNPCType();
	
	/**
	 * Checks if the NPC is tameable or not.
	 * @return True if the NPC is tameable, false otherwise.
	 */
	boolean isTameable();
	
	/**
	 * Checks if the NPC can trade or not.
	 * @return True if the NPC can trade, false otherwise.
	 */
	boolean canTrade();
	
	/**
	 * Checks if the NPC is hostile or not.
	 * @return True if the NPC is hostile, false otherwise.
	 */
	boolean isHostile();
	
	/**
	 * Checks if the NPC has a master.
	 * @return True if has a master, false otherwise.
	 */
	boolean hasMaster();
	
}