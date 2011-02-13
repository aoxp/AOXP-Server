package com.ao.data.dao.ini;

import java.util.HashMap;
import java.util.Map;

import com.ao.model.worldobject.WorldObjectType;

/**
 * World Object Type enumeration, as it was known in the old days of Visual Basic.
 */
public enum LegacyWorldObjectType {
	USE_ONCE(1),
	WEAPON(2),
	ARMOR(3),
	TREE(4),
	MONEY(5),
	DOOR(6),
	CONTAINER(7),
	SIGN(8),
	KEY(9),
	FORUM(10),
	POTION(11),
	BOOK(12),
	DRINK(13),
	WOOD(14),
	BONFIRE(15),
	SHIELD(16),
	HELMET(17),
	RING(18),
	TELEPORT(19),
	FURNITURE(20),
	JEWELRY(21),
	MINE(22),
	MINERAL(23),
	PARCHMENT(24),
	MUSICAL_INSTRUMENT(26),
	ANVIL(27),
	FORGE(28),
	GEMS(29),
	FLOWERS(30),
	BOAT(31),
	ARROW(32),
	EMPTY_BOTTLE(33),
	FILLED_BOTTLE(34),
	STAIN(35),
	ELVEN_TREE(36),
	BACKPACK(37);
	
	protected static final Map<LegacyWorldObjectType, WorldObjectType> worldObjectTypeMapper;	
	protected int value;
	
	static {
		// Populate mappings from old object types to new ones.
		worldObjectTypeMapper = new HashMap<LegacyWorldObjectType, WorldObjectType>();
		
		// BEWARE : Some objects have no mapping since they need extra info to be mapped (potions and weapons for instance).
		worldObjectTypeMapper.put(LegacyWorldObjectType.ARMOR, WorldObjectType.ARMOR);
		worldObjectTypeMapper.put(LegacyWorldObjectType.ARROW, WorldObjectType.AMMUNITION);
		worldObjectTypeMapper.put(LegacyWorldObjectType.BOAT, WorldObjectType.BOAT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.DRINK, WorldObjectType.DRINK);
		worldObjectTypeMapper.put(LegacyWorldObjectType.EMPTY_BOTTLE, WorldObjectType.EMPTY_BOTTLE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.FILLED_BOTTLE, WorldObjectType.FILLED_BOTTLE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.HELMET, WorldObjectType.HELMET);
		worldObjectTypeMapper.put(LegacyWorldObjectType.MINERAL, WorldObjectType.MINERAL);
		worldObjectTypeMapper.put(LegacyWorldObjectType.MUSICAL_INSTRUMENT, WorldObjectType.MUSICAL_INSTRUMENT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.RING, WorldObjectType.ACCESSORY);
		worldObjectTypeMapper.put(LegacyWorldObjectType.SHIELD, WorldObjectType.SHIELD);
		worldObjectTypeMapper.put(LegacyWorldObjectType.TELEPORT, WorldObjectType.TELEPORT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.USE_ONCE, WorldObjectType.FOOD);
		worldObjectTypeMapper.put(LegacyWorldObjectType.PARCHMENT, WorldObjectType.PARCHMENT);
		worldObjectTypeMapper.put(LegacyWorldObjectType.MONEY, WorldObjectType.MONEY);
		worldObjectTypeMapper.put(LegacyWorldObjectType.TREE, WorldObjectType.TREE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.ELVEN_TREE, WorldObjectType.TREE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.WOOD, WorldObjectType.WOOD);
		worldObjectTypeMapper.put(LegacyWorldObjectType.MINE, WorldObjectType.MINE);
		worldObjectTypeMapper.put(LegacyWorldObjectType.KEY, WorldObjectType.KEY);
		worldObjectTypeMapper.put(LegacyWorldObjectType.DOOR, WorldObjectType.DOOR);
		worldObjectTypeMapper.put(LegacyWorldObjectType.SIGN, WorldObjectType.SIGN);
		worldObjectTypeMapper.put(LegacyWorldObjectType.FORUM, WorldObjectType.FORUM);
		worldObjectTypeMapper.put(LegacyWorldObjectType.BACKPACK, WorldObjectType.BACKPACK);
		worldObjectTypeMapper.put(LegacyWorldObjectType.ANVIL, WorldObjectType.ANVIL);
		worldObjectTypeMapper.put(LegacyWorldObjectType.FORGE, WorldObjectType.FORGE);
	}
	
	
	/**
	 * Creates a new LegacyWorldObjectType.
	 * @param value The value corresponding to the object type. Should be unique.
	 */
	LegacyWorldObjectType(int value) {
		this.value = value;
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
}