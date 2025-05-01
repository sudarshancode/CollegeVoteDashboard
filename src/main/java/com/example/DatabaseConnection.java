package com.example;
import java.sql.*;

public class DatabaseConnection {
	public static Connection getConnection() {
	    String url = "jdbc:mysql://localhost:3306/collegevote";
	    String user = "root";
	    String password = "";
	    Connection conn=null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        System.out.println("JDBC Driver Loaded Successfully.");
	        conn = DriverManager.getConnection(url, user, password);
	        if (conn != null) {
	            System.out.println("Database Connected Successfully.");
	        } else {
	            System.out.println("Database Connection Failed.");
	        }
	    } catch (ClassNotFoundException e) {
	        System.out.println("Driver Not Loaded");
	        e.printStackTrace();
	    } catch (SQLException e) {
	        System.out.println("Connection Not Established");
	        e.printStackTrace();
	    }
	    return conn;
	}
}