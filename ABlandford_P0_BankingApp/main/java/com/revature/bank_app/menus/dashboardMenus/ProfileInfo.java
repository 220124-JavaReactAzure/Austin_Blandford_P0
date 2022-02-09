package com.revature.bank_app.menus.dashboardMenus;

import java.io.BufferedReader;

import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.models.Customer;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class ProfileInfo extends Menu {

	private final CustomerService customerService;

	public ProfileInfo(BufferedReader consoleReader, MenuRouter router, CustomerService customerService) {
		super("Profile Information", "/profile-information", consoleReader, router);
		this.customerService = customerService;
	}

	@Override
	public void render() throws Exception {

		Customer sessionCustomer = customerService.getSessionCustomer();

		System.out.println(sessionCustomer.toStringUserReadable());

		String profileMenu = "What would you like to edit?\n" + "1) First Name\n" + "2) Last Name\n" + "3) Email\n"
				+ "4) Exit to Dashboard";

		System.out.println(profileMenu);

		String userSelection = consoleReader.readLine();

		switch (userSelection) {
		case ("1"):
			System.out.println("Enter your new legal first name: ");
			String newFirstName = consoleReader.readLine();
			customerService.updateFirstName(sessionCustomer, newFirstName);
			break;
		case ("2"):
			System.out.println("Enter your new legal last name: ");
			String newLastName = consoleReader.readLine();
			customerService.updateLastName(sessionCustomer, newLastName);
			break;
		case ("3"):
			System.out.println("Enter your new email: ");
			String newEmail = consoleReader.readLine();
			customerService.updateEmail(sessionCustomer, newEmail);
			break;
		case ("4"):
			System.out.println("Redirecting to dashboard...");
			router.transfer("/dashboard");
			break;
		default:

		}

	}

}
