package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class DiceRollPacket implements OutgoingPacket {

	private byte strength;
	private byte agility;
	private byte intelligence;
	private byte charisma;
	private byte constitution;
	
	
	public DiceRollPacket(byte strength, byte agility, byte intelligence, byte charisma, byte constitution) {
		
		this.strength = strength;
		this.agility = agility;
		this.intelligence = intelligence;
		this.charisma = charisma;
		this.constitution = constitution;
	}


	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		
		buffer.put(strength);
		buffer.put(agility);
		buffer.put(intelligence);
		buffer.put(charisma);
		buffer.put(constitution);
	}

}
