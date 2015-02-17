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

import java.util.HashMap;
import java.util.Map;

import com.ao.model.character.Attribute;
import com.ao.network.Connection;

public class ConnectedUser implements User {
	private final Connection conn;

	private Account account;
	private Map<Attribute, Byte> attributes = new HashMap<Attribute, Byte>();

	/**
	 * Creates a new ConnectedUser.
	 * @param socket The channel over which to communicate with the client.
	 */
	public ConnectedUser(final Connection conn) {
		this.conn = conn;
	}

	@Override
	public Connection getConnection() {
		return conn;
	}

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
	public void setAccount(final Account account) {
		this.account = account;
	}

	/**
	 * Retrieves the asked attribute.
	 * @param dice The attribute.
	 * @return The attribute value.
	 */
	public Byte getAttribute(final Attribute dice) {
		return attributes.get(dice);
	}

	/**
	 * Sets the given attribute with the given value.
	 * @param dice The attribute to set.
	 * @param points The attribute's points.
	 */
	public void setAttribute(final Attribute dice, final byte points) {
		attributes.put(dice, points);
	}

}
