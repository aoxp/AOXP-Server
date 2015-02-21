/*
 * AO-XP Server (XP stands for Cross Platform) is a Java implementation of
 * Argentum Online's server Copyright (C) 2009 Juan Martín Sotuyo Dodero.
 * <juansotuyo@gmail.com>
 *
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package com.ao.service.map;

import com.ao.action.ActionExecutor;
import com.ao.data.dao.CityDAO;
import com.ao.data.dao.WorldMapDAO;
import com.ao.model.character.Character;
import com.ao.model.map.City;
import com.ao.model.map.Heading;
import com.ao.model.map.Position;
import com.ao.model.map.WorldMap;
import com.ao.service.MapService;
import com.google.inject.Inject;

/**
 * Concrete implementation of MapService.
 */
public class MapServiceImpl extends ActionExecutor<MapService>
		implements MapService {
	private final WorldMapDAO mapsDAO;
	private WorldMap[] maps;

	private final CityDAO citiesDAO;
	private City[] cities;

	private AreaServiceImpl areaService;

	@Inject
	public MapServiceImpl(final WorldMapDAO mapsDAO, final CityDAO citiesDAO, final AreaServiceImpl areaService) {
		this.mapsDAO = mapsDAO;
		this.citiesDAO = citiesDAO;
		this.areaService = areaService;
	}

	@Override
	public void loadMaps() {
		maps = mapsDAO.retrieveAll();
	}

	@Override
	public WorldMap getMap(final int id) {
		if (id < 1 || id > maps.length) {
			return null;
		}

		// Maps enumeration starts at 1, not 0.
		return maps[id - 1];
	}

	@Override
	protected MapService getService() {
		return this;
	}

	@Override
	public void loadCities() {
		cities = citiesDAO.retrieveAll();
	}

	@Override
	public City getCity(final byte id) {
		if (id < 1 || id > cities.length) {
			return null;
		}

		// Cities enumeration starts at 1, not 0.
		return cities[id - 1];
	}

	@Override
	public void putCharacterAtPos(final Character chara, final Position pos) {
		final WorldMap map = getMap(pos.getMap());
		map.putCharacterAtPos(chara,pos.getX(), pos.getY());
		areaService.addCharToMap(map, chara);
	}

	@Override
	public void moveCharacterTo(final Character character, final Heading heading) {
		final Position position = character.getPosition();
		byte x = position.getX();
		byte y = position.getY();

		switch (heading) {
		case NORTH:
			y++;
			break;

		case EAST:
			x++;
			break;

		case SOUTH:
			y--;
			break;

		case WEST:
			x--;
			break;
		}

		final WorldMap map = getMap(position.getMap());

		// Check if the position is available
		if (map.isTileAvailable(x, y, !character.isSailing(), character.isSailing(), true, true)) {
			// TODO : In newer version of the game, you can push ghosts and invisible admins
			// Update the map
			map.moveCharacterTo(character, x, y);

			// Update the character
			position.setX(x);
			position.setY(y);

			// TODO : Check tile events

			// TODO : Check & notify area change
		}
	}
}