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

import java.util.LinkedList;
import java.util.List;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.apache.log4j.Logger;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import ao.model.character.Character;
import ao.model.map.Tile;
import ao.model.map.Position;
import ao.model.map.Trigger;
import ao.model.map.WorldMap;
import ao.service.MapService;



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
	
	protected List<WorldMap> maps = new LinkedList<WorldMap>();
	private String mapPath;
	protected int mapAmount;
	
	@Inject
	public MapServiceImpl(@Named("MapsPath") String mapPath, @Named("MapAmount") int mapAmount) {

		this.mapPath = mapPath;
		this.mapAmount = mapAmount;

		int i = 0;

		for (i = 1; i <= mapAmount; i++) {
			maps.add(new WorldMap(i));
		}
		
	}
	
	/**
	 * Loads all maps
	 */
	public void loadMaps() {
		int i = 0;
		
		for (i = 1; i <= mapAmount; i++) {
			this.loadMap(i);
		}		
	}
	
	/**
	 * Retrieves the map with the given id.
	 * @param id The number map to be returned
	 * @return The WorldMap loaded
	 */
	public WorldMap getMap(int id) {
		if (id > 0 && id <= mapAmount) {
			return maps.get(id - 1);
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
			dataInf = new RandomAccessFile(mapPath + MAP_FILE_NAME + map + INF_EXTENSION, "r");
			dataMap = new RandomAccessFile(mapPath + MAP_FILE_NAME + map + MAP_EXTENSION, "r");
			bufInf = new byte[(int) dataInf.length()];
			bufMap = new byte[(int) dataMap.length()];
			dataInf.readFully(bufInf);		
			dataMap.readFully(bufMap);
		} catch (IOException e) {
			logger.error("Map loading failed!", e);
			throw new RuntimeException(e);
		}
		
		ByteBuffer bufferInf = ByteBuffer.wrap(bufInf);
		ByteBuffer bufferMap = ByteBuffer.wrap(bufMap);
		
		//VB writes with Little Endian and the default byte order in Java is Big Endian.
		bufferInf.order(ByteOrder.LITTLE_ENDIAN);
		bufferMap.order(ByteOrder.LITTLE_ENDIAN);

		//Get the map header

		short mapVersion = bufferMap.getShort(); 

		byte[] description = new byte[255];
		bufferMap.get(description);
		
		int CRC = bufferMap.getInt();
		int magicWord = bufferMap.getInt();	
		
		//The .map header has 8 bytes empty.
		byte[] emptyHeader = new byte[8];
		bufferMap.get(emptyHeader);

		//The .inf header has 10 bytes empty.
		emptyHeader = new byte[10];
		bufferInf.get(emptyHeader);	
		
		Tile[] Tiles = new Tile[WorldMap.MAP_HEIGHT * WorldMap.MAP_WIDTH];

		boolean blocked;
		Trigger trigger;
		Position tileExit;
		
		for (int y = 0; y < WorldMap.MAP_HEIGHT; y++ )  {
			for (int x = 0; x < WorldMap.MAP_HEIGHT; x++) {

				blocked = false;
				trigger = Trigger.NONE;
				tileExit = null;
				
				flag = bufferMap.get();

				//If the first bitflag is set then, the tile is blocked.
				if ((flag & BITFLAG_BLOCKED) == BITFLAG_BLOCKED) {
					blocked = true;
				}
				
				short[] layers = new short[Tile.LAYERS];

				//Every tile must have the first layer.
				layers[0] = bufferMap.getShort();

				if ((flag & BITFLAG_LAYER2) == BITFLAG_LAYER2) {
					layers[1] = bufferMap.getShort();
				}
				
				if ((flag & BITFLAG_LAYER3) == BITFLAG_LAYER3) {
					layers[2] = bufferMap.getShort();
				}

				if ((flag & BITFLAG_LAYER4) == BITFLAG_LAYER4) {
					layers[3] = bufferMap.getShort();
				}

				if ((flag & BITFLAG_TRIGGER) == BITFLAG_TRIGGER) {					
					try {
						trigger = Trigger.get(bufferMap.getShort());
					} catch (ArrayIndexOutOfBoundsException e) {
						logger.info("In (" + map + ", " + x + ", " + y + ") the trigger's value has an error");
						trigger = Trigger.NONE ;
					}
				}

				flag = bufferInf.get();

				if ((flag & BITFLAG_TILEEXIT) == BITFLAG_TILEEXIT) {
					tileExit = new Position(
											(byte) bufferInf.getShort(), 
											(byte) bufferInf.getShort(), 
											getMap(bufferInf.getShort())
											);
				}	

				if ((flag & BITFLAG_NPC) == BITFLAG_NPC) {
					//The NPC number.
					bufferInf.getShort();					
					//TODO Create the NPCharacter implementation
				}
				
				if ((flag & BITFLAG_OBJECT) == BITFLAG_OBJECT) {
					//The object index.
					bufferInf.getShort();
					
					//The objet's amount.
					bufferInf.getShort();
					
					//TODO Create the WorldObject implementation.				
				}
				
				//TODO Replace the nulls with the NPCCharacter and WorldObject objects.
				Tiles[WorldMap.getTileKey(x, y)] = new Tile(blocked, layers, trigger, tileExit, null, null);
		
				}
				
		}
		
		WorldMap buffMap = getMap(map);
		buffMap.setTiles(Tiles);
		return buffMap;
	}

}
