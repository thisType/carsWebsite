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
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
	  String id = (String) session.getAttribute("account");
	  StringBuilder build = new StringBuilder();
	  if( id == null) {
		  
		  
		 response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType("text/plain");
	        PrintWriter  writer = response.getWriter();
		    writer.write("nope");
		    return;
	  }
		
		if(action.equalsIgnoreCase("user")) {
			
			 PreparedStatement prep = PrepUser.getuser();
			
			 try {
				 
				 prep.setInt(1,Integer.parseInt(id));
				 
				 ResultSet result = prep.executeQuery();
				 
				 if(result.first()) {
				 
					 build.append(result.getString(1));
					 build.append(",");
					 build.append(result.getString(2));
					 build.append(" ");
					 build.append(result.getString(3));
					 build.append(",");
					 build.append(result.getString(4));
					 
					 response.setStatus(HttpServletResponse.SC_OK);
					   response.setContentType("text/plain");
				        PrintWriter  writer = response.getWriter();
				        writer.write(build.toString());
				        return;
					 
				 
					 
				 }
				
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		} else if(action.equalsIgnoreCase("history")) {
			
			
			PreparedStatement prep =PrepUser.getHistory();
			
			try {
				prep.setInt(1, Integer.parseInt(id));
				ResultSet result  = prep.executeQuery();
				
				if(result.first()) {
					do {
					
						
					build.append(result.getString(1));
					build.append(" ");
					build.append(result.getString(2));
					build.append(" ");
					build.append(result.getString(3));
					build.append(",");
						
						
						
						
						
					} while(result.next());
					
					response.setStatus(HttpServletResponse.SC_OK);
					   response.setContentType("text/plain");
				        PrintWriter  writer = response.getWriter();
				        writer.write(build.toString());
					
				} else {
					
					response.setStatus(HttpServletResponse.SC_OK);
					   response.setContentType("text/plain");
				        PrintWriter  writer = response.getWriter();
				        writer.write("no record");
				}
				
				
			
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
