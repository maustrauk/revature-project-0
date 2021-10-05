package com.revature.project0.scanner;

import java.util.Scanner;

import com.revature.project0.LogHelper;
import com.revature.project0.ServicesLoader;
import com.revature.project0.models.User;

public class EditInformationScreen implements Screen{
	
	private User user;
	private User editableUser;
	private final ServicesLoader sLoader = new ServicesLoader();
	private final LogHelper log = new LogHelper();
	
	public EditInformationScreen() {
	}

	public EditInformationScreen(User user, User editableUser) {
		this.user = user;
		this.editableUser = editableUser;
	}
	
	@Override
	public void render(Scanner conScan) {
		System.out.println("\nWelcome to Customer Information Interface");
		System.out.println("If you want to check Customer(User) information enter '1'");
		System.out.println("If you want to edit Customer(User) edit user name enter '2'");
		System.out.println("If you want to edit Customer(User) edit password enter '3'");
		System.out.println("If you want to edit Customer(User) edit user role enter '4'");
		System.out.println("If you want to edit Customer(User) delet all user accounts '5'");
		System.out.println("For exit to previus screen enter '0'\n");
		System.out.println("Pleas enter your response:");
		
		String enter =  conScan.nextLine();
		
		switch (enter) {
		case "0":
			new DetailInformationScreen(user, editableUser).render(conScan);
			break;
			
		case "1":
			System.out.println(editableUser);
 			break;
			
		case "2":
			while(true) {
				try {
					System.out.println("Enter new user name:");
					String userName = conScan.nextLine();
					editableUser.setUserName(userName);
					this.editableUser = sLoader.uploadUserToDb(editableUser);
					log.callInfoLogger("User was succsesfuly updated: \n" + editableUser);
					break;
				} catch (Exception e) {
					log.callExceptionLogger(e);
					log.callErrorLogger("Unvalid User Name");
					System.out.println("Try again");
				}
			}
			break;
			
		case "3":
			System.out.println("Enter new password:");
			String password = conScan.nextLine();
			editableUser.setUserPassword(password);
			this.editableUser = sLoader.uploadUserToDb(editableUser);
			log.callInfoLogger("User was succsesfuly updated: \n" + editableUser);
			break;
		case "4":
			while(true) {
				try {
					System.out.println("Enter new user role(integer):");
					int role = Integer.parseInt(conScan.nextLine());
					editableUser.setRoleId(role);
					this.editableUser = sLoader.uploadUserToDb(editableUser);
					log.callInfoLogger("User was succsesfuly updated: \n" + editableUser);
					break;
				} catch (Exception e) {
						log.callExceptionLogger(e);
						log.callErrorLogger("Unvalid User Role");
						System.out.println("Try again");
					}
			}
			break;
		case "5":
			editableUser.setAccountList(null);
			this.editableUser = sLoader.uploadUserToDb(editableUser);
			log.callInfoLogger("User was succsesfuly updated: \n" + editableUser);
			break;
			
		default:
			log.callErrorLogger("Wrong input!");
			System.out.println("Try again");
			break;
		}
		new EditInformationScreen(user, editableUser).render(conScan);
	}
	

}
