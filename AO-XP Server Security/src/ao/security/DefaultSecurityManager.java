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
