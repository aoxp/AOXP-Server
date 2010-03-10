package ao.service.worldobject;

import ao.data.dao.WorldObjectPropertiesDAO;
import ao.data.dao.exception.DAOException;
import ao.model.worldobject.properties.WorldObjectProperties;
import ao.service.WorldObjectService;

import com.google.inject.Inject;

/**
 * Default implementation of WorldObjectService.
 */
public class WorldObjectServiceImpl implements WorldObjectService {

	protected WorldObjectPropertiesDAO woPropertiesDao;
	
	protected WorldObjectProperties[] objectProperties;
	
	/**
	 * Creates a new WorldObjectServiceImpl instance.
	 * @param woPropertiesDao The world object properties dao to use.
	 */
	@Inject
	public WorldObjectServiceImpl(WorldObjectPropertiesDAO woPropertiesDao) {
		super();
		this.woPropertiesDao = woPropertiesDao;
	}

	/*
	 * (non-Javadoc)
	 * @see ao.service.WorldObjectService#loadObjects()
	 */
	@Override
	public void loadObjects() throws DAOException {
		objectProperties = woPropertiesDao.retrieveAll();
	}
}
