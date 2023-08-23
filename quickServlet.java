package iCarPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class quickServlet
 */
@WebServlet("/quickServlet")
public class quickServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public quickServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method s
		String search = request.getParameter("searchType");
		
		StringBuilder build = new StringBuilder();
		
		if(search.equalsIgnoreCase("type")) {
			  PreparedStatement prep = SecondCache.getType();
			  
		 try {
			ResultSet result = prep.executeQuery();
		  while(result.next()) {
			  
			  build.append(result.getString(1));
			  build.append(',');
			  
		  }
			
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 } else if(search.equalsIgnoreCase("brand")) {
				  PreparedStatement prep = SecondCache.getBrand();
				  
					 try {
						ResultSet result = prep.executeQuery();
					  while(result.next()) {
						  
						  build.append(result.getString(1));
						  build.append(',');
						  
					  }
						
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 } else if(search.equalsIgnoreCase("year")) {
						  PreparedStatement prep = SecondCache.getBrand();
						  
							 try {
								ResultSet result = prep.executeQuery();
							  while(result.next()) {
								  
								  build.append(result.getString(1));
								  build.append(',');
								  
							  }
								
							
					} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							 } 
		
		
		response.setContentType("text/plain");
        PrintWriter  writer = response.getWriter();
        writer.write(build.toString());
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
	}

}
