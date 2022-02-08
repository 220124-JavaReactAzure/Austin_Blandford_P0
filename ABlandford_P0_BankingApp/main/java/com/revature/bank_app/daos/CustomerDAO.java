package com.revature.bank_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import com.revature.bank_app.models.Customer;
import com.revature.bank_app.util.List;
import com.revature.bank_app.util.datasource.ConnectionFactory;

public class CustomerDAO implements CrudDAO<Customer> {

	public Customer findByUsernameAndPassword(String username, String password) {

		System.out.println("The information to use to find the user\nUsername: " + username + "\nPassword: " + password);

		return null;
	}

	@Override
	public Customer create(Customer newCustomer) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			newCustomer.setCustomerId(UUID.randomUUID().toString());

			System.out.println("Information to submit:\n\n" + newCustomer.toStringWithIds() + "\n");
			
			String sql = "insert into customers (customer_id, customer_name, email, customer_password, account_id) values (?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			String fullName = newCustomer.getFirstName() + " " + newCustomer.getLastName();
			
			ps.setString(1, newCustomer.getCustomerId());
			ps.setString(2, fullName);
			ps.setString(3, newCustomer.getEmail());
			ps.setString(4, newCustomer.getPassword());
			ps.setString(5, newCustomer.getAccountId());
			
			int rowsInserted = ps.executeUpdate();
			
			if(rowsInserted != 0) {
				System.out.println("Your account has been added. Please login with your information now.");
				return newCustomer;
			}

			
		} catch(SQLException e) {
			e.printStackTrace();
		}

//		LocalDateTime currentDateTime = LocalDateTime.now();
//
//		DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//		System.out.println("The current date and time is: " + currentDateTime.format(formatDateTime));

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
