package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.model.map.Position;
import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class PlayWavePacket implements OutgoingPacket {

	private int wave;
	private Position position;
	
	/**
	 * Creates  PlayWavePacket.
	 * 
	 * @param waveIndex The wave index.
	 * @param position The position of the map where the sound comes from.
	 */
	PlayWavePacket(int waveIndex, Position position) {
		this.wave = waveIndex;
		this.position = position;
	}
	
	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.put((byte) wave);
		buffer.put((byte) position.getX());
		buffer.put((byte) position.getY());
	}
	
}
