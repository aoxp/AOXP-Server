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

package com.ao.network;

import java.io.UnsupportedEncodingException;

import org.jboss.netty.buffer.ChannelBuffer;

public class DataBuffer {

	private static final String ASCII_FORMAT = "ASCII";
	private static final String UNICODE_FORMAT = "UTF8";
	protected ChannelBuffer buffer;

	public DataBuffer(ChannelBuffer buffer) {
		this.buffer = buffer;
	}

	/**
	 * Retrieves a byte array from the buffer and removes it.
	 * @param length The array length.
	 * @return The requested byte array.
	 */
	public byte[] getBlock(int length) {
		byte[] ret = new byte[length];

		buffer.readBytes(ret);

		return ret;
	}

	/**
	 * @see java.nio.ByteBuffer#get()
	 */
	public byte get() {
		return buffer.readByte();
	}
	
	
	public boolean getBoolean() {
		byte b = buffer.readByte();
		
		if (b == 1) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @see java.nio.ByteBuffer#getChar()
	 */
	public char getChar() {
		return buffer.readChar();
	}

	/**
	 * @see java.nio.ByteBuffer#getDouble()
	 */
	public double getDouble() {
		return buffer.readDouble();
	}

	/**
	 * @see java.nio.ByteBuffer#getFloat()
	 */
	public float getFloat() {
		return buffer.readFloat();
	}

	/**
	 * @see java.nio.ByteBuffer#getInt()
	 */
	public int getInt() {
		return buffer.readInt();
	}

	/**
	 * @see java.nio.ByteBuffer#getLong()
	 */
	public long getLong() {
		return buffer.readLong();
	}

	/**
	 * @see java.nio.ByteBuffer#getShort()
	 */
	public short getShort() {
		return buffer.readShort();
	}

	/**
	 * @see java.nio.ByteBuffer#put(byte)
	 */
	public DataBuffer put(byte b) {
		buffer.writeByte(b);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#put(byte[])
	 */
	public final DataBuffer put(byte[] src) {
		buffer.writeBytes(src);
		return this;
	}
	
	/**
	 * @see java.nio.ByteBuffer#put(byte)
	 */
	public DataBuffer putBoolean(boolean value) {
		if (value) {
			buffer.writeByte(1);
		} else {
			buffer.writeByte(0);			
		}

		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putChar(char)
	 */
	public DataBuffer putChar(char value) {
		buffer.writeChar(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putDouble(double)
	 */
	public DataBuffer putDouble(double value) {
		buffer.writeDouble(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putFloat(float)
	 */
	public DataBuffer putFloat(float value) {
		buffer.writeFloat(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putInt(int)
	 */
	public DataBuffer putInt(int value) {
		buffer.writeInt(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putLong(long)
	 */
	public DataBuffer putLong(long value) {
		buffer.writeLong(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putShort(short)
	 */
	public DataBuffer putShort(short value) {
		buffer.writeShort(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#toString()
	 */
	@Override
	public String toString() {
		return buffer.toString();
	}

	/**
	 * Reads an ASCII string from the buffer.
	 * @return The String at the buffer's current position.
	 * @throws UnsupportedEncodingException
	 */
	public String getASCIIString() throws UnsupportedEncodingException {

		// The string length is stored as a short just before the string itself.
		byte[] str = new byte[buffer.readShort()];

		buffer.readBytes(str);

		return new String(str, ASCII_FORMAT);
	}

	/**
	 * Reads an ASCII string with fixed length from the buffer.
	 * @param length The length of the string to be read.
	 * @return The String at the buffer's current position.
	 * @throws UnsupportedEncodingException
	 */
	public String getASCIIStringFixed(int length) throws UnsupportedEncodingException {

		byte[] str = new byte[length];

		buffer.readBytes(str);

		return new String(str, ASCII_FORMAT);
	}

	/**
	 * Reads a Unicode string from the buffer.
	 * @return The String at the buffer's current position.
	 * @throws UnsupportedEncodingException
	 */
	public String getUnicodeString() throws UnsupportedEncodingException {

		// The string length is stored as a short just before the string itself.
		byte[] str = new byte[buffer.readShort() * 2];

		buffer.readBytes(str);

		return new String(str, UNICODE_FORMAT);
	}

	/**
	 * Reads a Unicode string with a fixed length from the buffer.
	 * @param length The length of the string to be read,
	 * @return The String at the buffer's current position.
	 * @throws UnsupportedEncodingException
	 */
	public String getUnicodeString(int length) throws UnsupportedEncodingException {

		byte[] str = new byte[length];

		buffer.readBytes(str);

		return new String(str, UNICODE_FORMAT);
	}

	/**
	 * Writes the given ASCII string in the buffer's current position.
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException
	 */
	public DataBuffer putASCIIString(String value) throws UnsupportedEncodingException {
		buffer.writeShort((short) value.length());
		buffer.writeBytes(value.getBytes(ASCII_FORMAT));

		return this;
	}

	/**
	 * Writes the given ASCII string with fixed length in the buffer's current position.
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException
	 */
	public DataBuffer putASCIIStringFixed(String value) throws UnsupportedEncodingException {
		buffer.writeBytes(value.getBytes(ASCII_FORMAT));

		return this;
	}

	/**
	 * Writes the given Unicode string in the buffer's current position.
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException
	 */
	public DataBuffer putUnicodeString(String value) throws UnsupportedEncodingException {
		buffer.writeShort((short) value.length());
		buffer.writeBytes(value.getBytes(UNICODE_FORMAT));

		return this;
	}

	/**
	 * Writes the given Unicode string with fixed length in the buffer's current position.
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException
	 */
	public DataBuffer putUnicodeStringFixed(String value) throws UnsupportedEncodingException {
		buffer.writeBytes(value.getBytes(UNICODE_FORMAT));

		return this;
	}

	/**
	 * Retrieves the number of readable bytes in the buffer.
	 * @return The number of readable bytes in the buffer.
	 */
	public int getReadableBytes() {
		return buffer.readableBytes();
	}
}
