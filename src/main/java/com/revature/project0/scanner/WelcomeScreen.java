package com.revature.project0.scanner;

import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.MainDriver;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.User;

public class WelcomeScreen implements Screen{
	private User user;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public WelcomeScreen() {
	}

	public WelcomeScreen(User user) {
		this.user = user;
	}

	@Override
	public void render(Scanner conScan) {
		System.out.println("\nWelcome to the Bank Application");
		System.out.println("Do you have user account? y/n");
		String existing =  conScan.nextLine();
		
		if(existing.toLowerCase().equals("y")) {
			user = loginToSystem(conScan);
			log.callInfoLogger("Succsesful login");
		} else if (existing.toLowerCase().equals("n")) {
			user = createUser(conScan);
			log.callInfoLogger("Succsesful created");
		} else {
			log.callWarningLogger("Wrong input");
			System.out.println("Try again\n");
			new WelcomeScreen().render(conScan);
		}
		
		
		switch (user.getRoleId()) {
		case 1:
			new AdminScreen(user).render(conScan);
			break;
		case 2:
			new EmployeeScreen(user).render(conScan);
			break;
		case 3:
			new CustomerScreen(user).render(conScan);
			break;
		default:
			MainDriver.isRunning = false;
			return;
		}
	}
	
	public User askForUserCredentials(Scanner conScan) {
		System.out.println("Enter username:");
		String userName = conScan.nextLine();
		System.out.println("Enter password:");
		String password = conScan.nextLine();
		
		User user = sLoader.getSimpleUserService().getUserByName(userName);
		
		if (!user.getUserPassword().equals(password)  ) {
			throw new NullPointerException();
		}
		
		return user;
	}
	
	public User loginToSystem(Scanner conScan) {
		User user = new User();
		while(true) {
			try {
				user = askForUserCredentials(conScan);
				break;
			} catch (NullPointerException e) {
				log.callExceptionLogger(e);
				log.callErrorLogger("There is no such username or username doesn't match password");
				System.out.println("Try again");
			}
		}
		return user;
	}
	
	public User createUser(Scanner conScan) {
		User user = new User();
		User newUser = new User();
		
		while(true) {
			try {
				System.out.println("Enter new username:");
				String userName = conScan.nextLine();
				System.out.println("Enter new password:");
				String password = conScan.nextLine();
				user = new User(userName, password, 3);
				newUser = sLoader.getFullUserService().insertUser(user);
				break;
			}  catch (NullPointerException e) {
				log.callExceptionLogger(e);
				log.callErrorLogger("This username aleady exist");
				System.out.println("Try again");
			}
		}
		return newUser;
	}

}
