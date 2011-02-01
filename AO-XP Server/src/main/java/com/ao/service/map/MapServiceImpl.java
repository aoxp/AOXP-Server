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

import com.ao.data.dao.CityDAO;
import com.ao.data.dao.WorldMapDAO;
import com.ao.model.character.Character;
import com.ao.model.map.City;
import com.ao.model.map.Position;
import com.ao.model.map.WorldMap;
import com.ao.service.MapService;
import com.google.inject.Inject;

/**
 * Concrete implementation of MapService.
 */
public class MapServiceImpl implements MapService {

	private WorldMapDAO mapsDAO;
	private WorldMap[] maps;
	
	private CityDAO citiesDAO;
	private City[] cities;
	
	@Inject
	public MapServiceImpl(WorldMapDAO mapsDAO, CityDAO citiesDAO) {
		this.mapsDAO = mapsDAO;
		this.citiesDAO = citiesDAO;
	}
	
	@Override
	public void loadMaps() {
		maps = mapsDAO.retrieveAll();
	}
	
	@Override
	public WorldMap getMap(int id) {
		if (id < 1 || id > maps.length) {
			return null;
		}
		
		// Maps enumeration starts at 1, not 0.
		return maps[id - 1];
	}
	
	@Override
	public void loadCities() {
		cities = citiesDAO.retrieveAll();
	}
	
	@Override
	public City getCity(byte id) {
		if (id < 1 || id > cities.length) {
			return null;
		}
		
		// Cities enumeration starts at 1, not 0.
		return cities[id - 1];
	}
	
	@Override
	public void putCharacterAtPos(Character chara, Position pos) {
		pos.getMap().putCharacterAtPos(chara, pos.getX(), pos.getY());
	}
	
}