package com.revature.bank_app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.revature.bank_app.models.Customer;

public class BankDriver {

	static BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		
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
		
		Customer customer = new Customer(firstName, lastName, email, password);
		System.out.println("The information you entered is below.");
		System.out.print(customer.toString());
	}

}
