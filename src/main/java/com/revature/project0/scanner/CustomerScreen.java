package com.revature.project0.scanner;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class CustomerScreen implements Screen{
	private User user;
	
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public CustomerScreen() {
	}


	public CustomerScreen(User user) {
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		List<Account> accountList = sLoader.getAccountService().getAccountByUserID(user.getUserId()); 
		
		System.out.println("\nWelcome to Customer Interface");
		System.out.println("For applying for bank account enter '1'");
		System.out.println("For applying for joint bank account enter '2'");
		System.out.println("For checking your accounts status enter '3'");
		System.out.println("For operatioins with your account enter '4'");
		System.out.println("For exit enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		
		String enter =  conScan.nextLine();
		
		switch (enter) {
		case "0":
			//MainDriver.isRunning = false;
			System.exit(1);
			return;
			
		case "1":
			Account account = new Account((float) 0, user.getUserId(), false);
			account = sLoader.getAccountService().insertAccount(account);
			log.callInfoLogger("Bank account was created with id: " + account.getAccountId());
			break;
			
		case "2":
			System.out.println("Enter yours account id you want to make joint:");
			int id = Integer.parseInt(conScan.nextLine());
			for(Account acc : accountList) {
				if (acc.getAccountId() == id) {
					System.out.println("Enter valid user name of person ytou want to make joint:");
					String name = conScan.nextLine();
					User jointUser = new User();
					try {
						jointUser = sLoader.getSimpleUserService().getUserByName(name);
						if (jointUser.getRoleId() != 3) {
							throw new NullPointerException();
						}
					} catch (Exception e) {
						log.callExceptionLogger(e);
						log.callErrorLogger("There is no such user name in system");
						new CustomerScreen(user).render(conScan);
					}
					sLoader.getAccountService().setJointAccount(acc.getAccountId(), jointUser.getUserId());
					
					
				}
			}
			log.callErrorLogger("Account wasn't found");
			break;
			
		case "3":
			for(Account acc : accountList) {
				System.out.println(acc);
			}
			break;
			
		case "4":
			System.out.println("Enter approved bank account id:");
			int accountId = Integer.parseInt(conScan.nextLine());
			
			List<Account> accList = sLoader.getAccountService().getAccountByUserID(user.getUserId());
			
			for(Account acc : accList) {
				if (acc.getAccountId() == accountId && acc.isApproved()) {
					new AccountOperationScreen(acc, user).render(conScan);
				}
			}
			
			log.callErrorLogger("There no such account with id: " + accountId + " , or it doesn't approved.");
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			break;
		}
		
		new CustomerScreen(user).render(conScan);
	}
	

}
