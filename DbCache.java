package iCarPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbCache {
	//this class caches Connection object 
public static Connection conn;

public static Connection conn2;



public static Connection getConnection() {
	
	if(conn == null) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/itc_cars","root", "iproot");
		return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed");
		}
		
	}  
	
	return conn;
	

	}

	

public static Connection getConnectionTwo() {
	
	if(conn2 == null) {
		try {
			conn2 = DriverManager.getConnection("jdbc:mysql://localhost:3306/itc_cars","root", "iproot");
		return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Connection failed");
		}
		
	}  
	
	return conn2;
	

	}

	
}




