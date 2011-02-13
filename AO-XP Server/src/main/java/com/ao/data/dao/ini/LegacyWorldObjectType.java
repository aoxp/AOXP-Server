package com.ao.data.dao.ini;

import com.ao.model.worldobject.WorldObjectType;

/**
 * World Object Type enumeration, as it was known in the old days of Visual Basic.
 */
public enum LegacyWorldObjectType {
	// Beware, some objects are not mapped because they have no direct mapping, and we need extra info to do that
	USE_ONCE(1, WorldObjectType.FOOD),
	WEAPON(2, null),
	ARMOR(3, WorldObjectType.ARMOR),
	TREE(4, WorldObjectType.TREE),
	MONEY(5, WorldObjectType.MONEY),
	DOOR(6, WorldObjectType.DOOR),
	CONTAINER(7, null),
	SIGN(8, WorldObjectType.SIGN),
	KEY(9, WorldObjectType.KEY),
	FORUM(10, WorldObjectType.FORUM),
	POTION(11, null),
	BOOK(12, null),
	DRINK(13, WorldObjectType.DRINK),
	WOOD(14, WorldObjectType.WOOD),
	BONFIRE(15, null),
	SHIELD(16, WorldObjectType.SHIELD),
	HELMET(17, WorldObjectType.HELMET),
	RING(18, WorldObjectType.ACCESSORY),
	TELEPORT(19, WorldObjectType.TELEPORT),
	FURNITURE(20, null),
	JEWELRY(21, null),
	MINE(22, WorldObjectType.MINE),
	MINERAL(23, WorldObjectType.MINERAL),
	PARCHMENT(24, WorldObjectType.PARCHMENT),
	MUSICAL_INSTRUMENT(26, WorldObjectType.MUSICAL_INSTRUMENT),
	ANVIL(27, WorldObjectType.ANVIL),
	FORGE(28, WorldObjectType.FORGE),
	GEMS(29, null),
	FLOWERS(30, null),
	BOAT(31, WorldObjectType.BOAT),
	ARROW(32, WorldObjectType.AMMUNITION),
	EMPTY_BOTTLE(33, WorldObjectType.EMPTY_BOTTLE),
	FILLED_BOTTLE(34, WorldObjectType.FILLED_BOTTLE),
	STAIN(35, null),
	ELVEN_TREE(36, WorldObjectType.TREE),
	BACKPACK(37, WorldObjectType.BACKPACK);
	
	protected int value;
	protected WorldObjectType currentType;
	
	/**
	 * Creates a new LegacyWorldObjectType.
	 * @param value The value corresponding to the object type. Should be unique.
	 */
	LegacyWorldObjectType(int value, WorldObjectType currentType) {
		this.value = value;
		this.currentType = currentType;
	}
	
	/**
	 * Retrieves the LegacyWorldObjectType associated with the given value.
	 * @param value The value for which to search for a LegacyWorldObjectType.
	 * @return The matched LegacyWorldObjectType, if any.
	 */
	public static LegacyWorldObjectType valueOf(int value) {
		for (LegacyWorldObjectType type : LegacyWorldObjectType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		
		return null;
	}
	
	/**
	 * Retrieves the current world obejt type.
	 * @return The current world object type.
	 */
	public WorldObjectType getCurrentType() {
		return currentType;
	}
}