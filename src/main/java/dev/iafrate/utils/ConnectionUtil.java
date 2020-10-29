package dev.iafrate.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConnectionUtil {
	// this class follows the singleton design pattern
	private static ConnectionUtil cu = null;
	private static Properties properties;
	
	private ConnectionUtil() {

	}
	
	public static synchronized ConnectionUtil getConnectionUtil() {
		if (cu == null)
			cu = new ConnectionUtil();

		return cu;
	}
	
	public Connection getConnection() {
		Connection conn = null;
		try {
			String url = System.getenv("url");
			String password = System.getenv("psw");
			String username = System.getenv("usr");
			String drv = System.getenv("drv");
			Class.forName(drv);
			conn = DriverManager.getConnection(
					url,
					username,
					password
					);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}