package ao.domain.character;

import ao.domain.character.UserCharacter;

public interface NPCCharacter {
	/**
	 * Retrieves the NPC's index.
	 * @return The NPC's index.
	 */
	int getIndex();
	
	/**
	 * Retrieves the NPC's name.
	 * @return The NPC's index. 
	 */
	String getName();
	
	/**
	 * Retrieves the NPC's experience.
	 * @return The NPC's experience.
	 */
	long getExperience();
	
	/**
	 * Retrieves the NPC's gold.
	 * @return The NPC's gold.
	 */
	long getGold();
	
	/**
	 * Retrieves the NPC's description.
	 * @return The NPC's description.
	 */
	String getDescription();
	
	/**
	 * Retrieves the NPC's Attack Power.
	 * @return The NPC's Attack Power.
	 */
	int getAttackPower();
	
	/**
	 * Retrieves the NPC's Evasion Power.
	 * @return The NPC's Evasion Power.
	 */
	int getEvasionPower();
	
	/**
	 * Retrieves the NPC's body.
	 * @return The NPC's body.
	 */
	int getBody();
	
	/**
	 * Retrieves the NPC's master if has one.
	 * @return The NPC's master if has one.
	 */
	UserCharacter getMaster();
	
	/**
	 * Retrieves the NPC's Type.
	 * @return The NPC's Type.
	 */
	NPCType getNPCType();
	
	/**
	 * Checks if the NPC is domable or not.
	 * @return True if the NPC is domable, false otherwise.
	 */
	boolean isDomable();
	
	/**
	 * Checks if the NPC is commerciable or not.
	 * @return True if the NPC is comMerciable, false otherwise.
	 */
	boolean isCommerciable();
	
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
	
	/**
	 * Checks if the NPC can walk in the water.  
	 * @return True if the NPC can walk in water or if he is Jesus, false otherwise.
	 */
	boolean canWalkInWater();
	
	/**
	 * Checks if the NPC has some spell.
	 * @return True if the NPC has at least one spell.
	 */
	boolean hasSpells();

	
}