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

import ao.model.worldobject.properties.DoorProperties;
import ao.model.worldobject.properties.WorldObjectProperties;

public class Door extends AbstractWorldObject {

	public Door(WorldObjectProperties properties) {
		super(properties);
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the open.
	 */
	public boolean getOpen() {
		return ((DoorProperties) properties).getOpen();
	}
	
	/**
	 * @return the locked.
	 */
	public boolean getLocked() {
		return ((DoorProperties) properties).getLocked();
	}
	
	/**
	 * @return the code.
	 */
	public int getCode() {
		return ((DoorProperties) properties).getCode();
	}
	
	/**
	 * @return the openId.
	 */
	public int getOpenId() {
		return ((DoorProperties) properties).getOpenId();
	}
	
	/**
	 * @return the closedId.
	 */
	public int getClosedId() {
		return ((DoorProperties) properties).getClosedId();
	}
	
	/**
	 * @return the lockedId.
	 */
	public int getLockedId() {
		return ((DoorProperties) properties).getLockedId();
	}
	
}
