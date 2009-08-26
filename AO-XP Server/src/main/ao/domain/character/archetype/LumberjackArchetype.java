package ao.domain.character.archetype;

/**
 * A lumberjack archetype.
 * @author Zama
 */
public class LumberjackArchetype extends DefaultArchetype {

	private static final int HIT_INCREMENT = 2;
	private static final int STAMINA_INCREMENT = 35;
	private static final int LUMBER_MIN_AMOUNT = 1;
	private static final int LUMBER_MAX_AMOUNT = 4;
	private static final int LUMBER_STAMINA_COST = 2;

	/**
	 * Retrieves the hit increment.
	 * @param level The number of levels raised.
	 * @return The hit increment.
	 */
	public int getHitIncrement(int level) {
		return HIT_INCREMENT;
	}

	/**
	 * Retrieves the stamina increment for the archetype.
	 * @param level The number of levels raised.
	 * @return The stamina increment for the archetype.
	 */
	public int getStaminaIncrement(int level) {
		return STAMINA_INCREMENT;
	}
		
	/**
	 * Retrieves the maximum amount that can be Lumbered.
	 * @return The maximum amount that can be Lumbered.
	 */
	public int getLumberedMaxAmount() {
		return LUMBER_MAX_AMOUNT;
	}

	/**
	 * Retrieves the minimum amount that can be Lumbered.
	 * @return The minimum amount that can be Lumbered.
	 */
	public int getLumberedMinAmount() {
		return LUMBER_MIN_AMOUNT;
	}
	
	/**
	 * Retrieves the needed amount of stamina to Lumber.
	 * @return The needed amount of stamina to Lumber.
	 */
	public int getLumberjackingStaminaCost(){
		return LUMBER_STAMINA_COST;
	}
	
}
