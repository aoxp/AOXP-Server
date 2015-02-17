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

import com.ao.model.worldobject.properties.SignProperties;

public class Sign extends AbstractWorldObject {

	/**
	 * Creates a new Sign instance.
	 * @param properties The object's properties.
	 */
	public Sign(SignProperties properties) {
		super(properties);
	}

	/**
	 * Retrieves the sign's big graphic.
	 * @return The sign's big graphic.
	 */
	public int getBigGraphic() {
		return ((SignProperties) properties).getBigGraphic();
	}

	/**
	 * Retrieves the sign's text.
	 * @return The sign's text.
	 */
	public String getText() {
		return ((SignProperties) properties).getText();
	}

	@Override
	public boolean isFixed() {
		return true;
	}
}
