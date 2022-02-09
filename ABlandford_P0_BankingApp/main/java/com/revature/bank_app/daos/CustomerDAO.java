package com.revature.bank_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import com.revature.bank_app.models.Customer;
import com.revature.bank_app.services.AccountService;
import com.revature.bank_app.util.List;
import com.revature.bank_app.util.datasource.ConnectionFactory;

public class CustomerDAO implements CrudDAO<Customer> {

	public Customer findByEmailAndPassword(String email, String password) {

		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from customers where email = ? and customer_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("customer_password"));
				customer.setAccountId(rs.getString("account_id"));
				
				return customer;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	public Customer findByEmail(String email) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from customers where email = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("customer_password"));
				customer.setAccountId(rs.getString("account_id"));
				
				return customer;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public Customer findByCustomerId(String customerId) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			String sql = "select * from customers where customer_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, customerId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Customer customer = new Customer();
				customer.setCustomerId(rs.getString("customer_id"));
				customer.setFirstName(rs.getString("first_name"));
				customer.setLastName(rs.getString("last_name"));
				customer.setEmail(rs.getString("email"));
				customer.setPassword(rs.getString("customer_password"));
				customer.setAccountId(rs.getString("account_id"));
				
				return customer;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean delete(String customerId, String accountId) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "delete from customers where customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, customerId);
			
			int rowsDeleted = ps.executeUpdate();
			
			if(rowsDeleted != 0) {
				AccountService accountService = new AccountService();
				if(accountService.deleteById(accountId)) {
					return true;
				}
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Customer create(Customer newCustomer) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			newCustomer.setCustomerId(UUID.randomUUID().toString());

			System.out.println("Information to submit:\n\n" + newCustomer.toStringWithIds() + "\n");
			
			String sql = "insert into customers (customer_id, first_name, last_name, email, customer_password, account_id) values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, newCustomer.getCustomerId());
			ps.setString(2, newCustomer.getFirstName());
			ps.setString(3, newCustomer.getLastName());
			ps.setString(4, newCustomer.getEmail());
			ps.setString(5, newCustomer.getPassword());
			ps.setString(6, newCustomer.getAccountId());
			
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
	public boolean update(Customer updatedCustomer) {
		
		try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			System.out.println("Updating customer information...");
			
			String sql = "update customers set first_name = ?, last_name = ?, email = ? where customer_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, updatedCustomer.getFirstName());
			ps.setString(2, updatedCustomer.getLastName());
			ps.setString(3, updatedCustomer.getEmail());
			ps.setString(4, updatedCustomer.getCustomerId());
			
			int rowsUpdated = ps.executeUpdate();
			
			if(rowsUpdated != 0) {
				System.out.println("Success! Your profile has been updated.");
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

}
