package com.revature.bank_app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
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
		System.out.println(customer.toString());
		
		System.out.println("Adding information to customers.txt...");
		
		File customersFile = new File("resources/customers.txt");
		
		try(FileWriter fileWriter = new FileWriter(customersFile, true)) {
			fileWriter.write(customer.toFileString() + "\n");
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error persisting user information.");
		}
		
		System.out.println("Your account has been added. Please login with your information now.");
	}

}
