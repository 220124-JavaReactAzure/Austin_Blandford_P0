package com.revature.bank_app.menus.start;

import java.io.BufferedReader;

import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class Login extends Menu {

	CustomerService customerService;
	
	public Login(BufferedReader consoleReader, MenuRouter router, CustomerService customerService) {
		super("Login", "/login", consoleReader, router);
		this.customerService = customerService;
	}
	
	@Override
	public void render() throws Exception {
		System.out.println("Welcome to your banking app! Please enter your login information below.");
		
		System.out.print("Email: ");
		String email = consoleReader.readLine();
		
		System.out.print("Password: ");
		String password = consoleReader.readLine();
		
		String userInput = "Submitted Email: " + email + "\nSubmitted Password: " + password;
		
		System.out.println(userInput);
	}

}
