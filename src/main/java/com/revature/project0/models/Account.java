package com.revature.project0.models;

public class Account {
	private int accountId;
	private float balance;
	private int userId;
	private boolean isApproved;
	
	public Account() {
	}
	
	public Account(float balance, int userId, boolean isApproved) {
		this.balance = balance;
		this.userId = userId;
		this.isApproved = isApproved;
	}

	public Account(int accountId, float balance, int userId) {
		this.accountId = accountId;
		this.balance = balance;
		this.userId = userId;
	}
	
	public Account(int accountId, float balance, int userId, boolean isApproved) {
		this.accountId = accountId;
		this.balance = balance;
		this.userId = userId;
		this.isApproved = isApproved;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}
	

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	

	@Override
	public String toString() {
		String result = "Account id: " + this.accountId + " , Account Balance is: " + this.balance + " , Account status: ";
		if(this.isApproved) {
			result += "approved";
		} else {
			result += "not approved";
		}
		return result;
	}
	
}
