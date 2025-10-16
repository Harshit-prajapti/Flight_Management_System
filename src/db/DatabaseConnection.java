package db;

import java.sql.*;
import java.sql.Connection;
public class DatabaseConnection {
	public static final String url = "jdbc:mysql://localhost:3306/projectDb";
	public static final String userName = "root";
	public static final String password = "Harshit@2005";
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(url,userName,password);		
	}   
}
