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

import ao.model.character.Gender;
import ao.model.character.Race;
import ao.model.character.Reputation;
import ao.model.character.archetype.UserArchetype;
import ao.model.worldobject.properties.ItemProperties;

/**
 * Abstract implementation of item, provides most functionality.
 */
public abstract class AbstractItem extends AbstractWorldObject implements Item {

	protected static final int MAX_STACKED_ITEMS = 10000;
	
	protected int amount;
	
	/**
	 * Creates a new AbstractItem instance.
	 * @param properties The item's properties.
	 * @param amount The item's amount.
	 */
	public AbstractItem(ItemProperties properties, int amount) {
		super(properties);
		
		this.amount = amount;
		
		if (this.amount > MAX_STACKED_ITEMS) {
			this.amount = MAX_STACKED_ITEMS;
		} else if (this.amount < 0) {
			this.amount = 0;
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#addAmount(int)
	 */
	@Override
	public int addAmount(int amount) {
		
		if (this.amount + amount <= MAX_STACKED_ITEMS) {
			this.amount += amount;
		} else {
			this.amount = MAX_STACKED_ITEMS;
		}
		
		return this.amount;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#canBeUsedBy(ao.model.character.Race, ao.model.character.Gender, ao.model.character.archetype.UserArchetype, ao.model.character.Reputation)
	 */
	@Override
	public boolean canBeUsedBy(Race race, Gender gender,
			UserArchetype archetype, Reputation reputation) {
		
		// Race is only important for armors and clothes
		List<UserArchetype> forbiddenArchetypes = ((ItemProperties) properties).getForbiddenArchetypes();
		
		if (forbiddenArchetypes != null && forbiddenArchetypes.contains(archetype)) {
			return false;
		}
		
		// TODO : Check by gender and reputation
		
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getAmount()
	 */
	@Override
	public int getAmount() {
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getBuyPrice()
	 */
	@Override
	public int getValue() {
		return ((ItemProperties) properties).getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getManufactureDifficulty()
	 */
	@Override
	public int getManufactureDifficulty() {
		return ((ItemProperties) properties).getManufactureDifficulty();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getUsageDifficulty()
	 */
	@Override
	public int getUsageDifficulty() {
		return ((ItemProperties) properties).getUsageDifficulty();
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#isTradeable()
	 */
	@Override
	public boolean isTradeable() {
		return ((ItemProperties) properties).isTradeable();
	}
	
	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#isNewbie()
	 */
	@Override
	public boolean isNewbie() {
		return ((ItemProperties) properties).isNewbie();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Item clone();
}
