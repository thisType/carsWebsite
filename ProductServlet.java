package iCarPackage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ProductServlet
 */
//handles product details
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	 HttpSession session =  request.getSession(false);
	 String productId;
	 productId =(String) session.getAttribute("item");
	 StringBuilder builder = new StringBuilder();
	 System.out.println("item"+ productId);
	 if(productId  ==null ) {
		productId = "100000"; 
		
		
	 }
	 try {
	 
	 PreparedStatement prep = ProductCache.getProduct();

		prep.setInt(1, Integer.parseInt(productId));
		String id= "100000";
		ResultSet result = prep.executeQuery();
            if(result.first()) {
            	builder.append(result.getString(1));
            	id = result.getString(1);
            	builder.append(',');
            	builder.append(result.getString(2));
            	builder.append(',');
            	builder.append(result.getString(3));
            	builder.append(',');
            	builder.append(result.getInt(4));
            	builder.append(',');
            	builder.append(result.getString(5));
            	builder.append(',');
            	builder.append(result.getString(6));
            	builder.append(',');
            	builder.append(result.getString(7));
            	builder.append(',');
            	
            	
            	
            	
            }
            
            
        PreparedStatement prep2 = ProductCache.getImages();
        prep2.setInt(1, Integer.parseInt(id));
        ResultSet two = prep2.executeQuery();
        
        while(two.next()) {
        	builder.append(two.getString(1));
        	builder.append(',');
        	
        }
        
		
	} catch (NumberFormatException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	  
	 response.setContentType("text/plain");
     PrintWriter  writer = response.getWriter();
     writer.write(builder.toString());
	System.out.println("Product");
	System.out.println(builder.toString());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String cart =  request.getParameter("cart");
		HttpSession session  = request.getSession();
		ArrayList<String>  cartList = new ArrayList<String>();
		
		ArrayList<String>  sessionList  =  (ArrayList<String>) session.getAttribute("cart");
	            if(sessionList == null) {
	            	cartList.add(cart);
	            	session.setAttribute("cart",cartList );
	            	System.out.println("Cart"+cartList.toString());
	            } else {
	            	
	            	sessionList.add(cart);
	            	session.setAttribute("cart",sessionList);
	            	System.out.println("Cart"+sessionList.toString());
	            	
	            }

		     response.setStatus(HttpServletResponse.SC_OK);
		    PrintWriter writer = response.getWriter();
		    writer.write("ok");
		    
	            
	}

}
