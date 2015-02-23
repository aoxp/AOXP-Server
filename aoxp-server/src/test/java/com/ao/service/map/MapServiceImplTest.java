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

import static org.junit.Assert.assertSame;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;

import com.ao.data.dao.CityDAO;
import com.ao.data.dao.WorldMapDAO;
import com.ao.model.map.Tile;
import com.ao.model.map.WorldMap;
import com.ao.service.MapService;

public class MapServiceImplTest {

	@Test
	public void testLoadMaps() {
		final WorldMapDAO dao = mock(WorldMapDAO.class);
		final CityDAO cityDao = mock(CityDAO.class);
		final AreaService areaService = mock(AreaService.class);
		final MapService service = new MapServiceImpl(dao, cityDao, areaService);
		service.loadMaps();
	}

	@Test
	public void testGetMap() {
		final int mapId = 1;
		final WorldMap map = new WorldMap(null, mapId, (short) 1, new Tile[] {});
		final WorldMapDAO dao = mock(WorldMapDAO.class);
		final CityDAO cityDao = mock(CityDAO.class);
		final AreaService areaService = mock(AreaService.class);

		when(dao.retrieveAll()).thenReturn(new WorldMap[] {map});

		final MapService service = new MapServiceImpl(dao, cityDao, areaService);
		service.loadMaps();

		assertSame(map, service.getMap(mapId));
	}

}
