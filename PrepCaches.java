package iCarPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepCaches {
  
	
	// this class Caches preparedStatement objects in one place
	
	
	public static PreparedStatement posts;
	public static PreparedStatement postByType;
	public static PreparedStatement postByBrand;
	public static PreparedStatement postByYear;
	public static PreparedStatement postSearch;
	public static PreparedStatement   cart;
	
	public static PreparedStatement getPosts() {
		if(posts == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	posts = conn.prepareStatement(" select id,car_name,model,price from car_details");
	return posts;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return posts;
		
		
		
	}
	public static PreparedStatement getPostByType() {
		if(postByType == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	postByType = conn.prepareStatement(" select id,car_name,model,price from car_details where category = ?");
	return postByType;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return postByType;
		
		}
	public static PreparedStatement getPostByBrand() {
		
		if(postByBrand == null) {
			Connection conn = DbCache.getConnection();
			
try {
	
	
	postByBrand = conn.prepareStatement(" select id,car_name,model,price from car_details where brand = ?");
	return postByBrand;
	
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}
		return postByBrand;
		
		
	}
	
	
	public static PreparedStatement getPostByYear() {
	if (postByYear == null) {
		Connection conn = DbCache.getConnection();
		
try {


postByYear = conn.prepareStatement(" select id,car_name,model,price from car_details where model = ?");
return postByYear;

} catch (SQLException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
}
	return postByYear;
		
		
	}
	public static PreparedStatement getPostBySearch() {
		if (postSearch == null) {
			Connection conn = DbCache.getConnection();
			
	try {


	postSearch = conn.prepareStatement(" select id,car_name,model,price from car_details where "
			+ "car_name regexp ? or brand regexp ? or category regexp ? or description regexp ?");
	return postSearch;

	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
		return postSearch;
			
		
		
	}
	
	public static PreparedStatement getCart() {
		if (cart == null) {
			Connection conn = DbCache.getConnection();
			
	try {


	cart = conn.prepareStatement(" select id,car_name,model,price from car_details where "
			+ "id = ?");
	return cart;

	} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
	}
	}
		return cart;
			
		
		
	}
	
	
	

	
	

}
