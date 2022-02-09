package com.revature.bank_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.bank_app.models.Account;
import com.revature.bank_app.util.List;
import com.revature.bank_app.util.datasource.ConnectionFactory;

public class AccountDAO implements CrudDAO<Account> {

	public double getBalance(String accountId) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "select * from accounts where account_id = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, accountId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				Account account = new Account();
				account.setBalance(rs.getDouble("available_balance"));
				
				return account.getBalance();
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	@Override
	public Account create(Account newAccount) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			if(newAccount != null) {
				System.out.println("Creating new account for the user now...");
				
				String sql = "insert into accounts (account_id, available_balance) values (?, ?)";
				
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, newAccount.getAccountId());
				ps.setDouble(2, newAccount.getBalance());
				
				int rowsInserted = ps.executeUpdate();
				
				if(rowsInserted != 0) {
					return newAccount;
				}
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
		
	}

	@Override
	public List<Account> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account findById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(Account updatedObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(String id) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
			
			String sql = "delete from accounts where account_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, id);
			
			int rowsDeleted = ps.executeUpdate();
			
			if(rowsDeleted != 0) {
				return true;
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
