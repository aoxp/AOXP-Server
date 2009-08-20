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
	
	/**
	 * Retrieves the manufacture difficulty of the item (how hard it's to make).
	 * @return The manufacture difficulty of the item (how hard it's to make).
	 */
	int getManufactureDifficulty(); // TODO : Nota, esto reemplaza a minSkill para carpinteria, fundicion y herreria
	
	/**
	 * Retrieves the usage difficulty of the item.
	 * @return The usage difficulty of the item.
	 */
	int getUsageDifficulty(); // TODO : Nota, esto reemplaza minSkill para navegar, con lo que los separamos.
}
