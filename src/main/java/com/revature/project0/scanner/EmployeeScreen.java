package com.revature.project0.scanner;

import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.MainDriver;
import com.revature.project0.models.User;

public class EmployeeScreen implements Screen{
	
	private User user;
	private final LogHelper log = new LogHelper();

	public EmployeeScreen() {
	}

	public EmployeeScreen(User user) {
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		System.out.println("\nWelcome to Employee Interface");
		System.out.println("To view customer information enter '1'");
		System.out.println("To approve/deny customer accounts enter '2'");
		System.out.println("For exit enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		switch (enter) {
		case "0":
			MainDriver.isRunning = false;
			return;
			
		case "1":
			new CustomerInformationScreen(user).render(conScan);
			break;
			
		case "2":
			new ApprovalAccountScreen(user).render(conScan);
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			new EmployeeScreen(user).render(conScan);
			break;
		}
	}
	
	
	
}
