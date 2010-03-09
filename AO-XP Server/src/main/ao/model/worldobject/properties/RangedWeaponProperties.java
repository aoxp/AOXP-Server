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

package ao.model.worldobject.properties;

import java.util.List;

import ao.model.character.Race;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.WorldObjectType;

/**
 * Defines a Ranged Weapon's properties. Allows a lightweight pattern implementation.
 */
public class RangedWeaponProperties extends WeaponProperties {

	protected boolean needsAmmunition;
	
	/**
	 * Creates a new RangedWeaponProperties instance.
	 * @param type The type of the item.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param graphic The graphic for the item.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param value The item's value.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param forbiddenRaces List of Races not allowed to use this item.
	 * @param newbie Whether the item is newbie or not.
	 * @param stabs Wether if this item stabs or not.
	 * @param piercingDamage The pircing damage (not reduced by any kind of armor or defense) done by this item.
	 * @param minHit The minimum hit done by this item.
	 * @param maxHit The maximum hit done by this item.
	 * @param needsAmmunition True if the weapon needs ammunition, false of it's a throwable weapon.
	 */
	public RangedWeaponProperties(WorldObjectType type, int id, String name, int graphic,
			boolean tradeable, int value,
			int manufactureDifficulty, List<UserArchetype> forbiddenArchetypes, List<Race> forbiddenRaces,
			boolean newbie, int equippedGraphic, boolean stabs,
			int piercingDamage, int minHit, int maxHit, boolean needsAmmunition) {
		super(type, id, name, graphic, tradeable, value,
				manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, equippedGraphic,
				stabs, piercingDamage, minHit, maxHit);
		
		this.needsAmmunition = needsAmmunition;
	}

	/**
	 * @return the needsAmmunition
	 */
	public boolean getNeedsAmmunition() {
		return needsAmmunition;
	}
}
