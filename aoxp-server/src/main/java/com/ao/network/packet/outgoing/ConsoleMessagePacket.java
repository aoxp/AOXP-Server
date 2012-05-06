package com.ao.network.packet.outgoing;

import java.io.UnsupportedEncodingException;

import com.ao.model.fonts.Font;
import com.ao.network.DataBuffer;
import com.ao.network.packet.OutgoingPacket;

public class ConsoleMessagePacket implements OutgoingPacket {

    private String message;
    private Font font;

    /**
     * Creates a console message.
     *
     * @param message The message.
     * @param font    The message's font.
     */
    public ConsoleMessagePacket(String message, Font font) {
        this.message = message;
        this.font = font;
    }

    @Override
    public void write(DataBuffer buffer) throws UnsupportedEncodingException {

        buffer.putASCIIString(message);
        buffer.put((byte) font.ordinal());
    }

}
