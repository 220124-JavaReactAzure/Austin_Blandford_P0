package com.revature.bank_app.services;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.revature.bank_app.daos.CustomerDAO;
import com.revature.bank_app.models.Customer;

public class CustomerServiceTestSuite {

	CustomerService sut;
	CustomerDAO customerDAO;
	AccountService accountService;
	
	@Before
	public void testPrep() {
		customerDAO = new CustomerDAO();
		accountService = new AccountService();
		sut = new CustomerService(customerDAO, accountService);
	}
	
	@Test
	public void test_isCustomerValid_returnsTrue_givenValidInput() {
		
		// Arrange
		Customer validCustomer = new Customer("valid", "valid", "valid", "valid");
		
		// Act
		boolean actualResult = sut.isCustomerValid(validCustomer);
		
		// Assert
		Assert.assertTrue(actualResult);
		
	}
	
	@Test
	public void test_isCustomerValid_returnsFalse_givenUserWithEmail() {
		
		// Arrange
		Customer invalidCustomer1 = new Customer("", "", "", "");
		
		Customer invalidCustomer2 = new Customer("", "", null, "");
		
		Customer invalidCustomer3 = new Customer("", "", "                  ", "");
		
		// Act
		boolean actualResult1 = sut.isCustomerValid(invalidCustomer1);
		
		boolean actualResult2 = sut.isCustomerValid(invalidCustomer2);
		
		boolean actualResult3 = sut.isCustomerValid(invalidCustomer3);
		
		// Assert
		Assert.assertFalse(actualResult1);
		
		Assert.assertFalse(actualResult2);
		
		Assert.assertFalse(actualResult3);
		
	}
	
}
