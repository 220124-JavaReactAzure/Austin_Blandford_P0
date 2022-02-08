package com.revature.bank_app.menus.dashboardMenus;

import java.io.BufferedReader;

import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.models.Customer;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class Dashboard extends Menu {
	
	private final CustomerService customerService;

	public Dashboard(BufferedReader consoleReader, MenuRouter router, CustomerService customerService) {
		super("Dashboard", "/dashboard", consoleReader, router);
		this.customerService = customerService;
	}

	@Override
	public void render() throws Exception {
		
		Customer sessionCustomer = customerService.getSessionCustomer();
		
		if(sessionCustomer == null) {
			System.out.println("You are not currently logged in. Redirecting to the welcome menu...");
			router.transfer("/welcome");
			return;
		}
		
		while (customerService.isSessionActive()) {
			System.out.println("Welcome " + sessionCustomer.getFirstName() + " " + sessionCustomer.getLastName() + "!");
			String dashboardMenu = "1) View/edit your profile\n" +
								   "2) Check your balance\n" +
								   "3) Delete your account\n" +
								   "4) Logout\n";
			
			System.out.print(dashboardMenu);
			
			String userSelection = consoleReader.readLine();
			
			switch(userSelection) {
			case("1"):
				System.out.println("Redirecting to your profile information...");
				router.transfer("/profileInformation");
				break;
			case("2"):
				System.out.println("Redirecting to your account balance...");
				router.transfer("/accountBalance");
				break;
			case("3"):
				router.transfer("/deleteAccount");
				break;
			case("4"):
				customerService.logout();
				break;
			default:
				System.out.println("Your input was invalid. Please enter a number from 1 to 4.");
			}
		}
		
	}

	
	
}
