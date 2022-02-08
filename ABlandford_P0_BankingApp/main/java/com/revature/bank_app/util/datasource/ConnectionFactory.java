package com.revature.bank_app.util.datasource;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
	
	private static final ConnectionFactory connectionFactory = new ConnectionFactory();
	private Properties prop = new Properties();

	static {
		try {
			Class.forName("org.postgresql.Driver");
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ConnectionFactory() {

		try {
			prop.load(new FileReader("main/resources/db.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionFactory getInstance() {
		return connectionFactory;
	}
	
	public Connection getConnection() {
		Connection conn = null;

		try {
			conn = DriverManager.getConnection(prop.getProperty("url"),prop.getProperty("admin"),prop.getProperty("password"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return conn;
	}

}
