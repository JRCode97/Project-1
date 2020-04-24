package dev.rivera.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dev.rivera.controllers.*;
import dev.rivera.entities.Employee;

/**
 * Servlet implementation class ReimbursementServlet
 */
public class ReimbursementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ReimbursementController rc = new ReimbursementController();
    UserController uc = new UserController();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReimbursementServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		String user = request.getParameter("username");
		System.out.println(user);
		String pass = request.getParameter("password");
		System.out.println(pass);
		HttpSession sess = request.getSession();
		sess.setAttribute("username", user);
		System.out.println(sess.getId()+" "+sess.getAttribute("username"));
		sess.setAttribute("password", pass);
		System.out.println(sess.getId()+" "+sess.getAttribute("password"));
		if(uc.loginEmployee(request, response)) {
		
		response.sendRedirect("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com/:8080/ExpenseReimbursementProject/EmployeeHome.jsp");
		}else if(uc.loginManager(request, response)) {
			response.sendRedirect("http://ec2-18-191-60-107.us-east-2.compute.amazonaws.com/:8080/ExpenseReimbursementProject/ManagerHome.jsp");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
