package ao.data.dao;

import ao.model.user.Account;

public interface AccountDAO {

	Account read(String username);
}
