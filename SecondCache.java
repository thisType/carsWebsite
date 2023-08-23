package iCarPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SecondCache {
	
	
	public static PreparedStatement type;
	public static PreparedStatement brand;
	public static PreparedStatement year;
	
	
	
	public static PreparedStatement getType() {
		if(type == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	type = conn.prepareStatement(" select distinct category from car_details");
	return type;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
 return type;
		
}
	public static PreparedStatement getYear() {
		if(year == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	brand = conn.prepareStatement(" select distinct brand from car_details");
	return year;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
 return year;
		
}
	 
	public static PreparedStatement getBrand() {
		if(brand == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	brand = conn.prepareStatement(" select distinct brand from car_details ");
	return brand;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
 return brand;
		
}
		
		
	

}
