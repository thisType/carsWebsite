package iCarPackage;

import java.io.IOException;
import java.io.PrintWriter;
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
 * Servlet implementation class postServlet
 */
@WebServlet("/postServlet")
public class postServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public postServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	 String search  =   request.getParameter("search");
	 String searchValue = request.getParameter("searchValue");
	 StringBuilder build = new StringBuilder();
	 System.out.println(request.getParameter("search"));
	 
	 
	 if(search.equalsIgnoreCase("all")) {
		 try {
		 PreparedStatement prep = PrepCaches.getPosts();
		 
		
			ResultSet  result = prep.executeQuery();
			while(result.next()) {
				
				build.append(result.getString(1));
				build.append(',');
				build.append(result.getString(2));
				build.append(',');
				build.append(result.getInt(3));
				build.append(',');
				build.append(result.getString(4));
				build.append('.');
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
	 } else if(search.equalsIgnoreCase("type")) {
		 try {
			 PreparedStatement prep = PrepCaches.getPostByType();
			 
			 prep.setString(1, searchValue);
			
				ResultSet  result = prep.executeQuery();
				while(result.next()) {
					
					build.append(result.getInt(1));
					build.append(',');
					build.append(result.getString(2));
					build.append(',');
					build.append(result.getInt(3));
					build.append(',');
					build.append(result.getInt(4));
					build.append('.');
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }   else if(search.equalsIgnoreCase("search")) {
			 try {
PreparedStatement prep = PrepCaches.getPostBySearch();
			 
			 prep.setString(1, searchValue);
			 prep.setString(2, searchValue);
			 prep.setString(3, searchValue);
			 prep.setString(4, searchValue);
			
			
				ResultSet  result = prep.executeQuery();
				while(result.next()) {
					
					build.append(result.getString(1));
					build.append(',');
					build.append(result.getString(2));
					build.append(',');
					build.append(result.getInt(3));
					build.append(',');
					build.append(result.getString(4));
					build.append('.');
					
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			 
			 
		 } 
			 
			 
			 
			
				 
			 
			 
			 
			 
		 
	 
	 
	        response.setContentType("text/plain");
	        PrintWriter  writer = response.getWriter();
	        writer.write(build.toString());
	 
	          System.out.println("post called");
	          System.out.println(build);
	
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
        String item = request.getParameter("item");
        HttpSession  session = request.getSession();
        session.setAttribute("item", item);
        System.out.println("post"+item);
        response.setContentType("text/plain");
        response.setStatus(HttpServletResponse.SC_OK);
        PrintWriter writer = response.getWriter();
        writer.write("ok");
        
        
        
	
	}

}
