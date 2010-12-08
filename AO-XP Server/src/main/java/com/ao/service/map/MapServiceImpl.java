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

package com.ao.service.map;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ao.model.map.Position;
import com.ao.model.map.Tile;
import com.ao.model.map.Trigger;
import com.ao.model.map.WorldMap;
import com.ao.service.MapService;

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
	
	private static final String CONFIG_VALUES_DELIMITER = ",";
	private static final String CONFIG_RANGE_INDICATOR = "-";
	
	protected WorldMap[] maps;
	private String mapsPath;
	protected int mapsAmount;
	
	private Set<Short> waterGrhs = new HashSet<Short>();
	private Set<Short> lavaGrhs = new HashSet<Short>();
	
	@Inject
	public MapServiceImpl(@Named("mapsPath") String mapsPath, @Named("mapsAmount") int mapsAmount, @Named("mapsConfigFile") String mapsConfigFile) {

		this.mapsPath = mapsPath;
		this.mapsAmount = mapsAmount;
		this.maps = new WorldMap[mapsAmount];

		loadMapsConfig(mapsConfigFile);
		
		// Maps enumeration starts at 1, not 0.
		for (int i = 1; i <= mapsAmount; i++) {
			// Initialize all maps empty, this way references to not yet loaded maps can be correctly handled.
			maps[i - 1] = new WorldMap(i);
		}
	}
	
	@Override
	public void loadMaps() {
		
		// Maps enumeration starts at 1, not 0.
		for (int i = 1; i <= mapsAmount; i++) {
			loadMap(i);
		}		
	}
	
	@Override
	public WorldMap getMap(int id) {
		if (id > 0 && id <= mapsAmount) {
			return maps[id - 1];
		}
		
		return null;
	}
	
	/**
	 * Loads and retrieves the map with the given id.
	 * @param mapId The map's id.
	 * @return The loaded map.
	 */
	private WorldMap loadMap(int mapId) {
	
		byte flag;
		RandomAccessFile dataInf;
		RandomAccessFile dataMap;
		byte[] bufInf;
		byte[] bufMap;
				
		try {
			// Completely read both files.
			dataInf = new RandomAccessFile(mapsPath + MAP_FILE_NAME + mapId + INF_EXTENSION, "r");
			dataMap = new RandomAccessFile(mapsPath + MAP_FILE_NAME + mapId + MAP_EXTENSION, "r");
			
			bufInf = new byte[(int) dataInf.length()];
			bufMap = new byte[(int) dataMap.length()];
			
			dataInf.readFully(bufInf);		
			dataMap.readFully(bufMap);
		} catch (IOException e) {
			logger.error("Map " + mapId + " loading failed!", e);
			throw new RuntimeException(e);
		}
		
		ByteBuffer infBuffer = ByteBuffer.wrap(bufInf);
		ByteBuffer mapBuffer = ByteBuffer.wrap(bufMap);
		
		// The map files are written with Little Endian and the default byte order in Java is Big Endian.
		infBuffer.order(ByteOrder.LITTLE_ENDIAN);
		mapBuffer.order(ByteOrder.LITTLE_ENDIAN);

		// Load the map header.
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
		boolean isWater;
		boolean isLava;
		
		Trigger trigger;
		Position tileExit;
		
		for (int y = 0; y < WorldMap.MAP_HEIGHT; y++) {
			for (int x = 0; x < WorldMap.MAP_HEIGHT; x++) {

				blocked = isWater = isLava = false;
				trigger = Trigger.NONE;
				tileExit = null;
				
				flag = mapBuffer.get();

				// If the first bitflag is set the tile is blocked.
				if ((flag & BITFLAG_BLOCKED) == BITFLAG_BLOCKED) {
					blocked = true;
				}
				
				// Every tile must have the first layer.
				short floor = mapBuffer.getShort();
				
				
				// In this layer goes stuff that should appear over the floor.
				if ((flag & BITFLAG_LAYER2) == BITFLAG_LAYER2) {
					// Remove the short from the buffer so we can fetch the next value.
					mapBuffer.getShort();
				} else {
					
					// Are we on water?
					if (waterGrhs.contains(floor)) {
						isWater = true;
					} else if (lavaGrhs.contains(floor)) { // Are we on lava?
						isLava = true;
					}
				}
				
				// In this layer goes stuff that is over the chars but is not a roof.
				if ((flag & BITFLAG_LAYER3) == BITFLAG_LAYER3) {
					// Remove the short from the buffer so we can fetch the next value.
					mapBuffer.getShort();
				}
				
				// This layer determines the roof, if any.
				if ((flag & BITFLAG_LAYER4) == BITFLAG_LAYER4) {
					/*
					 * Don't really care, whether the tile has roof or not is determined by the trigger.
					 * Remove the short from the buffer so we can fetch the next value.
					 */
					mapBuffer.getShort();
				}

				if ((flag & BITFLAG_TRIGGER) == BITFLAG_TRIGGER) {
					
					try {
						trigger = Trigger.get(mapBuffer.getShort());
					} catch (ArrayIndexOutOfBoundsException e) {
						logger.warn(String.format("The position (%d, %d, %d) has an invalid trigger.", mapId, x, y));
						trigger = Trigger.NONE;
					}
				}

				flag = infBuffer.get();

				// The tile takes you to another position.
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
				tiles[WorldMap.getTileKey(x, y)] = new Tile(blocked, isWater, isLava, trigger, tileExit, null, null);
		
				}
				
		}
		
		// The map object already exists, we only need to set it the loaded data.
		WorldMap map = getMap(mapId);
		map.setTiles(tiles);
		
		return map;
	}

	/**
	 * Loads the maps configuration from the given file.
	 * @param configFile The file path to the config file.
	 */
	private void loadMapsConfig(String configFile) {
		Properties props = new Properties();
		
		try {
			props.load(new FileReader(configFile));
		} catch (IOException e) {
			logger.fatal("Error loading maps properties file(" + configFile + ")");
			throw new RuntimeException(e);
		}
		
		waterGrhs.addAll(parseRanges(props.getProperty("maps.water")));
		lavaGrhs.addAll(parseRanges(props.getProperty("maps.lava")));
	}
	
	/**
	 * Parses a comma-separated list of numbers and retrieves it.
	 * The list also can contain numerical ranges indicated with dashes which also
	 * separates the starting and ending number of the numerical range, both inclusive.
	 * e.g:
	 * The following string: "1,2,3-5,6-9,10" would produce the following return:
	 * [1, 2, 3, 4, 5, 6, 7, 8, 9]
	 * @param value The string value to be parsed.
	 * @return The list of numbers extracted from the list.
	 */
	private List<Short> parseRanges(String value) {
		String[] splittedValues = value.split(CONFIG_VALUES_DELIMITER);
		List<Short> ret = new ArrayList<Short>();
		
		for(String val : splittedValues) {
			if (val.contains(CONFIG_RANGE_INDICATOR)) {
				String[] rangePoints = val.split(CONFIG_RANGE_INDICATOR);
				short from = Short.valueOf(rangePoints[0]);
				short to = Short.valueOf(rangePoints[1]);
				
				for (short i = from; i <= to; i++) {
					ret.add(i);
				}
				
			} else {
				ret.add(Short.valueOf(val));
			}
		}
		
		return ret;
	}
	
}