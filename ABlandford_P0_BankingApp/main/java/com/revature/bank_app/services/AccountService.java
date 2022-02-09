package com.revature.bank_app.services;

import java.util.UUID;

import com.revature.bank_app.daos.AccountDAO;
import com.revature.bank_app.exceptions.InvalidRequestException;
import com.revature.bank_app.models.Account;

public class AccountService {

	private AccountDAO accountDao = new AccountDAO();
	private Account newAccount;
	private double defaultBalance = 0.00; 

	public Account createNewAccount() {
		
		String newAccountId = UUID.randomUUID().toString();
		
		newAccount = new Account(newAccountId, defaultBalance);
		
		return accountDao.create(newAccount);
	}
	
	public boolean deleteById(String accountId) {
		
		if(accountId == null || accountId.trim().equals("")) {
			throw new InvalidRequestException("The account provided doesn't exist. Please try again.");
		}
		
		return accountDao.delete(accountId);
	}

}
