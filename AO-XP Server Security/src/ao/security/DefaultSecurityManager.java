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

package ao.security;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Default SecurityManager implementation, not so secure.
 */
public class DefaultSecurityManager implements SecurityManager {

	@Override
	public int getClientHashLength() {
		// No client hash for default.
		return 0;
	}

	@Override
	public int getPasswordHashLength() {
		return Hashing.MD5_ASCII_LENGTH;
	}

	@Override
	public String[] getValidClientHashes() {
		return new String[] {};
	}

	@Override
	public void decrypt(ByteBuffer buffer, SocketChannel sc) {
		// Do nothing, no encryption for default.
	}

	@Override
	public void encrypt(ByteBuffer buffer, SocketChannel sc) {
		// Do nothing, no encryption for default.
	}

}
