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
 * Servlet implementation class FormServlet
 */
@WebServlet("/FormServlet")
public class FormServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FormServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email =request.getParameter("email");
		String pass = request.getParameter("password");
		HttpSession  session  = request.getSession();
	
		PreparedStatement  prep = PrepUser.Login();
		
		try {
			prep.setString(1, email);
			prep.setString(2, pass);
			
			ResultSet  result = prep.executeQuery();
			if(result.first()) {
				
				session.setAttribute("account", result.getString(1));
				response.setStatus(HttpServletResponse.SC_OK);
				   response.setContentType("text/plain");
			        PrintWriter  writer = response.getWriter();
			        writer.write("ok");
			    
				
				return;
				
				
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.setContentType("text/plain");
		        PrintWriter  writer = response.getWriter();
			    writer.write("nope");
			    return;
			
				
				
				
				
				
			}
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			response.setContentType("text/plain");
	        PrintWriter  writer = response.getWriter();
		    writer.write("nope");
		    return;
		
		}
	
	
		
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String  first = request.getParameter("firstName");
		String  second= request.getParameter("secondName");
		String email =request.getParameter("email");
		String pass = request.getParameter("password");
		
		
		    PreparedStatement  prep = PrepUser.getSignup();
		    
		    
		   
		    try {
		    	prep.setString(1, first);
				prep.setString(2, second);
				prep.setString(3, email);
				prep.setString(4,pass);
				
				
				int rows= prep.executeUpdate();
				
				if(rows == 1) {
					
					response.setStatus(HttpServletResponse.SC_OK);
					   response.setContentType("text/plain");
				        PrintWriter  writer = response.getWriter();
				        writer.write("ok");
				    
					
					return;
					
				} else {
					
					
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
					response.setContentType("text/plain");
			        PrintWriter  writer = response.getWriter();
				    writer.write("nope");
				    return;
				
					
					
				}
				
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				response.setContentType("text/plain");
		        PrintWriter  writer = response.getWriter();
			    writer.write("nope");
			    return;
				
			}
 
	}

}
