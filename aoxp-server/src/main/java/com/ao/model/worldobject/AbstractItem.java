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

package com.ao.model.worldobject;

import java.util.List;

import com.ao.model.character.Gender;
import com.ao.model.character.Race;
import com.ao.model.character.Reputation;
import com.ao.model.character.archetype.UserArchetype;
import com.ao.model.worldobject.properties.ItemProperties;

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
	public AbstractItem(final ItemProperties properties, final int amount) {
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
	 * @see com.ao.model.worldobject.Item#addAmount(int)
	 */
	@Override
	public int addAmount(final int amount) {

		if (this.amount + amount <= MAX_STACKED_ITEMS) {
			this.amount += amount;
		} else {
			this.amount = MAX_STACKED_ITEMS;
		}

		return this.amount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#canBeUsedBy(ao.model.character.Race, com.ao.model.character.Gender, com.ao.model.character.archetype.UserArchetype, com.ao.model.character.Reputation)
	 */
	@Override
	public boolean canBeUsedBy(final Race race, final Gender gender,
			final UserArchetype archetype, final Reputation reputation) {

		// Check if the archetype can use this item
		final List<UserArchetype> forbiddenArchetypes = ((ItemProperties) properties).getForbiddenArchetypes();

		if (forbiddenArchetypes != null && forbiddenArchetypes.contains(archetype)) {
			return false;
		}

		// Check if the race can use this item
		final List<Race> forbiddenRaces = ((ItemProperties) properties).getForbiddenRaces();

		if (forbiddenRaces != null && forbiddenRaces.contains(race)) {
			return false;
		}

		// TODO : Check by gender and reputation

		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#getAmount()
	 */
	@Override
	public int getAmount() {
		return amount;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#getBuyPrice()
	 */
	@Override
	public int getValue() {
		return ((ItemProperties) properties).getValue();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#isNewbie()
	 */
	@Override
	public boolean isNewbie() {
		return ((ItemProperties) properties).isNewbie();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#canBeStolen()
	 */
	@Override
	public boolean canBeStolen() {
		return true;	// Everything can be stolen by default
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Item clone();

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#isFalls()
	 */
	public boolean isFalls() {
		return ((ItemProperties) properties).isFalls();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#isNoLog()
	 */
	public boolean isNoLog() {
		return ((ItemProperties) properties).isNoLog();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.model.worldobject.Item#isRespawnable()
	 */
	public boolean isRespawnable() {
		return ((ItemProperties) properties).isRespawnable();
	}
}
