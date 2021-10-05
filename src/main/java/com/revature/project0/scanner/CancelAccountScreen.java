package com.revature.project0.scanner;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class CancelAccountScreen implements Screen {
	private User user;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public CancelAccountScreen() {
	}
	public CancelAccountScreen(User user) {
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		List <Account> accountsList = sLoader.getAccountService().getAllAccounts();
		System.out.println("\nWelcome to Cansel Account Interface");
		System.out.println("To get list of all accounts enter '1'");
		System.out.println("To cansel account enter '2'");
		System.out.println("For exit to previus screen enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		
		switch (enter) {
		case "0":
			new AdminScreen(user).render(conScan);
			break;
			
		case "1":
			for (Account acc : accountsList) {
				System.out.println(acc);
			}
			new CancelAccountScreen(user).render(conScan);
 			break;
			
		case "2":
			System.out.println("Enter valid account id:");
			int id = Integer.parseInt(conScan.nextLine());
			for (Account acc : accountsList) {
				if (acc.getAccountId() == id) {
					sLoader.getAccountService().deleteAccount(acc);
					new CancelAccountScreen(user).render(conScan);
				}
			}
			log.callErrorLogger("Account not found");
			new CancelAccountScreen(user).render(conScan);
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			new CancelAccountScreen(user).render(conScan);
			break;
		}
	}
	

}
