package ao.network;

import java.io.UnsupportedEncodingException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class DataBuffer {

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
	 * @see java.nio.ByteBuffer#get(int)
	 */
	public byte get(int index) {
		return buffer.get(index);
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
	public ByteBuffer put(byte b) {
		return buffer.put(b);
	}

	/**
	 * @see java.nio.ByteBuffer#put(byte[])
	 */
	public final ByteBuffer put(byte[] src) {
		return buffer.put(src);
	}

	/**
	 * @see java.nio.ByteBuffer#putChar(char)
	 */
	public ByteBuffer putChar(char value) {
		return buffer.putChar(value);
	}

	/**
	 * @see java.nio.ByteBuffer#putDouble(double)
	 */
	public ByteBuffer putDouble(double value) {
		return buffer.putDouble(value);
	}

	/**
	 * @see java.nio.ByteBuffer#putFloat(float)
	 */
	public ByteBuffer putFloat(float value) {
		return buffer.putFloat(value);
	}

	/**
	 * @see java.nio.ByteBuffer#putInt(int)
	 */
	public ByteBuffer putInt(int value) {
		return buffer.putInt(value);
	}

	/**
	 * @see java.nio.ByteBuffer#putLong(long)
	 */
	public ByteBuffer putLong(long value) {
		return buffer.putLong(value);
	}

	/**
	 * @see java.nio.ByteBuffer#putShort(short)
	 */
	public ByteBuffer putShort(short value) {
		return buffer.putShort(value);
	}

	/**
	 * @see java.nio.ByteBuffer#toString()
	 */
	public String toString() {
		return buffer.toString();
	}
	
	/**
	 * @see java.nio.Buffer#clear()
	 */
	public final Buffer clear() {
		return buffer.clear();
	}
	
	/**
	 * @see java.nio.Buffer#flip()
	 */
	public final Buffer flip() {
		return buffer.flip();
	}
	
	/**
	 *  Reads the first int at the buffer's current position and then
	 *  substracts that amount of bytes to compose them into a string.
	 * @return The String value at the buffer's current position.
	 */
	public String getASCIIString() {
		
		// The string length is stored as int just before the string itself.
		byte[] str = new byte[buffer.getInt()];
		
		buffer.get(str);
		
		return new String(str);
	}
	
	/**
	 *  Reads the first int at the buffer's current position and then
	 *  substracts that amount of bytes by two to compose them into an unicode string.
	 * @return The String value at the buffer's current position.
	 */
	public String getUnicodeString() throws UnsupportedEncodingException {
		
		// The string length is stored as int just before the string itself.
		byte[] str = new byte[buffer.getInt() * 2];
		
		buffer.get(str);
		
		return new String(str, "UTF8");
		
	}
	
	/**
	 * Writes the given string as a byte array in the buffer's current position. 
	 * @param value The string to write.
	 * @return This buffer.
	 */
	public ByteBuffer putString(String value) {
		return buffer.put(value.getBytes());
	}
	
}
