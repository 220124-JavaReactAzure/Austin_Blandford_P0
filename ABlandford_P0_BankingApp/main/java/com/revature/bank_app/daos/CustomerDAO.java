package com.revature.bank_app.daos;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.revature.bank_app.models.Customer;
import com.revature.bank_app.util.List;

public class CustomerDAO implements CrudDAO<Customer> {

	public Customer findByUsernameAndPassword(String username, String password) {

		System.out.println("The information to use to find the user\nUsername: " + username + "\nPassword: " + password);

		return null;
	}

	@Override
	public Customer create(Customer newObject) {
		// TODO Auto-generated method stub
		System.out.println("Information to submit:\n" + newObject.toString() + "\n");

		System.out.println("Your account has been added. Please login with your information now.");

		LocalDateTime currentDateTime = LocalDateTime.now();

		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		System.out.println("The current date and time is: " + currentDateTime.format(formatDateTime));

		return null;
	}

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Customer updatedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
