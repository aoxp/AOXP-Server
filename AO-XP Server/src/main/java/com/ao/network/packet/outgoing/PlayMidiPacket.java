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

public class PlayMidiPacket implements OutgoingPacket {

	private int midi;
	private int loops;
	
	/**
	 * Creates a PlayMidiPacket
	 * 
	 * @param midiIndex The index of the midi sound.
	 * @param loops The number of times to be played. -1 for infinite times.
	 */
	public PlayMidiPacket(int midiIndex, int loops) {
		this.midi = midiIndex;
		this.loops = loops;
	}
	
	
	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putShort((short) midi);
		buffer.putShort((short) loops);
	}

}
