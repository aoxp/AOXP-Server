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

import com.ao.model.character.Character;
import com.ao.model.worldobject.properties.KeyProperties;

/**
 * A Key.
 * @author itirabasso
 */
public class Key extends AbstractItem {

	/**
	 * Creates a new instance of a key.
	 * @param properties The key's properties.
	 * @param amount The amount of keys being created.
	 */
	public Key(KeyProperties properties, int amount) {
		super(properties, amount);
	}

	@Override
	public Item clone() {
		return new Key((KeyProperties) properties, amount);
	}

	@Override
	public void use(Character character) {
		// TODO Auto-generated method stub

	}

	/**
	 * Retrieves the code for this key.
	 * @return The key's code.
	 */
	public int getCode() {
		return ((KeyProperties) properties).getCode();
	}

}
