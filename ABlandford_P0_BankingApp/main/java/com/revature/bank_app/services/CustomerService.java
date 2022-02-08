package com.revature.bank_app.services;

import com.revature.bank_app.models.Account;
import com.revature.bank_app.models.Customer;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.revature.bank_app.daos.CustomerDAO;
import com.revature.bank_app.exceptions.AuthenticationException;
import com.revature.bank_app.exceptions.InvalidRequestException;
import com.revature.bank_app.exceptions.ResourcePersistenceException;

public class CustomerService {
	
	private final CustomerDAO customerDao;
	private final AccountService accountService;
	private Customer sessionCustomer;
	
	public CustomerService(CustomerDAO customerDao, AccountService accountService) {
		this.customerDao = customerDao;
		this.accountService = accountService;
		this.sessionCustomer = null;
	}
	
	public Customer getSessionCustomer() {
		return sessionCustomer;
	}

	public boolean registerNewCustomer(Customer newCustomer) {
		if(!isCustomerValid(newCustomer)) {
			throw new InvalidRequestException("Invalid user data provided!");
		}
		
		boolean emailAvailable = customerDao.findByEmail(newCustomer.getEmail()) == null;
		
		if(!emailAvailable) {
			throw new ResourcePersistenceException("This email already exists in our database. Please either login with this email, change your password, or register with a different email.\n");
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
	
	public void authenticateCustomer(String email, String password) {
		
		if(email == null || email.trim().equals("") || password == null || password.trim().equals("")) {
			throw new InvalidRequestException("The input you gave is invalid. Please try again.");
		}
		
		Customer authenticatedCustomer = customerDao.findByEmailAndPassword(email, password);
		
		if(authenticatedCustomer == null) {
			throw new AuthenticationException("Unauthenticated user. Provided information was not found in our database.");
		}
		
		sessionCustomer = authenticatedCustomer;
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
