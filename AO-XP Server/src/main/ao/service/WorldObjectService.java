package ao.service;

import ao.data.dao.exception.DAOException;

public interface WorldObjectService {

	/**
	 * Loads all objects.
	 * @throws DAOException
	 */
	void loadObjects() throws DAOException;
}
