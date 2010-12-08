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

package com.ao.model.worldobject.properties;

import com.ao.model.worldobject.WorldObjectType;

public class DoorProperties extends WorldObjectProperties {

	protected boolean open;
	protected boolean locked;
	protected int code;
	protected int openGrh;
	protected int closedGrh;
	
	public DoorProperties(WorldObjectType type, int id, String name, int graphic, 
			boolean open, boolean locked, int code, 
			int openGrh, int closedGrh) {
		super(type, id, name, graphic);
		
		this.open = open;
		this.locked = locked;
		this.code = code;
		this.openGrh = openGrh;
		this.closedGrh = closedGrh;
	}

	/**
	 * @return the open.
	 */
	public boolean getOpen() {
		return open;
	}
	
	/**
	 * @return the locked.
	 */
	public boolean getLocked() {
		return locked;
	}
	
	/**
	 * @return the code.
	 */
	public int getCode() {
		return code;
	}
	
	/**
	 * @return the openId.
	 */
	public int getOpenGrh() {
		return openGrh;
	}
	
	/**
	 * @return the closedId.
	 */
	public int getClosedGrh() {
		return closedGrh;
	}
	
}