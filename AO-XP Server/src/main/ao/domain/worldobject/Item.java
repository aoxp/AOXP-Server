package ao.domain.worldobject;

public interface Item extends WorldObject {

	/**
	 * Retrieves the item's amount.
	 * @return The amount of items put together.
	 */
	int getAmount();
	
	/**
	 * Attempts to use the item.
	 */
	void use();
}
