package com.revature.bank_app.menus.start;

import java.io.BufferedReader;

import com.revature.bank_app.exceptions.InvalidRequestException;
import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.models.Customer;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class Register extends Menu {

	CustomerService customerService;
	Customer newCustomer;
	
	public Register(BufferedReader consoleReader, MenuRouter router, CustomerService customerService) {
		super("Register", "/register", consoleReader, router);
		this.customerService = customerService;
	}

	@Override
	public void render() throws Exception {
		
		System.out.println("Welcome to your new banking app! To get started, we will need some basic information from you.");
		System.out.println("Please enter the information below.");
		
		System.out.print("First Name: ");
		String firstName = consoleReader.readLine();
		
		System.out.print("Last Name: ");
		String lastName = consoleReader.readLine();
		
		System.out.print("Email: ");
		String email = consoleReader.readLine();
		
		System.out.print("Password: ");
		String password = consoleReader.readLine();
		
		newCustomer = new Customer(firstName, lastName, email, password);
		boolean confirm = true;
		
		while(confirm) {
			System.out.println("\nThe information you entered is below.\n");
			System.out.println(newCustomer.toString());
			System.out.print("Is the information above correct(Y/N): ");
			String yesNoInput = consoleReader.readLine();
			switch(yesNoInput) {
			case("Y"):
			case("y"):
				System.out.println("\nInformation confirmed. Proceeding with the information you provided.\n");
				confirm = false;
				break;
			case("N"):
			case("n"):
				System.out.println("\nPlease enter your information again.\n");
				newCustomer = null;
				router.transfer("/register");
				confirm = false;
				break;
			default:
				System.out.println("\nI do not know what you are trying to tell me. Please enter Y or N for a yes or no answer.\n");
				break;
			}
		}
		
		try {
			if(customerService.registerNewCustomer(newCustomer)) {
				router.transfer("/login");
			} else {
				router.transfer("/welcome");
			}
		} catch(InvalidRequestException e) {
			System.out.println("You have provided invalid data. Please try again.");
			
			router.transfer("/register");
		}
	}
	
}
