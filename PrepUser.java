package iCarPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class PrepUser {
	
	public static PreparedStatement signup;
	public static PreparedStatement login;
	public static PreparedStatement user;
	public static  PreparedStatement history;
	public static PreparedStatement insertP;
	
	
	public static PreparedStatement getSignup() {
		if(signup == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	signup = conn.prepareStatement(" insert into users values (default, ?,?,?,?)");
	return signup;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return signup;
		
		
		
	}
	public static PreparedStatement Login() {
		if(login == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	login = conn.prepareStatement(" select id from users  where  email = ? and userpassword = ?");
	return login;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return login;
		
		
		
	}
	public static PreparedStatement getuser() {
		if(user == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	user = conn.prepareStatement(" select * from users where id = ?");
	return user;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return user;
		
		
		
	}
	public static PreparedStatement getHistory() {
		if(history == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	history = conn.prepareStatement(" select car_name,price, datePurchase from purchases p join "
			+ " car_details d on p.carId = d.id  where userId = ?");
	return history;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return history;
		
		
		
	}
	public static PreparedStatement getInsert() {
		if(insertP == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	insertP = conn.prepareStatement("insert into purchases values (default,?,?,default)");
	return insertP;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return insertP;
		
		
		
	}
	
}
