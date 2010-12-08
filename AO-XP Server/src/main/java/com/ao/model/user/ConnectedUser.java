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

package com.ao.model.user;

import java.util.Map;

import com.ao.model.character.Attribute;

public class ConnectedUser implements User {

	private Account account;
	private Map<Attribute, Byte> attributes;
	
	/**
	 * Retrieves the user's account.
	 * @return The account.
	 */
	public Account getAccount() {
		return account;
	}
	
	/**
	 * Sets the user's account.
	 * @param account The account.
	 */
	public void setAccount(Account account) {
		this.account = account;
	}
	
	/**
	 * Retrieves the asked attribute.
	 * @param dice The attribute.
	 * @return The attribute value.
	 */
	public Byte getAttribute(Attribute dice) {
		return attributes.get(dice);
	}
	
	/**
	 * Sets the given attribute with the given value.
	 * @param dice The attribute to set.
	 * @param points The attribute's points.
	 */
	public void setAttribute(Attribute dice, byte points) {
		attributes.put(dice, points);
	}
	
}
