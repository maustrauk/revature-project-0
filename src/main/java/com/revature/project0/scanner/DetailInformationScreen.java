package com.revature.project0.scanner;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.models.Account;
import com.revature.project0.models.User;

public class DetailInformationScreen implements Screen{
	private User user;
	private User customer;
	
	private final LogHelper log = new LogHelper();
	
	public DetailInformationScreen() {
	}

	public DetailInformationScreen(User user, User customer) {
		this.user = user;
		this.customer = customer;
	}



	@Override
	public void render(Scanner conScan) {
		List<Account> acountList = customer.getAccountList();
		
		System.out.println("\nWelcome to Customer Information Interface");
		System.out.println("If you want to check Customer(User) account information enter '1'");
		System.out.println("If you want to check Customer(User) account balance enter '2'");
		System.out.println("If you want to check Customer(User) personal information enter '3'");
		if (user.getRoleId() == 1) {
			System.out.println("If you want to change Customer(User) information enter '4'");
		}
		System.out.println("For exit to previus screen enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		switch (enter) {
		case "0":
			new CustomerInformationScreen(user).render(conScan);
			break;
			
		case "1":
			System.out.println("Customer have such accounts as:");
			for (Account acc : acountList) {
				String res = "Account id: " + acc.getAccountId() + " , approved status: ";
				if (acc.isApproved()) {
					res += "approved";
				} else {
					res += "not approved";
				}
				System.out.println(res);
			}
 			break;
			
		case "2":
			System.out.println("Enter valid customer account id:");
			boolean finded = false;
			int id = Integer.parseInt(conScan.nextLine());
			for (Account acc : acountList) {
				if (id == acc.getAccountId()) {
					System.out.println("Balance is: " + acc.getBalance());
					finded = true;
				}
			}
			if (!finded) {
				log.callErrorLogger("Account id wasn't found");
			}
			break;
			
		case "3":
			System.out.println("User id: " + customer.getUserId() + " , User Name: " + customer.getUserName() + " , User Password: " + customer.getUserPassword());
			break;
		case "4":
			if (user.getRoleId() == 2) {
				log.callErrorLogger("Wrong input!");
				System.out.println("Try again");
				new DetailInformationScreen(user, customer).render(conScan);
			} else {
				new EditInformationScreen(user, customer).render(conScan);
			}
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			break;
		}
		new DetailInformationScreen(user, customer).render(conScan);
	}

}
