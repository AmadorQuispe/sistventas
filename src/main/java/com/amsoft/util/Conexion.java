package com.amsoft.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class Conexion {

	private static String url = "jdbc:mysql://localhost:3306/sistventas?useSSL=false&serverTimezone=America/Lima";
	private static String userName = "root";
	private static String password = "Amador123.";

	// private static BasicDataSource pool;
	private static Connection conn;

	/*
	 * public static BasicDataSource getInstance() throws SQLException {
	 * if (pool == null) {
	 * pool = new BasicDataSource();
	 * pool.setUrl(url);
	 * pool.setUsername(userName);
	 * pool.setPassword(password);
	 * pool.setInitialSize(2);
	 * pool.setMinIdle(2);
	 * pool.setMaxIdle(5);
	 * pool.setMaxTotal(5);
	 * }
	 * return pool;
	 * }
	 */

	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		conn = DriverManager.getConnection(url, userName, password);
		return conn;
		// return getInstance().getConnection();
	}

}
