package com.revature.bank_app.services;

import com.revature.bank_app.models.Customer;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.bank_app.daos.CustomerDAO;
import com.revature.bank_app.exceptions.InvalidRequestException;

public class CustomerService {
	
	private CustomerDAO customerDAO = new CustomerDAO();

	public boolean registerNewCustomer(Customer newCustomer) {
		if(!isCustomerValid(newCustomer)) {
			throw new InvalidRequestException("Invalid user data provided!");
		}
		
		customerDAO.create(newCustomer);
		
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
