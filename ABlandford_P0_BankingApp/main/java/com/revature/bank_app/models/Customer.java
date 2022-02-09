package com.revature.bank_app.models;

public class Customer {

	// Variables
	private String customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String accountId;
	
	// Constructor
	public Customer() {
		super();
	}
	
	public Customer(String firstName, String lastName, String email, String password) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	public Customer(String customerId, String firstName, String lastName, String email, String password, String accountId) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.accountId = accountId;
	}

	// Getters and Setters
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}
	
	public String toFileString() {
		StringBuilder buildFileString = new StringBuilder();
		
		buildFileString.append(firstName).append(":")
					   .append(lastName).append(":")
					   .append(email).append(":")
					   .append(password).append(":");
		
		return buildFileString.toString();
	}
	
	public String toStringWithIds() {
		return "Customer [Customer ID: " + customerId + ", First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email + ", Password: " + password + ", Account ID: " + accountId +"]";
	}
	
	public String toStringUserReadable() {
		return "Here is your information\n\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\nEmail: " + email;
	}
	
	@Override
	public String toString() {
		return "Customer [First Name: " + firstName + ", Last Name: " + lastName + ", Email: " + email + ", Password: " + password + "]";
	}
	
}
