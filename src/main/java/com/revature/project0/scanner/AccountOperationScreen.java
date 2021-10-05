package com.revature.project0.scanner;

import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class AccountOperationScreen implements Screen{
	
	private Account account;
	private User user;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public AccountOperationScreen() {
	}
	
	public AccountOperationScreen(Account account, User user) {
		this.account = account;
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		
		System.out.println("\nWelcome to Operation Interface");
		System.out.println("For making withdraw enter '1'");
		System.out.println("For making deposit enter '2'");
		System.out.println("For making transfer enter '3'");
		System.out.println("For exit to prev window enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		switch (enter) {
		case "0":
			if (user.getRoleId() == 3) {
				new CustomerScreen(user).render(conScan);
			} else if(user.getRoleId() == 2) {
				new EmployeeScreen(user).render(conScan);
			} else {
				new AdminScreen(user).render(conScan);
			}
			break;
			
		case "1":
			while(true) {
				try {
					setWithdraw(conScan);
					break;
				} catch (Exception e) {
					log.callExceptionLogger(e);
					log.callErrorLogger("Not enougth funds on your balance or you enter not valid amount");
					System.out.println("Try again");
				}
			}
			new AccountOperationScreen(account, user).render(conScan);
			break;
			
		case "2":
			while(true) {
				try {
					setDeposit(conScan);
					break;
				} catch (Exception e) {
					log.callExceptionLogger(e);
					log.callErrorLogger("Enter valid amount");
					System.out.println("Try again");
				}
			}
			new AccountOperationScreen(account, user).render(conScan);
			break;
			
		case "3":
			while(true) {
				try {
					setTransfer(conScan);
					break;
				} catch (Exception e) {
					log.callExceptionLogger(e);
					log.callErrorLogger("Enter valid amount or valid account id");
					System.out.println("Try again");
				}
			}
			break;
			
		default:
			log.callWarningLogger("Wrong input");
			System.out.println("Try again");
			new AccountOperationScreen(account, user).render(conScan);
			break;
		}
		
		new AccountOperationScreen(account, user).render(conScan);
	}
	
	public void setDeposit(Scanner conScan) {
		System.out.println("Enter deposit amount:");
		float deposit = Float.parseFloat(conScan.nextLine());
		
		if (deposit <= 0) {
			throw new NullPointerException();
		}
		
		account.setBalance(account.getBalance() + deposit);
		this.account = sLoader.uploadAccountToDB(account);
		String info = "Deposit is successful!\n";
		info += "Account info after operation: " + account + "\n";
		log.callInfoLogger(info);
	}
	
	public void setWithdraw(Scanner conScan) {
		System.out.println("Enter withdraw amount:");
		float withdraw = Float.parseFloat(conScan.nextLine());
		
		if (withdraw <= 0 || account.getBalance() < withdraw) {
			throw new NullPointerException();
		}
		account.setBalance(account.getBalance() - withdraw);
		this.account = sLoader.uploadAccountToDB(account);
		String info = "Withdraw is successful!\n";
		info += "Account info after operation: " + account + "\n";
		log.callInfoLogger(info);
	}
	
	public void setTransfer(Scanner conScan) {
		System.out.println("Enter account id where you want to transfer your funds:");
		int id = Integer.parseInt(conScan.nextLine());
		
		Account target = sLoader.getAccountService().getByAccountID(id);
		
		if (!target.isApproved()) {
			throw new NullPointerException();
		}
		
		System.out.println("Enter transfer amount:");
		float transfer = Float.parseFloat(conScan.nextLine());
		
		if (transfer <= 0 || account.getBalance() < transfer) {
			throw new NullPointerException();
		}
		
		account.setBalance(account.getBalance() - transfer);
		target.setBalance(target.getBalance() + transfer);
		this.account = sLoader.uploadAccountToDB(account);
		target = sLoader.uploadAccountToDB(target);
		String info = "Transfer is successful!\n";
		info += "Account_1 info after operation: " + account + "\n";
		info += "Account_2 info after operation: " + target + "\n";
		log.callInfoLogger(info);
	}

}
