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

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Test;

import com.ao.data.dao.WorldMapDAO;
import com.ao.data.dao.CityDAO;
import com.ao.model.map.WorldMap;
import com.ao.model.map.City;
import com.ao.service.MapService;

public class MapServiceImplTest {

	@Test
	public void testLoadMaps() {
		WorldMapDAO dao = EasyMock.createMock(WorldMapDAO.class);
		CityDAO cityDao = EasyMock.createMock(CityDAO.class);
		EasyMock.expect(dao.retrieveAll()).andReturn(null).once();
		EasyMock.replay(dao);
		
		
		
		MapService service = new MapServiceImpl(dao, cityDao);
		service.loadMaps();
		
		EasyMock.verify(dao);
	}

	@Test
	public void testGetMap() {
		int mapId = 1;
		
		WorldMap map = new WorldMap(mapId);
		WorldMapDAO dao = EasyMock.createMock(WorldMapDAO.class);
		CityDAO cityDao = EasyMock.createMock(CityDAO.class);
		
		EasyMock.expect(dao.retrieveAll()).andReturn(new WorldMap[] {map}).once();
		EasyMock.replay(dao);
		
		MapService service = new MapServiceImpl(dao,cityDao);
		service.loadMaps();
		
		Assert.assertEquals(service.getMap(mapId), map);
		
	}

}
