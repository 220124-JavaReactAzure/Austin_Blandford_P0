package com.revature.bank_app.daos;

import com.revature.bank_app.models.Account;
import com.revature.bank_app.util.List;

public class AccountDAO implements CrudDAO<Account> {

	@Override
	public Account create(Account newAccount) {
		
		if(newAccount != null) {
			System.out.println("Account create hit. Creating new account for the user now...");		
			System.out.println("New account id: " + newAccount.getAccountId());
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
