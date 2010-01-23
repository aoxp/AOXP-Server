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

package ao.model.map;

/**
 * Triggers for map position events.
 */
public enum Trigger {
	NONE,
    UNDERROOF,
    trigger_2, // TODO: What is this?
    INVALIDPOSITION,
    SAFEZONE,
    ANTIPICKET,
    FIGHTZONE;


	/**
	 * Enum values.
	 */
	private static Trigger[] values = Trigger.values();
	
	/**
	 * Retrieves the gender with the given index.
	 * @param index The gender index.
	 * @return The gender.
	 */
	public static Trigger get(short index) {
		return values[index];
	}
	
}