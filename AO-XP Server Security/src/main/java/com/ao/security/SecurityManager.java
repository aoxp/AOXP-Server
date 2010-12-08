/*
 	      DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
                    Version 2, December 2004

 AO-XP Server (XP stands for Cross Platform) is a Java implementation of Argentum Online's server 
 Copyright (C) 2009-2010 Juan Martín Sotuyo Dodero. <juansotuyo@gmail.com>

 Everyone is permitted to copy and distribute verbatim or modified
 copies of this license document, and changing it is allowed as long
 as the name is changed.

            DO WHAT THE FUCK YOU WANT TO PUBLIC LICENSE
   TERMS AND CONDITIONS FOR COPYING, DISTRIBUTION AND MODIFICATION

  0. You just DO WHAT THE FUCK YOU WANT TO.
*/

package com.ao.security;

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
