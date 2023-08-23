package iCarPackage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet
 */
@WebServlet("/SessionServlet")
public class SessionServlet extends HttpServlet implements Servlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SessionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		String name = request.getParameter("sessionName");
		StringBuilder build  = new StringBuilder();
		HttpSession session = request.getSession();
		String value=  (String) session.getAttribute(name);
		System.out.println("session"+name);
		System.out.println("sessionV"+value);
		if(value == null) {
			PrintWriter writer = response.getWriter();
			   build.append("hey not found");
			    response.setStatus(HttpServletResponse.SC_NOT_FOUND);
			    writer.write(build.toString());
			    return;
			
		} else {
			    
			
			response.setStatus(HttpServletResponse.SC_OK);
			   response.setContentType("text/plain");
		        PrintWriter  writer = response.getWriter();
		        writer.write(value);
		}
		
		
		
	}



}
