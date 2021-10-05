package com.revature.project0.scanner;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class AdminScreen implements Screen{
	private User user;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();

	public AdminScreen() {
	}
	
	public AdminScreen(User user) {
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		List<Account> accountList = sLoader.getAccountService().getAllAccounts();
		
		System.out.println("\nWelcome to Admin Screen");
		System.out.println("If you want to approve/deny accounts enter '1'");
		System.out.println("If you want to make operations on accounts enter '2'");
		System.out.println("If you want to cansel account enter '3'");
		System.out.println("If you want to view all accounts enter '4");
		System.out.println("If you want to view customer information enter '5'");
		System.out.println("For exit enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		switch (enter) {
		case "0":
			System.exit(1);
			return;
			
		case "1":
			new ApprovalAccountScreen(user).render(conScan);
			break;
			
		case "2":
			System.out.println("Enter approved bank account id:");
			int accountId = Integer.parseInt(conScan.nextLine());
			
			for (Account acc : accountList) {
				if (acc.getAccountId() == accountId && acc.isApproved()) {
					new AccountOperationScreen(acc, user).render(conScan);
				} 
			}
			log.callErrorLogger("There no such account with id: " + accountId + " , or it doesn't approved.");
			break;
		case "3":
			new CancelAccountScreen(user).render(conScan);
			break;
		case "4":
			System.out.println("Account list:");
			for (Account acc : accountList) {
				System.out.println(acc);
			}
			break;
		case "5":
			new CustomerInformationScreen(user).render(conScan);
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			break;
		}
		new AdminScreen(user).render(conScan);
	}

}
