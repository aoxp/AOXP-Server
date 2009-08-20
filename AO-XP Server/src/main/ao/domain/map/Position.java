package ao.domain.map;

public class Position {

	/**
	 * The position in the X axis.
	 */
	private byte x;
	
	/**
	 * The position in the Y axis.
	 */
	private byte y;
	
	/**
	 * The position's map.
	 */
	private int map;
	
	/**
	 * Creates a new position with the given data
	 * @param x	The position in the X axis.
	 * @param y	The position in the Y axis.
	 * @param map The map where the position is at.
	 */
	public Position(byte x, byte y, int map) {
		this.x = x;
		this.y = y;
		this.map = map;
	}
	
	/**
	 * Retrieves the position in the X axis.
	 * @return The position in the X axis.
	 */
	public byte getX() {
		return x;
	}
	
	/**
	 * Sets the position in the X axis.
	 * @param x	The new position in the X axis.
	 */
	public void setX(byte x) {
		this.x = x;
	}
	
	/**
	 * Retrieves the position in the Y axis.
	 * @return	The position in the Y axis.
	 */
	public byte getY() {
		return y;
	}
	
	/**
	 * Sets the position in the Y axis.
	 * @param y	The new position in the Y axis.
	 */
	public void setY(byte y) {
		this.y = y;
	}
	
	/**
	 * Retrieves the position's map.
	 * @return The position's map.
	 */
	public int getMap() {
		return map;
	}
	
	/**
	 * Sets the position's map.
	 * @param map The new position's map. 
	 */
	public void setMap(int map) {
		this.map = map;
	}
	
	// TODO: Agregar métodos para desplazarse fácilmente sobre ejes X e Y.
}
