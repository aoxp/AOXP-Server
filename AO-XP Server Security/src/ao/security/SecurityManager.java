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
 * Server security manager.
 *
 */
public interface SecurityManager {

	/**
	 * Retrieves the passwords' hash's length.
	 * @return The hash's length.
	 */
	int getPasswordHashLength();
	
	/**
	 * Retrieves the client's hash's length.
	 * @return The hash's length.
	 */
	int getClientHashLength();
	
	/**
	 * Retrieves all the valid client's hashes.
	 * @return The hashes.
	 */
	String[] getValidClientHashes();
	
	/**
	 * Encrypts the given buffer.
	 * @param buffer The buffer to by encrypted.
	 * @param sc The SocketChannel where the data will be sent.
	 */
	void encrypt(ByteBuffer buffer, SocketChannel sc);
	
	/**
	 * Decrypts the given buffer starting from the last mark.
	 * @param buffer The buffer to be decrypted.
	 * @param sc The SocketChannel where the data came from.
	 */
	void decrypt(ByteBuffer buffer, SocketChannel sc);
	
}
