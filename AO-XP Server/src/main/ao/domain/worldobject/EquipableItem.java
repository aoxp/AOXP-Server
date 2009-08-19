package ao.domain.worldobject;

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
}
