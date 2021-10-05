package com.revature.project0.scanner;

import java.util.List;
import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.User;

public class CustomerInformationScreen implements Screen{
	
	private User user;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public CustomerInformationScreen() {
	}

	public CustomerInformationScreen(User user) {
		this.user = user;
	}



	@Override
	public void render(Scanner conScan) {
		System.out.println("\nWelcome to Account Information Interface");
		System.out.println("To check all customer ids a system enter '1'");
		System.out.println("To check information by customer id enter '2'");
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
			List<User> userList = sLoader.getSimpleUserService().getAllUsers();
			
			System.out.println("List of ids:");
			
			for(User u : userList) {
				if (user.getRoleId() == 2) {
					if (u.getRoleId() == 3) {
						System.out.println(u.getUserId());
					}
				} else {
					System.out.println(u.getUserId());
				}
			}
			new CustomerInformationScreen(user).render(conScan);
 			break;
			
		case "2":
			User userEntity = new User();
			while(true) {
				try {
					System.out.println("Enter valid customer id:");
					int id = Integer.parseInt(conScan.nextLine());
					userEntity = sLoader.getSimpleUserService().getUserById(id);
					if (user.getRoleId() == 2 && userEntity.getRoleId() != 3) {
						throw new NullPointerException();
					}
					break;
					
				} catch (Exception e) {
					log.callExceptionLogger(e);
					log.callErrorLogger("Not valid id");
					System.out.println("Try again");
			}
			
			}
			new DetailInformationScreen(user, userEntity).render(conScan);
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			new CustomerInformationScreen(user).render(conScan);
			break;
		}
	}

	
}
