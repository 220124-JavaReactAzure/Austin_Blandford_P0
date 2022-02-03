package com.revature.bank_app.menus;

import java.io.BufferedReader;

import com.revature.bank_app.exceptions.InvalidRequestException;
import com.revature.bank_app.models.Customer;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class Register extends Menu {

	CustomerService customerService;
	
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
		
		Customer newCustomer = new Customer(firstName, lastName, email, password);
		System.out.println("The information you entered is below.");
		System.out.println(newCustomer.toString());
		
		System.out.println("Adding information to customers.txt...");
		
		try {
			customerService.registerNewCustomer(newCustomer);
		} catch(InvalidRequestException e) {
			System.out.println("You have provided invalid data. Please try again.");
			
			router.transfer("/welcome");
		}
	}
	
}
