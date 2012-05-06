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
package com.ao.model.worldobject.properties.manufacture;

import com.ao.model.worldobject.properties.WorldObjectProperties;

/**
 * Defines a manufacturable item.
 * @author jsotuyod
 */
public class Manufacturable {

	protected WorldObjectProperties object;
	
	protected ManufactureType manufactureType;
	
	protected int manufactureDifficulty;
	protected int requiredWood;
	protected int requiredElvenWood;
	protected int requiredGoldIngot;
	protected int requiredSilverIngot;
	protected int requiredIronIngot;
	
	/**
	 * Create a new manufacturable instance.
	 * 
	 * @param object The object that can be manufactured.
	 * @param manufactureType The type of manufacturing skill required.
	 * @param manufactureDifficulty The difficulty to manufacture this item.
	 * @param requiredWood The wood required for this manufacture.
	 * @param requiredElvenWood The elven wood required for this manufacture.
	 * @param requiredGoldIngot The gold ingots required for this manufacture.
	 * @param requiredSilverIngot The silver ingots required for this manufacture.
	 * @param requiredIronIngot The iron ingots required for this manufacture.
	 */
	public Manufacturable(WorldObjectProperties object, ManufactureType manufactureType,
			int manufactureDifficulty, int requiredWood, int requiredElvenWood,
			int requiredGoldIngot, int requiredSilverIngot,
			int requiredIronIngot) {
		super();
		this.object = object;
		this.manufactureType = manufactureType;
		this.manufactureDifficulty = manufactureDifficulty;
		this.requiredWood = requiredWood;
		this.requiredElvenWood = requiredElvenWood;
		this.requiredGoldIngot = requiredGoldIngot;
		this.requiredSilverIngot = requiredSilverIngot;
		this.requiredIronIngot = requiredIronIngot;
	}

	/**
	 * Retrieves the type of manufacturable the item is.
	 * @return The type of manufacturable the described item is.
	 */
	public ManufactureType getManufactureType() {
		return manufactureType;
	}
	
	/**
	 * Retrieves the difficulty to manufacture this item.
	 * @return The difficulty to manufacture this item.
	 */
	public int getManufactureDifficulty() {
		return manufactureDifficulty;
	}

	/**
	 * @return the requiredWood
	 */
	public int getRequiredWood() {
		return requiredWood;
	}

	/**
	 * @return the requiredElvenWood
	 */
	public int getRequiredElvenWood() {
		return requiredElvenWood;
	}

	/**
	 * @return the requiredGoldIngot
	 */
	public int getRequiredGoldIngot() {
		return requiredGoldIngot;
	}

	/**
	 * @return the requiredSilverIngot
	 */
	public int getRequiredSilverIngot() {
		return requiredSilverIngot;
	}

	/**
	 * @return the requiredIronIngot
	 */
	public int getRequiredIronIngot() {
		return requiredIronIngot;
	}

	/**
	 * @return the object
	 */
	public WorldObjectProperties getObject() {
		return object;
	}
}
