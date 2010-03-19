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
 * Defines a Weapon's properties. Allows a lightweight pattern implementation.
 */
public class WeaponProperties extends EquippableItemProperties {

	protected boolean stabs;
	protected int piercingDamage;	// This replaces the old "Refuerzo"

	protected int minHit;
	protected int maxHit;
	
	//  TODO : add poison modifier!
	
	/**
	 * Creates a new WeaponProperties instance.
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
	 */
	public WeaponProperties(WorldObjectType type, int id, String name, int graphic,
			boolean tradeable, int value,
			int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, List<Race> forbiddenRaces, boolean newbie,
			boolean noLog, boolean falls, boolean respawnable, 
			int equippedGraphic, boolean stabs, int piercingDamage, int minHit, int maxHit) {
		super(type, id, name, graphic, tradeable, value, manufactureDifficulty, forbiddenArchetypes, forbiddenRaces, newbie, noLog, falls, respawnable, equippedGraphic);

		this.stabs = stabs;
		this.piercingDamage = piercingDamage;
		
		this.minHit = minHit;
		this.maxHit = maxHit;
	}

	/**
	 * @return the true if this item stabs, false otherwise
	 */
	public boolean getStabs() {
		return stabs;
	}

	/**
	 * @return the piercingDamage
	 */
	public int getPiercingDamage() {
		return piercingDamage;
	}

	/**
	 * @return the minHit
	 */
	public int getMinHit() {
		return minHit;
	}

	/**
	 * @return the maxHit
	 */
	public int getMaxHit() {
		return maxHit;
	}
}
