package ao.data.dao;

import ao.model.user.Account;

public interface AccountDAO {

	Account retrieve(String username) throws DAOException;
}
