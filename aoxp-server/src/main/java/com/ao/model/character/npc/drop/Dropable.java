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

package com.ao.model.character.npc.drop;

/**
 * An item that may be dropped.
 * @author jsotuyod
 */
public class Dropable {

	protected int objId;
	protected int amount;
	protected float chance;

	/**
	 * Creates a new Dropable instance.
	 * @param objId The index of the world obejct that may be dropped.
	 * @param amount The amount of the item to be dropped.
	 * @param chance The chance this object has of being dropped.
	 */
	public Dropable(int objId, int amount, float chance) {
		super();
		this.objId = objId;
		this.amount = amount;
		this.chance = chance;
	}

	/**
	 * @return the objId
	 */
	public int getObjId() {
		return objId;
	}

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @return the chance
	 */
	public float getChance() {
		return chance;
	}
}
