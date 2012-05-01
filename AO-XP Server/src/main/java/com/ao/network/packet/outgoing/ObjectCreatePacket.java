package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class ObjectCreatePacket implements OutgoingPacket {

	private short grhIndex;
	private byte x;
	private byte y;
	
	/**
	 * Creates an object.
	 * 
     * @param grhIndex Grh of the object.
     * @param x        X coord of the character's new position.
     * @param y        Y coord of the character's new position.
     */
    public ObjectCreatePacket(short grhIndex, byte x, byte y) {
        this.grhIndex = grhIndex;
        this.x = x;
        this.y = y;
    }

    @Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		
		buffer.putShort(grhIndex);
		buffer.put(x);
		buffer.put(y);
	}

}
