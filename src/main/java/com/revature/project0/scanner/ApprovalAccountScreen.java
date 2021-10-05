package com.revature.project0.scanner;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class ApprovalAccountScreen implements Screen{

	private User user;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public ApprovalAccountScreen() {
	}
	
	public ApprovalAccountScreen(User user) {
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		List<Account> accountsList = sLoader.getAccountService().getAllAccounts();
		
		System.out.println("\nWelcome to Approval/Deny account Screen");
		System.out.println("To get list of all accounts and their status enter '1'");
		System.out.println("To change account status enter '2'");
		System.out.println("For exit to previus screen enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		
		switch (enter) {
		case "0":
			if (user.getRoleId() == 2) {
				new EmployeeScreen(user).render(conScan);
			} else {
				new AdminScreen(user).render(conScan);
			}
			break;
			
		case "1":
			for (Account acc : accountsList) {
				System.out.println("Account id: " + acc.getAccountId() + " , Is account approved: " + acc.isApproved());
			}
			new ApprovalAccountScreen(user).render(conScan);
 			break;
			
		case "2":
			System.out.println("Enter valid account id:");
			int id = Integer.parseInt(conScan.nextLine());
			for(Account acc: accountsList) {
				if (id == acc.getAccountId()) {
					System.out.println("Do you want to change approval status: ");
					String change = conScan.nextLine();
					if (change.toLowerCase().equals("y")) {
						changeStatus(acc);
						new ApprovalAccountScreen(user).render(conScan);
					} else if (change.toLowerCase().equals("n")) {
						new ApprovalAccountScreen(user).render(conScan); 
					} else {
						log.callErrorLogger("Wrong input");
						new ApprovalAccountScreen(user).render(conScan);
					}
				}
			}
			log.callErrorLogger("Account wasn't found");
			new ApprovalAccountScreen(user).render(conScan);
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			new ApprovalAccountScreen(user).render(conScan);
			break;
		}
	}
	
	public void changeStatus (Account acc) {
		acc.setApproved(!acc.isApproved());
		sLoader.getAccountService().updateAccount(acc);
	}

}
