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
 * Packet to tell the client their new hunger and thirst values
 *
 * @author mvanotti
 */
public class UpdateHungerAndThirstPacket implements OutgoingPacket {
	private final byte minHunger;
	private final byte maxHunger;
	private final byte minThirst;
	private final byte maxThirst;


	/**
	 * Creates a new UpdateHungerAndThirstPacket
	 *
	 * @param minHunger user's current hunger level
	 * @param maxHunger user's total hunger
	 * @param minThirst user's current thirst level
	 * @param maxThirst user's total thirst
	 */
	public UpdateHungerAndThirstPacket(final int minHunger, final int maxHunger,
			final int minThirst, final int maxThirst) {
		this.minHunger = (byte) minHunger;
		this.maxHunger = (byte) maxHunger;
		this.minThirst = (byte) minThirst;
		this.maxThirst = (byte) maxThirst;
	}

	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		// TODO : Is it really necessary to send the max values if they are constant?
		buffer.put(maxThirst);
		buffer.put(minThirst);
		buffer.put(maxHunger);
		buffer.put(minHunger);
	}

}
