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

import ao.model.character.Character;
import ao.model.worldobject.properties.StaffProperties;

/**
 * A magic staff.
 */
public class Staff extends AbstractEquipableItem implements Weapon {

	/**
	 * Creates a new Staff instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public Staff(StaffProperties properties, int amount) {
		super(properties, amount);
	}

	/**
	 * Retrieves the staff's magic power.
	 * @return The staff's magic power.
	 */
	public int getMagicPower() {
		return ((StaffProperties) properties).getMagicPower();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Weapon#getAttackPower()
	 */
	@Override
	public int getAttackPower() {
		return ((StaffProperties) properties).getAttackPower();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.AbstractItem#clone()
	 */
	@Override
	public Item clone() {
		return new Staff((StaffProperties) properties, amount);
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
