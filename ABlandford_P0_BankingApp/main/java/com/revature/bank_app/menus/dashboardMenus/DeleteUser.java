package com.revature.bank_app.menus.dashboardMenus;

import java.io.BufferedReader;

import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class DeleteUser extends Menu {
	
	private final CustomerService customerService;

	public DeleteUser(BufferedReader consoleReader, MenuRouter router, CustomerService customerService) {
		super("Delete User", "/delete-account", consoleReader, router);
		this.customerService = customerService;
	}

	@Override
	public void render() throws Exception {
		
		System.out.println("Would you like to delete your account?\n"
							+ "1) Yes\n"
							+ "2) No");
		
		String userSelection = consoleReader.readLine();
		
		switch(userSelection) {
		case("1"):
			customerService.deleteUser(customerService.getSessionCustomer());
			break;
		case("2"):
			router.transfer("/dashboard");
			break;
		default:
			System.out.println("That input is invalid. Please enter 1 for yes or 2 for no.");
		}
		
	}

}
