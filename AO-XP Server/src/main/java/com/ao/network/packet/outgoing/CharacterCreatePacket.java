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
import com.ao.model.map.Heading;
import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class CharacterCreatePacket implements OutgoingPacket {

	private short body;
	private short head;
	private Heading heading;
	private short charIndex;
	private byte x;
	private byte y;
	private short weapon;
	private short shield;
	private short fx;
	private short fxLoops;
	private short helmet;
	private String name;
	private byte nickColor;
	private byte privileges;

	/**
	 * Create character packages
	 * 
	 * @param body
	 *            Body index of the new character.
	 * @param head
	 *            Head index of the new character.
	 * @param heading
	 *            Heading in which the new character is looking.
	 * @param charIndex
	 *            The index of the new character.
	 * @param x
	 *            X coordinate of the new character's position.
	 * @param y
	 *            Y coordinate of the new character's position.
	 * @param weapon
	 *            Weapon index of the new character.
	 * @param shield
	 *            Shield index of the new character.
	 * @param fx
	 *            FX index to be displayed over the new character.
	 * @param fxLoops
	 *            Number of times the FX should be rendered.
	 * @param helmet
	 *            Helmet index of the new character.
	 * @param name
	 *            Name of the new character.
	 * @param criminal
	 *            Determines if the character is a criminal or not.
	 * @param privileges
	 *            Sets if the character is a normal one or any kind of
	 *            administrative character.
	 */
	public CharacterCreatePacket(short body, short head, Heading heading, short charIndex, byte x, byte y,
			short weapon, short shield, short fx, short fxLoops, short helmet, String name, byte nickColor,
			byte privileges) {

		this.body = body;
		this.head = head;
		this.heading = heading;
		this.charIndex = charIndex;
		this.x = x;
		this.y = y;
		this.weapon = weapon;
		this.shield = shield;
		this.fx = fx;
		this.fxLoops = fxLoops;
		this.helmet = helmet;
		this.name = name;
		this.nickColor = nickColor;
		this.privileges = privileges;
	}

	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putShort(charIndex);
		buffer.putShort(body);
		buffer.putShort(head);
		buffer.put((byte) heading.ordinal());
		buffer.put(x);
		buffer.put(y);
		buffer.putShort(weapon);
		buffer.putShort(shield);
		buffer.putShort(helmet);
		buffer.putShort(fx);
		buffer.putShort(fxLoops);
		buffer.putASCIIString(name);
		buffer.put(nickColor);
		buffer.put(privileges);
	}

}
