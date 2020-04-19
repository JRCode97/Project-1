package dev.rivera.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.rivera.controllers.ReimbursementController;

/**
 * Servlet implementation class DispatcherServlet
 */
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ReimbursementController rc = new ReimbursementController();
    public DispatcherServlet() {
        super();

    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String uri = request.getRequestURI();
		switch(uri) {
		
		//API application program interface usually do not return html pages
		//they are endpoints that you can send and get info from usually in json
		case "/ExpenseReimbursementProject/api/showEmployeeReimbursements": 
			rc.getSpecificEmployeeReimbursements(request, response);
			
		break;
		case "/ExpenseReimbursementProject/api/add": 
			rc.addReimbursement(request, response); 
			
		break;
		case "/ExpenseReimbursementProject/api/delete": 
			rc.deleteReimbursement(request, response);
			
		break;
		case "/ExpenseReimbursementProject/api/approve": 
			rc.approveReimbursement(request, response);
			
		break;
		case "/ExpenseReimbursementProject/api/deny": 
			rc.denyReimbursement(request, response);
			
		break;
		case "/ExpenseReimbursementProject/api/showall": 
			rc.getAllReimbursements(request, response);
		break;

		default : response.getWriter().append("your request uri did not match anything "+ uri);break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
