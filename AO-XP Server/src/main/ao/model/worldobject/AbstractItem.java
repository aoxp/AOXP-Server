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

/**
 * Abstract implementation of item, provides most functionality.
 */
public abstract class AbstractItem implements Item {

	protected static final int MAX_STACKED_ITEMS = 10000;
	
	
	protected int id;
	protected String name;
	protected int amount;
	protected boolean tradeable;
	protected int graphic;
	protected int value;
	protected int usageDifficulty;
	protected int manufactureDifficulty;
	
	private List<UserArchetype> forbiddenArchetypes;
	
	/**
	 * Creates a new AbstractItem instance.
	 * @param id The id of the item.
	 * @param name The name of the item.
	 * @param amount The item's amount.
	 * @param tradeable True if it's tradeable, false otherwise.
	 * @param graphic The graphic for the item.
	 * @param value The item's value.
	 * @param usageDifficulty The item's usage difficulty.
	 * @param manufactureDifficulty The item's manufacture difficulty.
	 * @param forbiddenArchetypes List of UserArchetypes not allowed to use this item.
	 */
	public AbstractItem(int id, String name, int amount, boolean tradeable,
			int graphic, int value, int usageDifficulty,
			int manufactureDifficulty, boolean smallRace,
			List<UserArchetype> forbiddenArchetypes) {
		super();
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.tradeable = tradeable;
		this.graphic = graphic;
		this.value = value;
		this.usageDifficulty = usageDifficulty;
		this.manufactureDifficulty = manufactureDifficulty;
		this.forbiddenArchetypes = forbiddenArchetypes;
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
		return value;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getId()
	 */
	@Override
	public int getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getManufactureDifficulty()
	 */
	@Override
	public int getManufactureDifficulty() {
		return manufactureDifficulty;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#getUsageDifficulty()
	 */
	@Override
	public int getUsageDifficulty() {
		return usageDifficulty;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.Item#isTradeable()
	 */
	@Override
	public boolean isTradeable() {
		return tradeable;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.WorldObject#getGraphic()
	 */
	@Override
	public int getGraphic() {
		return graphic;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.model.worldobject.WorldObject#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#clone()
	 */
	@Override
	public abstract Item clone();
}
