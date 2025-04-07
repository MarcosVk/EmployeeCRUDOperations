package com.example.employee;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeeDBConn {
	
	static {
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	    } catch (ClassNotFoundException e) {
	        throw new RuntimeException("MySQL JDBC Driver not found!", e);
	    }
	}
	
	public static Connection getConnection() throws SQLException{
		String connURL="jdbc:mysql://localhost:3306/employee?useSSL=false";
		String userName = "root";
	    String password = "Vviiggnneesshh@0";
		return DriverManager.getConnection(connURL, userName, password);
	}

}
