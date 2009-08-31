package ao.config;

import ao.domain.character.archetype.Archetype;

public interface ArchetypeConfiguration {

	/**
	 * Retrieves the given archetype's evasion modifier
	 * @param archetype The archetype's class.
	 * @return The archetype's evasion modifier.
	 */
	float getEvasionModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's melee accuracy modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's melee accuracy.
	 */
	float getMeleeAccuracyModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's ranged accuracy modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's ranged accuracy modifier.
	 */
	float getRangedAccuracyModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's melee damage modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's melee damage modifier.
	 */
	float getMeleeDamageModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's ranged damage modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's ranged damage modifier.
	 */
	float getRangedDamageModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's wrestling damage modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's wrestling damage modifier.
	 */
	float getWrestlingDamageModifier(Class <?extends Archetype> archetype);
	
	/**
	 * Retrieves the given archetype's block power modifier.
	 * @param archetype The archetype's class.
	 * @return The archetype's block power modifier.
	 */
	float getBlockPowerModifier(Class <?extends Archetype> archetype);
	
}
