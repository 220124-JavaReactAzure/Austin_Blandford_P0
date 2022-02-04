package com.revature.bank_app.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.revature.bank_app.menus.start.Login;
import com.revature.bank_app.menus.start.Register;
import com.revature.bank_app.menus.start.Welcome;
import com.revature.bank_app.services.CustomerService;
import com.revature.bank_app.util.logger.Logger;

public class AppState {

	private final Logger logger;
	private static boolean isRunning;
	private final MenuRouter router;
	
	public AppState() {
		
		logger = Logger.getLogger(true);
		logger.log("Application is initializing....");
		
		isRunning = true;
		router = new MenuRouter();
		BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
		
		CustomerService customerService = new CustomerService();
		router.addMenu(new Welcome(consoleReader, router));
		router.addMenu(new Register(consoleReader, router, customerService));
		router.addMenu(new Login(consoleReader, router, customerService));
		
		logger.log("Application initialized! Redirecting to the main menu.");
	}
	
	public void startup() {
		try {
			while(isRunning) {
				router.transfer("/welcome");
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void shutdown() {
		System.out.println("Exiting the application. Hope to see you again!");
		isRunning = false;
	}
	
}
