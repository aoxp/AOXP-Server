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

package com.ao.network.packet;

import java.io.UnsupportedEncodingException;

import com.ao.network.Connection;
import com.ao.network.DataBuffer;

public interface IncomingPacket {

	/**
	 * Handles the received packet.
	 * @param buffer The buffer from which to read data.
	 * @param connection The packet's connection container.
	 * @return True if the packet was complete, false otherwise.
	 * @throws IndexOutOfBoundsException If there is not enough data in the buffer
	 * @throws UnsupportedEncodingException If there is a string whose encoding it unknown.
	 */
	boolean handle(DataBuffer buffer, Connection connection) throws IndexOutOfBoundsException, UnsupportedEncodingException;
}
