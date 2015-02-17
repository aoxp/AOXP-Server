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

import com.ao.model.character.Character;
import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

/**
 * Tells the user that a character is / is not visible.
 * @author Juan Martín Sotuyo Dodero
 */
public class SetInvisiblePacket implements OutgoingPacket {
	private final Character character;
	private final boolean invisible;

	/**
	 * Creates a new SetInvisiblePacket.
	 * @param character The character being shown / hidden.
	 * @param invisible True if the char is invisible, false otherwise
	 */
	public SetInvisiblePacket(final Character character, final boolean invisible) {
		this.character = character;
		this.invisible = invisible;
	}

	@Override
	public void write(final DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putShort(character.getCharIndex());
		buffer.putBoolean(invisible);
	}
}
