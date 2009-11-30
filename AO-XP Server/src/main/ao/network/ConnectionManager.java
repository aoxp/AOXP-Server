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

package ao.network;

import java.io.IOException;
import java.nio.channels.SocketChannel;

import ao.model.user.User;

/**
 * Manages active connections.
 */
public interface ConnectionManager {
	
	/**
	 * Handles incoming data on the given socket channel.
	 * @param sc The socket channel whose data should be handled.
	 */
	void handleIncomingData(SocketChannel sc);
	
	/**
	 * Registers a new connection associated with the given socket channel.
	 * @param sc The socket channel of the new connection.
	 */
	void registerConnection(SocketChannel sc);
	
	/**
	 * Unregisters a connection associated with the given socket channel.
	 * @param sc The socket channel of the new connection.
	 */
	void unregisterConnection(SocketChannel sc);
	
	/**
	 * Retrieves the input buffer associated with the given socket channel.
	 * @param sc The socket channel for which the input buffer is requested.
	 * @return The input buffer for the requested socket channel.
	 */
	DataBuffer getInputBuffer(SocketChannel sc);
	
	/**
	 * Retrieves the output buffer associated with the given socket channel.
	 * @param user The user whose output buffer is requested.
	 * @return The output buffer for which the requested user.
	 */
	DataBuffer getOutputBuffer(User user);

	/**
	 * 
	 * Flushes the output buffer associated with the given user.
	 * @param user The user whose output buffer is being flushed.
	 * @throws IOException
	 */
	void flushOutputBuffer(User user) throws IOException;
}
