package com.revature.bank_app.menus.start;

import java.io.BufferedReader;

import com.revature.bank_app.menus.Menu;
import com.revature.bank_app.util.MenuRouter;
import static com.revature.bank_app.util.AppState.shutdown;

public class Welcome extends Menu {

	public Welcome(BufferedReader consoleReader, MenuRouter router) {
		super("Welcome", "/welcome", consoleReader, router);
	}

	@Override
	public void render() throws Exception {
		
		System.out.print("Welcome to your new banking app! Please input a number to make your selection.\n" + "1) Login\n" + "2) Register\n" + "3) Exit\n");
		
		String selection = consoleReader.readLine();
		
		switch(selection) {
		case("1"):
			router.transfer("/login");
			break;
		case("2"):
			router.transfer("/register");
			break;
		case("3"):
			shutdown();
			break;
		default:
			System.out.println("The input you entered is invalid. Please try again.");
			break;
		}
		
	}
	
}
