package ao.model.map;


/**
 * A game's map.
 */
public class Map {

	public static final int MAP_WIDTH = 100;
	public static final int MAP_HEIGHT = 100;
	
	protected String name;
	protected int id;
	
	// We don't user jagged arrays, they are inefficient in Java!
	protected Tile[] tiles;
	
	/**
	 * Creates a new Map.
	 * @param name The name of the map.
	 * @param id The unique id of the map.
	 * @param tiles The array of tiles composing the map.
	 */
	public Map(String name, int id, Tile[] tiles) {
		super();
		this.name = name;
		this.id = id;
		this.tiles = tiles;
	}
	
	/**
	 * Retrieves the tile at the given coordinates.
	 * @param x The coordinate along the x vertex (zero is at the left).
	 * @param y The coordinate along the y vertex (zero is at the top).
	 * @return The tile at the given coordinates.
	 */
	public Tile getTile(int x, int y) {
		return tiles[y * MAP_WIDTH + x];
	}

	/**
	 * Retrieves the map's name.
	 * @return The map's name.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Retrieves the map's unique id.
	 * @return The map's unique id.
	 */
	public int getId() {
		return id;
	}
	
}
