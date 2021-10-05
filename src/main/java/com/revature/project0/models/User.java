package com.revature.project0.models;

import java.util.List;

public class User {
	
	private int userId;
	private String userName;
	private String userPassword;
	private int roleId;
	private List<Account> accountList;
	
	public User() {
	}

	public User(String userName, String userPassword, int roleId) {
		this.userName = userName;
		this.userPassword = userPassword;
		this.roleId = roleId;
	}
	
	public User(int userId, String userName, String userPassword, int roleId) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.roleId = roleId;
	}
	
	public User(int userId, String userName, String userPassword, int roleId, List<Account> accountList) {
		this.userId = userId;
		this.userName = userName;
		this.userPassword = userPassword;
		this.roleId = roleId;
		this.accountList = accountList;
	}

	public int getUserId() {
		return userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public List<Account> getAccountList() {
		return accountList;
	}

	public void setAccountList(List<Account> accountList) {
		this.accountList = accountList;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", userName=" + userName + ", userPassword=" + userPassword + ", roleId="
				+ roleId + ", accountList=" + accountList + "]";
	}
	
	
}
