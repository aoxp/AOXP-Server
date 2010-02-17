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

import java.util.List;

import ao.model.character.Character;
import ao.model.character.archetype.UserArchetype;

/**
 * A magic staff.
 */
public class Staff extends AbstractEquipableItem implements Weapon {

	protected int magicPower;
	protected int attackPower;
	
	/**
	 * Creates a new Staff instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param amount The item's amount.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 * @param newbie Whether the item is newbie or nor.
	 * @param equippedGraphic The index of the graphic when equipped.
	 * @param attackPower The attack power of the item.
	 * @param magicPower The magic power of the staff.
	 */
	public Staff(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty,
			List<UserArchetype> forbiddenArchetypes, boolean newbie, int equippedGraphic,
			int attackPower, int magicPower) {
		super(id, name, amount, tradeable, graphic, value, usageDifficulty,
				manufactureDifficulty, forbiddenArchetypes, newbie, equippedGraphic);
		
		this.attackPower = attackPower;
		this.magicPower = magicPower;
	}

	/**
	 * Retrieves the staff's magic power.
	 * @return The staff's magic power.
	 */
	public int getMagicPower() {
		return magicPower;
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Weapon#getAttackPower()
	 */
	@Override
	public int getAttackPower() {
		return attackPower;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Staff(magicPower, name, magicPower, tradeable, magicPower, magicPower, magicPower, magicPower, forbiddenArchetypes, newbie, magicPower, attackPower, magicPower);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#use(ao.model.character.Character)
	 */
	@Override
	public void use(Character character) {
		// TODO Auto-generated method stub
		
	}
}
