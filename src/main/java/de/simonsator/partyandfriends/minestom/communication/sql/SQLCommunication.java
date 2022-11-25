package de.simonsator.partyandfriends.minestom.communication.sql;

import de.simonsator.partyandfriends.minestom.error.ErrorReporter;

import java.sql.*;
import java.util.Properties;

/**
 * @author simonsator
 * @version 1.0.0 created on 12.06.16
 */
public abstract class SQLCommunication {
	/**
	 * The MySQL DATABASE
	 */
	protected final String DATABASE;
	/**
	 * The URL of the SQL server
	 */
	private final String URL;
	private Connection connection;
	private final String USER_NAME;
	private final String PASSWORD;
	private final boolean USE_SSL;

	public SQLCommunication(String pDatabase, String pURL, String pUserName, String pPassword) {
		this.DATABASE = pDatabase;
		this.URL = pURL + "/" + DATABASE;
		this.USER_NAME = pUserName;
		this.PASSWORD = pPassword;
		this.USE_SSL = false;
		connection = createConnection();
	}

	public SQLCommunication(String pDatabase, String pURL, String pUserName, String pPassword, boolean pUseSSL) {
		this.DATABASE = pDatabase;
		this.URL = pURL + "/" + DATABASE;
		this.USER_NAME = pUserName;
		this.PASSWORD = pPassword;
		this.USE_SSL = pUseSSL;
		connection = createConnection();
	}

	protected void close(ResultSet rs, Statement stmt) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected void close(ResultSet rs, Statement stmt, PreparedStatement prepStmt) {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (prepStmt != null)
				prepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	protected Connection getConnection() {
		try {
			if (connection != null && connection.isValid(6))
				return connection;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection = createConnection();
	}

	protected void close(PreparedStatement pPrepStmt) {
		try {
			if (pPrepStmt != null)
				pPrepStmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private Connection createConnection() {
		try {
			closeConnection();
			Class.forName("com.mysql.jdbc.Driver");
			Properties properties = new Properties();
			properties.setProperty("user", USER_NAME);
			properties.setProperty("password", PASSWORD);
			properties.setProperty("useSSL", USE_SSL + "");
			properties.setProperty("allowPublicKeyRetrieval", !USE_SSL + "");
			properties.setProperty("database", DATABASE);
			properties.setProperty("createDatabaseIfNotExist", "true");
			return DriverManager.getConnection(URL, properties);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			new ErrorReporter("§cParty and Friends was either not able to connect to the MySQL database or to login into the MySQL database. " +
					"Please correct your MySQL data in the config.toml. If you need further help contact Simonsator via Discord (@Simonsator#5834), PM him (https://www.spigotmc.org/conversations/add?to=simonsator ) or write an email to him (support@simonsator.de). Please don't forget to send him the latets.log file. Also please don't write a bad review without giving him 24 hours time to fix the problem.");
		}
		return null;
	}

	public void closeConnection() {
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException ignored) {
		}
	}

}
