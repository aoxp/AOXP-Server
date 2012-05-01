package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class ChangeMapPacket implements OutgoingPacket {
	
	private short map;
	private short version;
	
	public ChangeMapPacket(short map, short version) {
		this.map = map;
		this.version= version;
	}

	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putShort(map);
		buffer.putShort(version);
	}
}
