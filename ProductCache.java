package iCarPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductCache {
	
	public static PreparedStatement product;
	public static PreparedStatement images;
	
	
	public static PreparedStatement getProduct() {
		
		if(product ==null) {
			
			
			Connection conn = DbCache.getConnection();
			
			
			try {
				product = conn.prepareStatement("select * from  car_details where id = ?");
				return product;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			}
		return product;
	}
public static PreparedStatement getImages() {
		
		if(images ==null) {
			
			
			Connection conn = DbCache.getConnection();
			
			
			try {
			images = conn.prepareStatement("select id from images where carId = ?");
				return images;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			}
		return images;
	}

}
