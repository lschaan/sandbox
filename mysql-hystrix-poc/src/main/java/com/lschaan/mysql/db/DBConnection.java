package com.lschaan.mysql.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Value;

public class DBConnection {

	@Value("${db.url}")
	private String URL;

	@Value("${db.user}")
	private String USER;

	@Value("${db.password}")
	private String PASSWORD;

	public Connection getConnection() throws SQLException {
		Connection c = null;
		try {
			c = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException error) {
			throw new SQLException("Unable to connect! " + error.getMessage());
		}
		return c;
	}
}