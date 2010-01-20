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
import ao.model.map.WorldMap;
import ao.service.MapService;



public class MapServiceImpl implements MapService {

	private static final Logger logger = Logger.getLogger(MapServiceImpl.class);
	private static final String MAP_EXTENSION = ".map";
	private static final String INF_EXTENSION = ".inf";
	
	private List<WorldMap> mapList = new LinkedList<WorldMap>();
	private String mapPath;
	private int mapAmount;
	
	@Inject
	public MapServiceImpl(@Named("MapsPath") String mapPath, @Named("MapAmount") int mapAmount) {
		// TODO Auto-generated constructor stub
 		this.mapPath = mapPath;
		this.mapAmount = mapAmount;

		int count = 0;

		for (count = 1; count < mapAmount + 1; count++) {
			mapList.add(new WorldMap(count));
		}
		
	}
	
	/**
	 * Loads all maps
	 */
	public void loadMaps() {
		int count = 0;
		
		for (count = 1; count < mapAmount + 1; count++) {
			this.loadMap(count);
		}		
	}
	
	/**
	 * Retrieves the map with the given id.
	 * @param id The number map to be returned
	 * @return The WorldMap loaded
	 */
	public WorldMap getMap(int id) {
		if ((id > 0) && (id <= mapAmount)) {
			return mapList.get(id - 1);
		}
		return null;
	}
	
	/**
	 * Loads the given map from the map path.
	 * @param map The number map to be loaded
	 * @return The WorldMap loaded
	 */
	private WorldMap loadMap(int map) {
		// TODO Auto-generated method stub
		
		byte flag;
		int x, y;
		RandomAccessFile dataInf = null;
		RandomAccessFile dataMap = null;
		byte[] bufInf = null;
		byte[] bufMap = null;
				
		try {
			dataInf = new RandomAccessFile(mapPath + "mapa" + map + INF_EXTENSION, "r");
			dataMap = new RandomAccessFile(mapPath + "mapa" + map + MAP_EXTENSION, "r");
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
		
		
		bufferInf.order(ByteOrder.LITTLE_ENDIAN);
		bufferMap.order(ByteOrder.LITTLE_ENDIAN);

		
		// Get the map header
		short mapVersion = bufferMap.getShort(); 

		byte[] description = new byte[255];
		bufferMap.get(description);
		
		int CRC = bufferMap.getInt();
		int magicWord = bufferMap.getInt();	
		
		bufferMap.getShort();
		bufferMap.getShort();
		bufferMap.getShort();
		bufferMap.getShort();
		
		// .INF Header 
		bufferInf.getShort();
		bufferInf.getShort();
		bufferInf.getShort();
		bufferInf.getShort();
		bufferInf.getShort();

		Tile[] Tiles = new Tile[WorldMap.MAP_HEIGHT * WorldMap.MAP_WIDTH];
		
		for (y = 0; y < WorldMap.MAP_HEIGHT; y++ )  {
			for (x = 0; x < WorldMap.MAP_HEIGHT; x++) {

				boolean blocked = false;
				short trigger = 0;
				Position tileExit = null;
				
				flag = bufferMap.get();

				//If the frist bitflag is seted then, the tile is blocked.
				if ((flag & 1) == 1) {
					blocked = true;
				}
				
				short[] layers = new short[Tile.LAYERS];

				//Every tile must have the frist layer.
				layers[0] = bufferMap.getShort();

				if ((flag & 2) == 2) {
					layers[1] = bufferMap.getShort();
				}
				
				if ((flag & 4) == 4) {
					layers[2] = bufferMap.getShort();
				}

				if ((flag & 8) == 8) {
					layers[3] = bufferMap.getShort();
				}

				if ((flag & 16) == 16) {
					trigger = bufferMap.getShort();
				}

				flag = bufferInf.get();

				if ((flag & 1) == 1) {
					tileExit = new Position(
											(byte) bufferInf.getShort(), 
											(byte) bufferInf.getShort(), 
											getMap(bufferInf.getShort())
											);
				}	

				if ((flag & 2) == 2) {
					bufferInf.getShort();					
					//TODO Create the NPCharacter implementation
				}
				
				if ((flag & 4) == 4) {
					bufferInf.getShort();
					bufferInf.getShort();					
					//TODO Create the WorldObject implementation.				
				}
				
				//TODO Replace the nulls with the NPCCharacter and WorldObject objects.
				Tiles[WorldMap.getTileKey(x, y)] = new Tile(blocked, layers, trigger, tileExit, null, null);

			}
			
		}
		
		this.getMap(map).setTiles(Tiles);
		
		return this.getMap(map);
	}

}
