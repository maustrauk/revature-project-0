package com.revature.project0.services;

import java.util.List;

import com.revature.project0.daos.UserDaoImpl;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class UserService {
	private UserDaoImpl uDao;
	private AccountService aServ;

	public UserService() {
	}
	
	public UserService(UserDaoImpl uDao) {
		this.uDao = uDao;
	}
	
	public UserService(UserDaoImpl uDao, AccountService aServ) {
		this.uDao = uDao;
		this.aServ = aServ;
	}
	
	public List<User> getAllUsers() {
		return uDao.getAll();
	}
	
	public User getUserByName(String name) {
		if (uDao.getByName(name) == null) {
			throw new NullPointerException("There isn't a user with name: " + name);
		}
		return uDao.getByName(name);
	}
	
	public User getUserById(int id) {
		User user = uDao.getById(id);
		if (user.getUserName() == null) {
			throw new NullPointerException("There isn't a user with id: " + id);
		}
		return user;
	}
	
	private void accountListUpdate(List<Account> accountList) {
		for(Account account: accountList) {
			try {
				aServ.insertAccount(account);
			} catch(NullPointerException e) {
				aServ.updateAccount(account);
			}
		}
	}
	
	public void updateUser(User user) {
		if (uDao.getById(user.getUserId()) == null) {
			throw new NullPointerException("There isn't a user with id: " + user.getUserId());
		}
		if (uDao.getByName(user.getUserName()) != null) {
			throw new NullPointerException("There is a user with username: " + user.getUserName());
		}
		uDao.update(user);
		accountListUpdate(user.getAccountList());
	}
	
	public User insertUser(User user) {
		User check = uDao.getByName(user.getUserName());
		User newUser = new User();
		if (check.getUserName() != null) {
			throw new NullPointerException("There is user with similar username: " + user.getUserName() + " in DB");
		}
		newUser = uDao.insert(user);
		if (user.getAccountList() != null) {
			accountListUpdate(user.getAccountList());
		}
		return newUser;
	}
	
	public void deleteUser(User user) {
		if (uDao.getById(user.getUserId()) == null) {
			throw new NullPointerException("There isn't a user with id: " + user.getUserId());
		}
		uDao.delete(user);
	}
	
	
}
