/*
    AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
    Copyright (C) 2009 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package ao.model.worldobject;


/**
 * World Object Type enumeration.
 */
public enum WorldObjectType {
	FOOD(1),
	WEAPON(2),
	ARMOR(3),
	TREE(4),
	MONEY(5),
	DOOR(6),
	CONTAINER(7),	// TODO : WHAT IS THIS??
	SIGN(8),
	KEY(9),
	FORUM(10),
	POTION(11),
	DRINK(13),
	WOOD(14),
	BONFIRE(15),
	SHIELD(16),
	HELMET(17),
	ACCESSORY(18),
	TELEPORT(19),
	MINE(22),
	MINERAL(23),
	PARCHMENT(24),
	MUSICAL_INSTRUMENT(26),
	ANVIL(27),
	FORGE(28),
	BOAT(31),
	AMMUNITION(32),
	EMPTY_BOTTLE(33),
	FILLED_BOTTLE(34),
	ELVEN_TREE(36),
	BACKPACK(37);
	
	protected int value;
	
	/**
	 * Creates a new WorldObjectType.
	 * @param value The value corresponding to the object type. Should be unique.
	 */
	private WorldObjectType(int value) {
		this.value = value;
	}
	
	/**
	 * Retrieves the WorldObjectType associated with the given value.
	 * @param value The value for which to search for a WorldObjectType.
	 * @return The matched WorldObjectType, if any.
	 */
	public static WorldObjectType valueOf(int value) {
		for (WorldObjectType type : WorldObjectType.values()) {
			if (type.value == value) {
				return type;
			}
		}
		
		return null;
	}
}
