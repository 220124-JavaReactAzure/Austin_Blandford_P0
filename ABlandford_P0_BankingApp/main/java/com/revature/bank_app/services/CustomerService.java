package com.revature.bank_app.services;

import com.revature.bank_app.models.Customer;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.bank_app.exceptions.InvalidRequestException;

public class CustomerService {

	public boolean registerNewCustomer(Customer newCustomer) {
		if(!isCustomerValid(newCustomer)) {
			throw new InvalidRequestException("Invalid user data provided!");
		}
		
		File customersFile = new File("resources/customers.txt");
		
		try(FileWriter fileWriter = new FileWriter(customersFile, true)) {
			fileWriter.write(newCustomer.toFileString() + "\n");
		} catch(Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Error persisting user information.");
		}
		
		System.out.println("Your account has been added. Please login with your information now.");
		
		LocalDateTime currentDateTime = LocalDateTime.now();
		
		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		System.out.println("The current time is this: " + currentDateTime.format(formatDateTime));
		
		return true;
	}
	
	public boolean isCustomerValid(Customer newCustomer) {
		
//		String pattern = "^(.+)@(.+)$";
//		
//		Pattern patternCompiler = Pattern.compile(pattern);
//		
//		Matcher matcher = patternCompiler.matcher(newCustomer.getEmail());
//		
//		if(matcher.find()) {
//			System.out.println("The email entered is valid.");
//		} else {
//			System.out.println("That email is not valid. Please try again.");
//		}
//		
//		return true;
		
		if(newCustomer == null) return false;
		if(newCustomer.getFirstName() == null || newCustomer.getFirstName().trim().equals("")) return false;
		if(newCustomer.getLastName() == null || newCustomer.getLastName().trim().equals("")) return false;
		if(newCustomer.getEmail() == null || newCustomer.getEmail().trim().equals("")) return false;
		return newCustomer.getPassword() != null || !newCustomer.getPassword().trim().equals("");
	}
	
}
