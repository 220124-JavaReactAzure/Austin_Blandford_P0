package com.revature.bank_app.services;

import com.revature.bank_app.models.Account;
import com.revature.bank_app.models.Customer;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.bank_app.daos.CustomerDAO;
import com.revature.bank_app.exceptions.InvalidRequestException;

public class CustomerService {
	
	private CustomerDAO customerDao = new CustomerDAO();
	private AccountService accountService = new AccountService();

	public boolean registerNewCustomer(Customer newCustomer) {
		if(!isCustomerValid(newCustomer)) {
			throw new InvalidRequestException("Invalid user data provided!");
		}
		
		Account newAccount = accountService.createNewAccount();
		
		if(newAccount != null) {
			newCustomer.setAccountId(newAccount.getAccountId());
			customerDao.create(newCustomer);
		} else {
			System.out.println("Account registration failed. Please try again.");
			return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unused")
	public boolean isCustomerValid(Customer newCustomer) {
		
		System.out.println("Validating your input...");
		
		Pattern emailPatternCompiler = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher emailMatcher = emailPatternCompiler.matcher(newCustomer.getEmail());
		
		if(newCustomer == null) return false;
		if(newCustomer.getFirstName() == null || newCustomer.getFirstName().trim().equals("")) return false;
		if(newCustomer.getLastName() == null || newCustomer.getLastName().trim().equals("")) return false;
		if(newCustomer.getEmail() == null || newCustomer.getEmail().trim().equals("") || !emailMatcher.find()) return false;
		return newCustomer.getPassword() != null || !newCustomer.getPassword().trim().equals("");
	}
	
}
