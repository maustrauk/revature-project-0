package com.revature.project0.services;

import java.util.List;

import com.revature.project0.daos.AccountDaoImpl;
import com.revature.project0.models.Account;

public class AccountService {
	private AccountDaoImpl aDao;

	public AccountService() {
	}

	public AccountService(AccountDaoImpl aDao) {
		this.aDao = aDao;
	}
	
	public List<Account> getAllAccounts() {
		return aDao.getAll();
	}
	
	public Account insertAccount(Account account) {
		return aDao.insert(account);
	}
	
	public void updateAccount(Account account) {
		if (aDao.getById(account.getAccountId()) == null) {
			throw new NullPointerException("There is no such account with id: " + account.getAccountId() + " in DB");
		}
		aDao.update(account);
	}
	
	public void deleteAccount(Account account) {
		if (aDao.getById(account.getAccountId()) == null) {
			throw new NullPointerException("There is no such account with id: " + account.getAccountId() + " in DB");
		}
		aDao.delete(account);
	}
	
	public Account getByAccountID(int id) {
		if (aDao.getById(id) == null) {
			throw new NullPointerException("There is no such account with id: " + id + " in DB");
		}
		return aDao.getById(id);
	}
	
	public List<Account> getAccountByUserID(int id) {
		List<Account> list = aDao.getAccountsByUserID(id);
		for (Account acc : aDao.getJointByUserID(id)) {
			list.add(acc);
		}
		return list;
	}
	
	public void setJointAccount(int accountId, int addedUserId) {
		Account check = aDao.getById(accountId);
		if (check.getAccountId() == 0) {
			throw new NullPointerException("There is no such account with id: " + accountId + " in DB");
		}
		aDao.insertJunction(accountId, addedUserId);
	}
	
}
