package com.revature.bank_app.menus.dashboardMenus;

import java.io.BufferedReader;

import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.models.Customer;
import com.revature.bank_app.services.AccountService;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.MenuRouter;

public class AccountBalance extends Menu {

	private final AccountService accountService;
	private final CustomerService customerService;
	
	public AccountBalance(BufferedReader consoleReader, MenuRouter router, CustomerService customerService, AccountService accountService) {
		super("Account Balance", "/account-balance", consoleReader, router);
		this.customerService = customerService;
		this.accountService = accountService;
	}

	@Override
	public void render() throws Exception {
		
		Customer sessionCustomer = customerService.getSessionCustomer();
		
		double availableBalance = accountService.retrieveBalance(sessionCustomer.getAccountId());
		
		System.out.println("Your available balance: " + availableBalance + "\n");
		
		System.out.println("1) Return to dashboard");
		
		String userSelection = consoleReader.readLine();
		
		switch(userSelection) {
		case("1"):
			router.transfer("/dashboard");
			break;
		default:
			System.out.println("Please enter the number 1 to return to the dashboard.");
		}
		
	}

}
