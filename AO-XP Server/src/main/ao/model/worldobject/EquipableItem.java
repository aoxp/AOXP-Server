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

public interface EquipableItem extends Item {

	/**
	 * Checks if the item is equipped or not.
	 * @return True if the item is equipped, false otherwise.
	 */
	boolean isEquipped();
	
	/**
	 * Sets the item as equipped or not.
	 * @param equipped True if the item is equipped, false otherwise.
	 */
	void setEquipped(boolean equipped);
	
	/**
	 * Retrieves the object's graphic when equipped (may be a body index, head index, etc. according to the specific item). 
	 * @return The object's graphic when equipped (may be a body index, head index, etc. according to the specific item).
	 */
	int getEquippedGraphic();
}
