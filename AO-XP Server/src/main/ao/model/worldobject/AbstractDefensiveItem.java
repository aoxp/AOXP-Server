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

import ao.model.worldobject.properties.DefensiveItemProperties;

/**
 * Base class for defensive items.
 */
public abstract class AbstractDefensiveItem extends AbstractEquipableItem implements
		DefensiveItem {
	
	/**
	 * Creates a new AbstractDefensiveItem instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public AbstractDefensiveItem(DefensiveItemProperties properties, int amount) {
		super(properties, amount);
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.DefensiveItem#getMaxDef()
	 */
	@Override
	public int getMaxDef() {
		return ((DefensiveItemProperties) properties).getMaxDef();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.DefensiveItem#getMinDef()
	 */
	@Override
	public int getMinDef() {
		return ((DefensiveItemProperties) properties).getMinDef();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.DefensiveItem#getMinMagicDef()
	 */
	@Override
	public int getMinMagicDef() {
		return ((DefensiveItemProperties) properties).getMinMagicDef();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.DefensiveItem#getMaxMagicDef()
	 */
	@Override
	public int getMaxMagicDef() {
		return ((DefensiveItemProperties) properties).getMaxMagicDef();
	}
}
