package ao.model.map;

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
	private Map map;
	
	/**
	 * Creates a new position with the given data
	 * @param x	The position in the X axis.
	 * @param y	The position in the Y axis.
	 * @param map The position's map.
	 */
	public Position(byte x, byte y, Map map) {
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
	public Map getMap() {
		return map;
	}
	
	/**
	 * Sets the position's map.
	 * @param map The new position's map. 
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * Adds (or substract if the given number is negative) positions to the X axis.
	 * @param positions The positions to add.
	 */
	public void addToX(int positions) {
		// TODO: Chequear que el número no se vaya fuera de los rangos?
		x += positions;
	}
	
	/**
	 * Adds (or substract if the given number is negative) positions to the Y axis.
	 * @param positions The positions to add.
	 */
	public void addToY(int positions) {
		// TODO: Chequear que el número no se vaya fuera de los rangos?
		y += positions;
	}
	
	/**
	 * Calculates the Manhattan distance to the given Position.
	 * @param pos The other position to calculate the distance.
	 * @return The distance to the other position.
	 */
	public int getDistance(Position pos) {
		return Math.abs(x - pos.x) + Math.abs(y - pos.y);
	}

	/**
	 * Checks if the given position is in our vision range.
	 * @param pos The position to check.
	 * @return True if the given position is in the vision range, false otherwise.
	 */
	public boolean inVisionRange(Position pos) {
		
		if (map != pos.map ||
			Math.abs(x - pos.x) > Map.VISIBLE_AREA_WIDTH ||
			Math.abs(y - pos.y) > Map.VISIBLE_AREA_HEIGHT) {
				return false;
		}
		
		return true;
	}
}
