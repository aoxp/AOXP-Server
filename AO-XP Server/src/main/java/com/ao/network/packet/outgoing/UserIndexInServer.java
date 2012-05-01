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
package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

/**
 * Packet to tell the client his user index in the server. 
 * @author Gonzalo Fernández Verón
 */
public class UserIndexInServer implements OutgoingPacket {

	private short userIndex;

	/**
	 * Creates a new UserIndexInServer
	 * @param userIndex The index to tell to the client.  
	 */
	public UserIndexInServer(short userIndex) {
		super();
		this.userIndex = userIndex; 

	}

	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putShort(this.userIndex);
	}

}
