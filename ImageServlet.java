package iCarPackage;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/ImageServlet")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	BufferedInputStream  img = null;
	String idS = request.getParameter("id");
	
	int id  = Integer.parseInt(idS);
	PreparedStatement prep =null;
	
	      if(id <100000) {
		
		prep = PrepImagesCache.getId();
	      }   else {
	    	  
	    	  prep = PrepImagesCache.getImgCarId();
	      }

		
	try {
		prep.setInt(1, id);
		ResultSet result = prep.executeQuery();
		
		
		if(result.first()) {
			
			Blob blob = result.getBlob(1);
			img = new BufferedInputStream(blob.getBinaryStream());
			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		
		
		
		
	}
	
	response.setContentType("image/jpeg");
	OutputStream out =response.getOutputStream();
	
	out.write(img.readAllBytes());
	
	
	}
	
	    
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

}
}
