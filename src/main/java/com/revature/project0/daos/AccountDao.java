package com.revature.project0.daos;


import java.util.List;

import com.revature.project0.models.Account;


public interface AccountDao extends GenericDao<Account> {
	void insertJunction(int accountId, int userId);
	List<Account> getAccountsByUserID(int id);
	List<Account> getJointByUserID(int id);
}
