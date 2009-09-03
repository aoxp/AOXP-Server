package ao.model.worldobject;

public interface Weapon extends EquipableItem {

	/**
	 * Retrieves the weapon's total attack power.
	 * @return The attack power of the weapon.
	 */
	int getAttackPower();
}
