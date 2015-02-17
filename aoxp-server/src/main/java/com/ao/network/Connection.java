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

package com.ao.network;

import org.jboss.netty.channel.Channel;

import com.ao.model.user.ConnectedUser;
import com.ao.model.user.User;
import com.ao.network.packet.OutgoingPacket;

/**
 * Connection class. A simple DTO to wrap the user and connection together.
 */
public class Connection {
	private Channel socket;
	private User user;

	/**
	 * Creates a new Connection.
	 * @param socket The channel over which to communicate with the client.
	 */
	public Connection(final Channel socket) {
		this.socket = socket;

		user = new ConnectedUser(this);
	}

	/**
	 * Retrieves the user.
	 * @return The user.
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Closes the connection.
	 */
	public void disconnect() {
		socket.close();
	}

	/**
	 * Sends the given packet to the client.
	 * @param packet The packet being sent.
	 */
	public void send(final OutgoingPacket packet) {
		socket.write(packet);
	}

	/**
	 * Changes the current connection's user model.
	 * @param user The new user model to be used.
	 */
	public void changeUser(final User user) {
		this.user = user;
	}
}
