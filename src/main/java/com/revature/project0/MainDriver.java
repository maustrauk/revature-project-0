package com.revature.project0;

import java.util.Scanner;

import com.revature.project0.models.User;
import com.revature.project0.scanner.Screen;
import com.revature.project0.scanner.WelcomeScreen;



public class MainDriver {
	
	public static boolean isRunning = true;

	public static void main(String[] args) {
		
		Scanner conScan = new Scanner(System.in);
		User user = new User();
		Screen presentScreen = new WelcomeScreen(user);
				
		while(isRunning) {
			presentScreen.render(conScan);
			}
		
		conScan.close();
		System.out.println("done");
	}

}
