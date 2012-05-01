package com.ao.network;

import java.io.UnsupportedEncodingException;

import com.ao.model.spell.Spell;
import com.ao.network.packet.OutgoingPacket;

/**
 * Tells the client to change a spell's slot.
 * @author Juan Martín Sotuyo Dodero
 */
public class ChangeSpellSlotPacket implements OutgoingPacket {

	private Spell spell;
	private byte slot;

	/**
	 * Creates a new ChangeSpellSlotPacket
	 * @param spell The spell to set at the given position (may be null)
	 * @param slot The slot at which to set the item.
	 */
	private ChangeSpellSlotPacket(Spell spell, byte slot) {
		super();
		this.spell = spell;
		this.slot = slot;
	}

	@Override
	public void write(DataBuffer buffer) throws UnsupportedEncodingException {
		buffer.put(slot);

		if (spell == null) {
			buffer.putShort((short) 0);
			buffer.putASCIIString("(None)");
		} else {
			buffer.putShort((short) spell.getId());
			buffer.putASCIIString(spell.getName());
		}
	}

}
