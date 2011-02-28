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

package com.ao.service.worldobject;

import com.ao.data.dao.WorldObjectPropertiesDAO;
import com.ao.data.dao.exception.DAOException;
import com.ao.model.worldobject.WorldObject;
import com.ao.model.worldobject.factory.WorldObjectFactory;
import com.ao.model.worldobject.factory.WorldObjectFactoryException;
import com.ao.service.WorldObjectService;
import com.google.inject.Inject;

/**
 * Default implementation of WorldObjectService.
 */
public class WorldObjectServiceImpl implements WorldObjectService {

	protected WorldObjectPropertiesDAO woPropertiesDao;

	protected WorldObjectFactory woFactory;

	/**
	 * Creates a new WorldObjectServiceImpl instance.
	 * @param woPropertiesDao The world object properties dao to use.
	 */
	@Inject
	public WorldObjectServiceImpl(WorldObjectPropertiesDAO woPropertiesDao,
		WorldObjectFactory woFactory) {
		super();
		this.woPropertiesDao = woPropertiesDao;
		this.woFactory = woFactory;
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.service.WorldObjectService#loadObjects()
	 */
	@Override
	public void loadObjects() throws DAOException {
		woPropertiesDao.loadAll();
	}

	/*
	 * (non-Javadoc)
	 * @see com.ao.service.WorldObjectService#createWorldObject(int, int)
	 */
	@Override
	public WorldObject createWorldObject(int id, int amount) throws WorldObjectFactoryException {
		return woFactory.getWorldObject(woPropertiesDao.getWorldObjectProperties(id), amount);
	}
}
