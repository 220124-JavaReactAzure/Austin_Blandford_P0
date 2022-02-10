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
	
	public double retrieveBalance(String accountId) {
		
		if(!validateAccount(accountId)) {
			throw new InvalidRequestException("The account provided doesn't exist. Please try again.");
		}
		
		return accountDao.getBalance(accountId);
	}
	
	public boolean deleteById(String accountId) {
		
		if(!validateAccount(accountId)) {
			throw new InvalidRequestException("The account provided doesn't exist. Please try again.");
		}
		
		return accountDao.delete(accountId);
	}
	
	public boolean deposit(String accountId, double balance, double amountToDeposit) {
		if(!validateAccount(accountId)) {
			System.out.println("The account provided doesn't exist. Please try again.");
			return false;
		} else if(!validateMoney(amountToDeposit)) {
			System.out.println("You tried to deposit a negative amount of money or no money. Please try again with a value above 0.");
			return false;
		}
		
		amountToDeposit = balance + amountToDeposit;
		
		return accountDao.updateBalance(accountId, amountToDeposit);
	}
	
	public boolean withdraw(String accountId, double balance, double amountToWithdraw) {
		if(!validateAccount(accountId)) {
			System.out.println("The account provided doesn't exist. Please try again.");
			return false;
		} else if(!validateMoney(amountToWithdraw)) {
			System.out.println("You tried to withdraw a negative amount of money or no money. Please try again with a value above 0.");
			return false;
		} else if(!validateBalance(balance)) {
			System.out.println("Your balance is currently at 0. Please deposit money before you may make a withdrawal.");
			return false;
		} else if(!validateWithdrawal(balance, amountToWithdraw)) {
			System.out.println("You are trying to withdraw more than you have in your account. Please try again.");
			return false;
		}
		
		amountToWithdraw = balance - amountToWithdraw;
		
		return accountDao.updateBalance(accountId, amountToWithdraw);
	}
	
	@SuppressWarnings("null")
	public boolean validateAccount(String accountId) {
		return accountId != null || !accountId.trim().equals("");
	}
	
	public boolean validateMoney(double money) {
		return money > 0;
	}
	
	public boolean validateBalance(double balance) {
		return balance > 0;
	}
	
	public boolean validateWithdrawal(double balance, double amountToWithdraw) {
		return amountToWithdraw <= balance;
	}

}
