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

package com.ao.data.dao.map;

import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ao.data.dao.WorldMapDAO;
import com.ao.model.map.Position;
import com.ao.model.map.Tile;
import com.ao.model.map.Trigger;
import com.ao.model.map.WorldMap;
import com.ao.utils.RangeParser;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public class WorldMapDAOImpl implements WorldMapDAO {

	private Logger logger = Logger.getLogger(WorldMapDAOImpl.class);
	
	private static final String MAP_FILE_NAME_FORMAT = "Mapa%d.map";
	private static final String INF_FILE_NAME_FORMAT = "Mapa%d.inf";
	
	private static final byte BITFLAG_BLOCKED = 1;
	private static final byte BITFLAG_LAYER2 = 2;
	private static final byte BITFLAG_LAYER3 = 4;
	private static final byte BITFLAG_LAYER4 = 8;
	private static final byte BITFLAG_TRIGGER = 16;
	
	private static final byte BITFLAG_TILEEXIT = 1;
	private static final byte BITFLAG_NPC = 2;
	private static final byte BITFLAG_OBJECT = 4;
	
	private int mapsAmount;
	private String mapsPath;
	private Set<Short> waterGrhs = new HashSet<Short>();
	private Set<Short> lavaGrhs = new HashSet<Short>();
	
	@Inject
	public WorldMapDAOImpl(@Named("mapsPath") String mapsPath, @Named("mapsAmount") int mapsAmount, @Named("mapsConfigFile") String mapsConfigFile) {
		this.mapsPath = mapsPath;
		this.mapsAmount = mapsAmount;
		
		loadMapsConfig(mapsConfigFile);
	}
	
	@Override
	public WorldMap[] retrieveAll() {
		WorldMap[] maps = new WorldMap[mapsAmount];
		
		// Maps enumeration starts at 1.
		for (int i = 1; i <= mapsAmount; i++) {
			// Initialize all maps empty, this way references to not yet loaded maps can be correctly handled.
			maps[i - 1] = new WorldMap(i);
		}
		
		for (int i = 1; i <= mapsAmount; i++) {
			loadMap(i, maps);
		}
		
		return maps;
		
	}

	/**
	 * Loads the map with the given id in the given map list.
	 * @param id The map's id.
	 * @param maps The list of maps where the map will be loaded. Also this list
	 * 				must contain all maps pre-loaded to handle inter-maps references.
	 */
	private void loadMap(int id, WorldMap[] maps) {
		byte flag;
		RandomAccessFile dataInf;
		RandomAccessFile dataMap;
		byte[] bufInf;
		byte[] bufMap;
		
		try {
			// Completely read both files.
			dataInf = new RandomAccessFile(mapsPath + String.format(INF_FILE_NAME_FORMAT, id), "r");
			dataMap = new RandomAccessFile(mapsPath + String.format(MAP_FILE_NAME_FORMAT, id), "r");
			
			bufInf = new byte[(int) dataInf.length()];
			bufMap = new byte[(int) dataMap.length()];
			
			dataInf.readFully(bufInf);		
			dataMap.readFully(bufMap);
		} catch (IOException e) {
			logger.error("Map " + id + " loading failed!", e);
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
		
		// Unused header value.
		mapBuffer.getLong();
		
		// Unused header values.
		infBuffer.getLong();
		infBuffer.getShort();
		
		Tile[] tiles = new Tile[WorldMap.MAP_HEIGHT * WorldMap.MAP_WIDTH];

		boolean blocked;
		boolean isWater;
		boolean isLava;
		
		short triggerIndex;
		Trigger trigger;
		Position tileExit;
		short floor;
		
		// Tiles enumeration starts at 1.
		for (int y = WorldMap.MIN_Y; y < WorldMap.MAX_Y; y++) {
			for (int x = WorldMap.MIN_X; x < WorldMap.MAX_X; x++) {

				blocked = isWater = isLava = false;
				trigger = Trigger.NONE;
				tileExit = null;
				
				flag = mapBuffer.get();

				// If the first bitflag is set the tile is blocked.
				if ((flag & BITFLAG_BLOCKED) == BITFLAG_BLOCKED) {
					blocked = true;
				}
				
				// Every tile must have the first layer.
				floor = mapBuffer.getShort();
				
				
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
					
					triggerIndex = mapBuffer.getShort();
					
					try {
						trigger = Trigger.get(triggerIndex);
					} catch (ArrayIndexOutOfBoundsException e) {
						logger.warn(String.format("The position (%d, %d, %d) has an invalid trigger: %d.", id, x, y, triggerIndex));
						trigger = Trigger.NONE;
					}
				}

				flag = infBuffer.get();

				// The tile takes you to another position.
				if ((flag & BITFLAG_TILEEXIT) == BITFLAG_TILEEXIT) {
					short toMap = infBuffer.getShort();
					
					byte toX = (byte) infBuffer.getShort();
					byte toY = (byte) infBuffer.getShort();
					
					
					if (toMap < 1 || toMap > maps.length) {
						logger.error(String.format("The position (%d, %d, %d) has an invalid tile exit to a non-existant map (%d). Omitting.", id, x, y, toMap));
					} else {
						tileExit = new Position(toX, toY, maps[toMap - 1]);
					}
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
		
		// Fill the map with the loaded data.
		maps[id - 1].setTiles(tiles);
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
			logger .fatal("Error loading maps properties file(" + configFile + ")");
			throw new RuntimeException(e);
		}
		
		waterGrhs.addAll(RangeParser.parseShorts(props.getProperty("maps.water")));
		lavaGrhs.addAll(RangeParser.parseShorts(props.getProperty("maps.lava")));
	}
	
}
