package ao.network;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

public class DataBuffer {

	private static final String ASCII_FORMAT = "ASCII";
	private static final String UNICODE_FORMAT = "UTF8";
	protected ByteBuffer buffer;

	public DataBuffer(int capacity) {
		buffer = ByteBuffer.allocate(capacity);
	}

	/**
	 * @see java.nio.ByteBuffer#get()
	 */
	public byte get() {
		return buffer.get();
	}

	/**
	 * @see java.nio.ByteBuffer#getChar()
	 */
	public char getChar() {
		return buffer.getChar();
	}

	/**
	 * @see java.nio.ByteBuffer#getDouble()
	 */
	public double getDouble() {
		return buffer.getDouble();
	}

	/**
	 * @see java.nio.ByteBuffer#getFloat()
	 */
	public float getFloat() {
		return buffer.getFloat();
	}

	/**
	 * @see java.nio.ByteBuffer#getInt()
	 */
	public int getInt() {
		return buffer.getInt();
	}

	/**
	 * @see java.nio.ByteBuffer#getLong()
	 */
	public long getLong() {
		return buffer.getLong();
	}

	/**
	 * @see java.nio.ByteBuffer#getShort()
	 */
	public short getShort() {
		return buffer.getShort();
	}

	/**
	 * @see java.nio.ByteBuffer#put(byte)
	 */
	public DataBuffer put(byte b) {
		buffer.put(b);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#put(byte[])
	 */
	public final DataBuffer put(byte[] src) {
		buffer.put(src);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putChar(char)
	 */
	public DataBuffer putChar(char value) {
		buffer.putChar(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putDouble(double)
	 */
	public DataBuffer putDouble(double value) {
		buffer.putDouble(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putFloat(float)
	 */
	public DataBuffer putFloat(float value) {
		buffer.putFloat(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putInt(int)
	 */
	public DataBuffer putInt(int value) {
		buffer.putInt(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putLong(long)
	 */
	public DataBuffer putLong(long value) {
		buffer.putLong(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#putShort(short)
	 */
	public DataBuffer putShort(short value) {
		buffer.putShort(value);
		return this;
	}

	/**
	 * @see java.nio.ByteBuffer#toString()
	 */
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
		byte[] str = new byte[buffer.getShort()];
		
		buffer.get(str);
		
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
		
		buffer.get(str);
		
		return new String(str, ASCII_FORMAT);
	}
	
	/**
	 * Reads a Unicode string from the buffer.
	 * @return The String at the buffer's current position.
	 * @throws UnsupportedEncodingException
	 */
	public String getUnicodeString() throws UnsupportedEncodingException {
		
		// The string length is stored as a short just before the string itself.
		byte[] str = new byte[buffer.getShort() * 2];
		
		buffer.get(str);
		
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
		
		buffer.get(str);
		
		return new String(str, UNICODE_FORMAT);
	}
	
	/**
	 * Writes the given ASCII string in the buffer's current position. 
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException 
	 */
	public DataBuffer putASCIIString(String value) throws UnsupportedEncodingException {
		buffer.putShort((short) value.length());
		buffer.put(value.getBytes(ASCII_FORMAT));
		
		return this;
	}
	
	/**
	 * Writes the given ASCII string with fixed length in the buffer's current position. 
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException 
	 */
	public DataBuffer putASCIIStringFixed(String value) throws UnsupportedEncodingException {
		buffer.put(value.getBytes(ASCII_FORMAT));
		
		return this;
	}
	
	/**
	 * Writes the given Unicode string in the buffer's current position. 
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException 
	 */
	public DataBuffer putUnicodeString(String value) throws UnsupportedEncodingException {
		buffer.putShort((short) value.length());
		buffer.put(value.getBytes(UNICODE_FORMAT));
		
		return this;
	}
	
	/**
	 * Writes the given Unicode string with fixed length in the buffer's current position. 
	 * @param value The string to be written.
	 * @return This buffer.
	 * @throws UnsupportedEncodingException 
	 */
	public DataBuffer putUnicodeStringFixed(String value) throws UnsupportedEncodingException {
		buffer.put(value.getBytes(UNICODE_FORMAT));
		
		return this;
	}
}
