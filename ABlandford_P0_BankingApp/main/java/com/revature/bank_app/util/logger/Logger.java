package com.revature.bank_app.util.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class Logger {
	
	private static final String ANSI_GREEN = "\u001B[32m";
	private static final String ANSI_YELLOW = "\u001B[33m";
	private static final String ANSI_CYAN = "\u001B[36m";
	private static final String ANSI_RED = "\u001B[31m";
	private static final String ANSI_RESET = "\u001B[0m";
	
	private static Logger logger;
	private final boolean printToConsole;
	
	private Logger(boolean printToConsole) {
		this.printToConsole = printToConsole;
	}
	
	public static Logger getLogger(boolean printToConsole) {
		
		// Lazy Singleton
		if(logger == null) {
			logger = new Logger(printToConsole);
		}
		
		return logger;
	}
	
	// Think about the different thresholds and assigning methods to them
	public void info(String message) {
		
	}
	
	public void log(String message, Object... extra) {
		
		try (Writer logWriter = new FileWriter("main/resources/bank.log", true)){
			
			String messFormatted = String.format(message, extra);
			
			logWriter.write(messFormatted + "\n");
			
			if(printToConsole == true) {
				System.out.println(ANSI_GREEN + message + ANSI_RESET);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
