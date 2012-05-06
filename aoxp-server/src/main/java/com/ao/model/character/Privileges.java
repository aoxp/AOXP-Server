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

package com.ao.model.character;

public class Privileges {

	public final int USER = 0x01;

	public final int COUNSELOR = 0x02;

	public final int DEMIGOD = 0x04;

	public final int GOD = 0x08;

	public final int ADMIN = 0x10;

	public final int ROLE_MASTER = 0x20;

	public final int CHAOS_COUNSELOR = 0x40;

	public final int ROYAL_COUNSELOR = 0x80;

	private int privilegesFlags;

	public Privileges(int privilegesFlags) {
		this.privilegesFlags = privilegesFlags;
	}

	public boolean isUser() {
		return ((privilegesFlags & USER) != 0);
	}
	
	public boolean isCouncelor() {
		return ((privilegesFlags & COUNSELOR) != 0);
	}
	
	public boolean isDemigod() {
		return ((privilegesFlags & DEMIGOD) != 0);
	}
	
	public boolean isGod() {
		return ((privilegesFlags & GOD) != 0);
	}
	
	public boolean isAdmin() {
		return ((privilegesFlags & ADMIN) != 0);
	}
	
	public boolean isRoleMaster() {
		return ((privilegesFlags & ROLE_MASTER) != 0);
	}
	
	public boolean isChaosCounserlor() {
		return ((privilegesFlags & CHAOS_COUNSELOR) != 0);
	}
	
	public boolean isRoyalCounselor() {
		return ((privilegesFlags & ROYAL_COUNSELOR) != 0);
	}
	
	public int getPrivilegesFlags() {
		return privilegesFlags;
	}

}
