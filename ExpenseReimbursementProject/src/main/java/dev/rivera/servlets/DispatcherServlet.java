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
		case "/ExpenseReimbursementProject/api/getName": 
			rc.getManagerName(request, response);
			
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
		case "/ExpenseReimbursementProject/api/showPending": 
			rc.getPendingReimbursements(request, response);
		break;
		case "/ExpenseReimbursementProject/api/showDenied": 
			rc.getDeniedReimbursements(request, response);
		break;
		case "/ExpenseReimbursementProject/api/showApproved": 
			rc.getApprovedReimbursements(request, response);
		break;
		case "/ExpenseReimbursementProject/api/logOut": 
			request.getSession().removeAttribute("username");
			request.getSession().removeAttribute("password");
			request.getSession().removeAttribute("name");
			request.getSession().invalidate();

		break;
		case"/ExpenseReimbursementProject/api/highestRequester":
			rc.getMostReimbursementMaker(request, response);
			break;
		case"/ExpenseReimbursementProject/api/avgReimbursement":
			rc.getAverageReimbursementAmount(request, response);
			break;
		case"/ExpenseReimbursementProject/api/amtOfApproved":
			rc.getApprovedReimbursementAmount(request, response);
			break;
		case"/ExpenseReimbursementProject/api/amtOfDenied":
			rc.getDeniedReimbursementAmount(request, response);
			break;
		case"/ExpenseReimbursementProject/api/highestRequesterinWorkForce":
			rc.getMostReimbursementMakerforWorkForce(request, response);
			break;
		case"/ExpenseReimbursementProject/api/avgReimbursementinWorkForce":
			rc.getAverageReimbursementAmountforWorkForce(request, response);
			break;
		case"/ExpenseReimbursementProject/api/amtOfApprovedinWorkForce":
			rc.getApprovedReimbursementAmountforWorkForce(request, response);
			break;
		case"/ExpenseReimbursementProject/api/amtOfDeniedinWorkForce":
			rc.getDeniedReimbursementAmountforWorkForce(request, response);
			break;
		case"/ExpenseReimbursementProject/api/getEmployeesinWorkForce":
			rc.getCurrentManagersEmployees(request, response);
			break;
		default : response.getWriter().append("your request uri did not match anything "+ uri);break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
