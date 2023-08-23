package iCarPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class CartServlet
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CartServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		StringBuilder build = new StringBuilder();
		ArrayList<String>  cart =  (ArrayList<String>) session.getAttribute("cart");
		   if(cart == null) {
			    PrintWriter writer = response.getWriter();
			   build.append("hey not found");
			    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			    writer.write(build.toString());
			    return;
			   
			   
		   } else {
			   
			   PreparedStatement prep = PrepCaches.getCart();
			   
			   
			   try {
			   
			   for(String s : cart) {
				   
				   prep.setInt(1, Integer.parseInt(s));
					ResultSet result = prep.executeQuery();
				
					if(result.first()) {
						build.append(result.getString(1));
						build.append(',');
						build.append(result.getString(2));
						build.append(',');
						build.append(result.getString(3));
						build.append(',');
						build.append(result.getString(4));
						build.append('.');
						
						
						
					}
					
			   
			   
			   
			   }
			   response.setStatus(HttpServletResponse.SC_OK);
			   response.setContentType("text/plain");
		        PrintWriter  writer = response.getWriter();
		        writer.write(build.toString());
			   
			   
			   return;
			   
			   
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// we will do post for the purch through session
		HttpSession session = request.getSession();
		String  account  = (String) session.getAttribute("account");
		ArrayList<String> cart  =  (ArrayList<String>) session.getAttribute("cart");
		System.out.println("Cart::"+cart+" account"+account);
		  if(account ==null | cart ==null) {
			  
			  response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			   response.setContentType("text/plain");
		        PrintWriter  writer = response.getWriter();
		        writer.write("login or create account ");
		        System.out.println("hey CartServlet failure");
		        return;
			  
		  } else {
			  
			 
			   
			   
			PreparedStatement prep = PrepUser.getInsert();
			try {
			  for(String car  : cart) {
				  prep.setInt(1,Integer.parseInt(account));
				  prep.setInt(2, Integer.parseInt(car));
				  prep.executeUpdate();
				 
				  
				  
			  }
			  response.setStatus(HttpServletResponse.SC_OK);
			   response.setContentType("text/plain");
		        PrintWriter  writer = response.getWriter();
		        writer.write("ok");
		        System.out.println("Succfeful ......");
		        return;
		       
				
				
				
			} catch (NumberFormatException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			}
		  
		  
		  
		
		
		
		
	}
	@SuppressWarnings("unchecked")
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deleteItem = request.getParameter("item");
		
		HttpSession session = request.getSession();
		StringBuilder build = new StringBuilder();
		ArrayList<String> cart =  (ArrayList<String>) session.getAttribute("cart");
		
		
		if(cart == null) {
		    PrintWriter writer = response.getWriter();
		   build.append("hey not found");
		    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
		    writer.write(build.toString());
		    return;
		   
		   
	   } else {
		     
		    cart.remove(deleteItem);
		    session.setAttribute("cart", cart);
		    System.out.println("delete"+ cart.toString());
		   
		   
		   
		   
		   }
		   
		   
		    
		   response.setStatus(HttpServletResponse.SC_OK);
		   response.setContentType("text/plain");
	        PrintWriter  writer = response.getWriter();
	        writer.write("ok");
		   
		   
		   
	   }
		
	}
	
	
	


