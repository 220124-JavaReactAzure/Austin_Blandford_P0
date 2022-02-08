package com.revature.bank_app.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.bank_app.models.Account;
import com.revature.bank_app.util.List;
import com.revature.bank_app.util.datasource.ConnectionFactory;

public class AccountDAO implements CrudDAO<Account> {

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
		// TODO Auto-generated method stub
		return false;
	}

}
