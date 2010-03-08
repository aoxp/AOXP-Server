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

package ao.service.map;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.log4j.Logger;

import ao.model.map.Position;
import ao.model.map.Tile;
import ao.model.map.Trigger;
import ao.model.map.WorldMap;
import ao.service.MapService;

import com.google.inject.Inject;
import com.google.inject.name.Named;

/**
 * Concrete implementation of MapService.
 */
public class MapServiceImpl implements MapService {

	private static final Logger logger = Logger.getLogger(MapServiceImpl.class);
	
	private static final String MAP_EXTENSION = ".map";
	private static final String INF_EXTENSION = ".inf";
	private static final String MAP_FILE_NAME = "Mapa";
	
	private static final byte BITFLAG_BLOCKED = 1;
	private static final byte BITFLAG_LAYER2 = 2;
	private static final byte BITFLAG_LAYER3 = 4;
	private static final byte BITFLAG_LAYER4 = 8;
	private static final byte BITFLAG_TRIGGER = 16;
	
	private static final byte BITFLAG_TILEEXIT = 1;
	private static final byte BITFLAG_NPC = 2;
	private static final byte BITFLAG_OBJECT = 4;
	
	protected WorldMap[] maps;
	private String mapsPath;
	protected int mapsAmount;
	
	@Inject
	public MapServiceImpl(@Named("MapsPath") String mapsPath, @Named("MapsAmount") int mapsAmount) {

		this.mapsPath = mapsPath;
		this.mapsAmount = mapsAmount;
		this.maps = new WorldMap[mapsAmount];

		for (int i = 1; i <= mapsAmount; i++) {
			maps[i - 1] = new WorldMap(i);
		}
	}
	
	/**
	 * Loads all maps
	 */
	public void loadMaps() {
		
		for (int i = 1; i <= mapsAmount; i++) {
			loadMap(i);
		}		
	}
	
	/**
	 * Retrieves the map with the given id.
	 * @param id The number map to be returned
	 * @return The WorldMap loaded
	 */
	public WorldMap getMap(int id) {
		if (id > 0 && id <= mapsAmount) {
			return maps[id - 1];
		}
		
		return null;
	}
	
	/**
	 * Loads the given map from the map path.
	 * @param map The number map to be loaded
	 * @return The WorldMap loaded
	 */
	private WorldMap loadMap(int map) {
	
		byte flag;
		RandomAccessFile dataInf = null;
		RandomAccessFile dataMap = null;
		byte[] bufInf = null;
		byte[] bufMap = null;
				
		try {
			// Completely read both files
			dataInf = new RandomAccessFile(mapsPath + MAP_FILE_NAME + map + INF_EXTENSION, "r");
			dataMap = new RandomAccessFile(mapsPath + MAP_FILE_NAME + map + MAP_EXTENSION, "r");
			bufInf = new byte[(int) dataInf.length()];
			bufMap = new byte[(int) dataMap.length()];
			dataInf.readFully(bufInf);		
			dataMap.readFully(bufMap);
		} catch (IOException e) {
			logger.error("Map " + map + " loading failed!", e);
			throw new RuntimeException(e);
		}
		
		ByteBuffer infBuffer = ByteBuffer.wrap(bufInf);
		ByteBuffer mapBuffer = ByteBuffer.wrap(bufMap);
		
		// VB writes with Little Endian and the default byte order in Java is Big Endian.
		infBuffer.order(ByteOrder.LITTLE_ENDIAN);
		mapBuffer.order(ByteOrder.LITTLE_ENDIAN);

		// Get the map header

		short mapVersion = mapBuffer.getShort(); 

		byte[] description = new byte[255];
		mapBuffer.get(description);
		
		int crc = mapBuffer.getInt();
		int magicWord = mapBuffer.getInt();	
		
		// The .map header has 8 empty bytes.
		byte[] emptyHeader = new byte[8];
		mapBuffer.get(emptyHeader);

		// The .inf header has 10 empty bytes.
		emptyHeader = new byte[10];
		infBuffer.get(emptyHeader);	
		
		Tile[] tiles = new Tile[WorldMap.MAP_HEIGHT * WorldMap.MAP_WIDTH];

		boolean blocked;
		Trigger trigger;
		Position tileExit;
		short[] layers;
		
		for (int y = 0; y < WorldMap.MAP_HEIGHT; y++) {
			for (int x = 0; x < WorldMap.MAP_HEIGHT; x++) {

				blocked = false;
				trigger = Trigger.NONE;
				tileExit = null;
				
				flag = mapBuffer.get();

				// If the first bitflag is set the tile is blocked.
				if ((flag & BITFLAG_BLOCKED) == BITFLAG_BLOCKED) {
					blocked = true;
				}
				
				layers = new short[Tile.LAYERS_AMOUNT];

				// Every tile must have the first layer.
				layers[0] = mapBuffer.getShort();

				if ((flag & BITFLAG_LAYER2) == BITFLAG_LAYER2) {
					layers[1] = mapBuffer.getShort();
				}
				
				if ((flag & BITFLAG_LAYER3) == BITFLAG_LAYER3) {
					layers[2] = mapBuffer.getShort();
				}

				if ((flag & BITFLAG_LAYER4) == BITFLAG_LAYER4) {
					layers[3] = mapBuffer.getShort();
				}

				if ((flag & BITFLAG_TRIGGER) == BITFLAG_TRIGGER) {
					
					try {
						trigger = Trigger.get(mapBuffer.getShort());
					} catch (ArrayIndexOutOfBoundsException e) {
						logger.warn(String.format("The position (%d, %d, %d) has an invalid trigger.", map, x, y));
						trigger = Trigger.NONE;
					}
				}

				flag = infBuffer.get();

				if ((flag & BITFLAG_TILEEXIT) == BITFLAG_TILEEXIT) {
					tileExit = new Position(
											(byte) infBuffer.getShort(), 
											(byte) infBuffer.getShort(), 
											getMap(infBuffer.getShort())
											);
				}	

				if ((flag & BITFLAG_NPC) == BITFLAG_NPC) {
					// The NPC number.
					infBuffer.getShort();					
					// TODO: instantiate the NPCCharacter object.
				}
				
				if ((flag & BITFLAG_OBJECT) == BITFLAG_OBJECT) {
					// The object index.
					infBuffer.getShort();
					
					// The object's amount.
					infBuffer.getShort();
					
					// TODO: instantiate the WorldObject object.				
				}
				
				// TODO: Replace the nulls with the NPCCharacter and WorldObject objects.
				tiles[WorldMap.getTileKey(x, y)] = new Tile(blocked, layers, trigger, tileExit, null, null);
		
				}
				
		}
		
		WorldMap buffMap = getMap(map);
		buffMap.setTiles(tiles);
		
		return buffMap;
	}

}
