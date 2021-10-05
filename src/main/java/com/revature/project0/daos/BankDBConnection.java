package com.revature.project0.daos;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BankDBConnection {
	
	ClassLoader classLoader = getClass().getClassLoader();
	InputStream is;
	Properties p = new Properties();
	
	public BankDBConnection() {
		is = classLoader.getResourceAsStream("database.properties");
		try {
			p.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Connection getDBConnection() throws SQLException{
		final String URL = p.getProperty("URL");
		final String USERNAME = p.getProperty("USERNAME");
		final String PASSWORD = p.getProperty("PASSWORD");
		return DriverManager.getConnection(URL, USERNAME, PASSWORD);
	}
}
