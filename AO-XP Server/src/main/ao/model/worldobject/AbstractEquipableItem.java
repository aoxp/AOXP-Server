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

import ao.model.worldobject.properties.EquippableItemProperties;

/**
 * Base class for equipable items.
 */
public abstract class AbstractEquipableItem extends AbstractItem implements
		EquipableItem {
	
	protected boolean equipped;
	
	/**
	 * Creates a new AbstractEquipableItem instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public AbstractEquipableItem(EquippableItemProperties properties, int amount) {
		super(properties, amount);
		
		this.equipped = false;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.EquipableItem#getEquippedGraphic()
	 */
	@Override
	public int getEquippedGraphic() {
		return ((EquippableItemProperties) properties).getEquippedGraphic();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.EquipableItem#isEquipped()
	 */
	@Override
	public boolean isEquipped() {
		return equipped;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.EquipableItem#setEquipped(boolean)
	 */
	@Override
	public void setEquipped(boolean equipped) {
		this.equipped = equipped;
	}
}
