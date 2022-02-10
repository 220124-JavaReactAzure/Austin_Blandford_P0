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
		
		String menu = "What would you like to do?\n"
					+ "1) Deposit Money\n"
					+ "2) Withdraw Money\n"
					+ "3) Return to dashboard\n";
		
		System.out.println(menu);
		
		String userSelection = consoleReader.readLine();
		String depositInput;
		
		switch(userSelection) {
		case("1"):
			System.out.print("Please enter the amount you would like to deposit: ");
			depositInput = consoleReader.readLine();
			double amountToDeposit = Double.parseDouble(depositInput);
			if(accountService.deposit(sessionCustomer.getAccountId(), availableBalance, amountToDeposit)) {
				router.transfer("/dashboard");
				break;
			}
			System.out.println("Your transaction has failed. Please try again.");
			router.transfer("/account-balance");
			break;
		case("2"):
			System.out.print("Please enter the amount you would like to withdraw: ");
			depositInput = consoleReader.readLine();
			double amountToWithdraw = Double.parseDouble(depositInput);
			if(accountService.withdraw(sessionCustomer.getAccountId(), availableBalance, amountToWithdraw)) {				
				router.transfer("/dashboard");
				break;
			}
			System.out.println("Your transaction has failed. Please try again.");
			router.transfer("/account-balance");
			break;
		case("3"):
			router.transfer("/dashboard");
			break;
		default:
			System.out.println("Please enter the number 1 to return to the dashboard.");
		}
		
	}

}
