package ao.model.worldobject;

public interface EquipableItem extends Item {

	/**
	 * Checks if the item is equipped or not.
	 * @return True if the item is equipped, false otherwise.
	 */
	boolean isEquipped();
	
	/**
	 * Sets the item as equipped or not.
	 * @param equipped True if the item is equipped, false otherwise.
	 */
	void setEquipped(boolean equipped);
	
	/**
	 * Retrieves the object's graphic when equipped (may be a body index, head index, etc. according to the specific item). 
	 * @return The object's graphic when equipped (may be a body index, head index, etc. according to the specific item).
	 */
	int getEquippedGraphic();
}
