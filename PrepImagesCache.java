package iCarPackage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PrepImagesCache {
	
	public static PreparedStatement imgCarId;
	public static PreparedStatement id;
	
	
	
	public synchronized static PreparedStatement getImgCarId() {
		if(imgCarId == null) {
			Connection conn  = DbCache.getConnection();
			try {
				imgCarId = conn.prepareStatement("select img from images where carId = ? limit 1");
				
				return imgCarId;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Prepimages");
			}
			
			
		}
		return imgCarId;
		
		
	}
	public synchronized static PreparedStatement getId() {
		if(id== null) {
			Connection conn  = DbCache.getConnection();
			try {
				id = conn.prepareStatement("select img from images where id = ?");
				
				return id;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("Prepimages");
			}
			
			
		}
		return id;
		
		
	}
	
	


}
