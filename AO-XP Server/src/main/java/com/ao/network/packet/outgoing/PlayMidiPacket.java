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
