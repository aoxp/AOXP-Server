package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class GuildChatPacket implements OutgoingPacket {

	private String message;
	
	GuildChatPacket(String message) {
		this.message = message;
	}
	
	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.putASCIIString(message);
	}

}
