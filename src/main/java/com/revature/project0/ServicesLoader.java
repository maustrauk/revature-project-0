package com.revature.project0;

import com.revature.project0.daos.AccountDaoImpl;
import com.revature.project0.daos.BankDBConnection;
import com.revature.project0.daos.UserDaoImpl;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;
import com.revature.project0.services.AccountService;
import com.revature.project0.services.UserService;

public class ServicesLoader {
	private BankDBConnection con = new BankDBConnection();
	private UserDaoImpl uDao = new UserDaoImpl(con);
	private AccountDaoImpl aDao = new AccountDaoImpl(con);
	
	public UserService getSimpleUserService() {
		return new  UserService(uDao);
		
	}
	
	public UserService getFullUserService() {
		return new  UserService(uDao, this.getAccountService());
	}
	
	public AccountService getAccountService() {
		return new AccountService(aDao);
	}
	
	public Account uploadAccountToDB(Account acc) {
		getAccountService().updateAccount(acc);
		return getAccountService().getByAccountID(acc.getAccountId());
	}
	
	public User uploadUserToDb(User u) {
		 getFullUserService().updateUser(u);
		 return getFullUserService().getUserById(u.getUserId());
	}
}
