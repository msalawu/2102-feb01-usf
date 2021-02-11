package com.revature.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	private static ConnectionUtil cu = null;
	private static Properties props;
	
	private ConnectionUtil() {
		props = new Properties();
		
		try {
			InputStream dbProps = ConnectionUtil.class.getClassLoader().
				getResourceAsStream("database.properties");
			props.load(dbProps);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtil();
		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		
		try {
			Class.forName(props.getProperty("drv"));
			conn = DriverManager.getConnection(
						props.getProperty("url"),
						props.getProperty("usr"),
						props.getProperty("psw")
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
