package dev.rivera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.crypto.spec.RC2ParameterSpec;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dev.rivera.entities.Employee;
import dev.rivera.entities.Manager;
import dev.rivera.entities.Reimbursement;
import dev.rivera.services.*;

public class ReimbursementController {
	ReimbursementService rs = new ReimbursementServiceimpl();
	UserService us = new UserServiceimpl();
public void getAllReimbursements(HttpServletRequest request, HttpServletResponse response) {
		
		
		List<Reimbursement> reimbursements = rs.getAllReimbursements();
		System.out.println("list of reimbursements"+reimbursements);
		try {
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(reimbursements);
			pw.append(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}}

public void getManagerName(HttpServletRequest request,HttpServletResponse response) {
	String name = (String)request.getSession().getAttribute("username");
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(name);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void getApprovedReimbursements(HttpServletRequest request, HttpServletResponse response) {
	
	
	List<Reimbursement> reimbursements = rs.getApprovedReimbursements(Integer.parseInt((String)request.getSession().getAttribute("id")));
	System.out.println("list of reimbursements"+reimbursements);
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(reimbursements);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}


public void getDeniedReimbursements(HttpServletRequest request, HttpServletResponse response) {
	
	
	List<Reimbursement> reimbursements = rs.getDeniedReimbursements(Integer.parseInt((String)request.getSession().getAttribute("id")));
	System.out.println("list of reimbursements"+reimbursements);
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(reimbursements);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}

public void getPendingReimbursements(HttpServletRequest request, HttpServletResponse response) {
	
	
	List<Reimbursement> reimbursements = rs.getPendingReimbursements(Integer.parseInt((String)request.getSession().getAttribute("id")));
	System.out.println("list of reimbursements"+reimbursements);
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(reimbursements);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}


public void getSpecificEmployeeReimbursements(HttpServletRequest request, HttpServletResponse response) {
	
	Employee e = us.loginEmployee((String)request.getSession().getAttribute("username"),(String) request.getSession().getAttribute("password"));
	
	List<Reimbursement> reimbursements = rs.getSpecificEmployeeReimbursments(e);
	System.out.println("list of reimbursements"+reimbursements);
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(reimbursements);
		pw.append(json);
	} catch (IOException ex) {
		// TODO Auto-generated catch block
		ex.printStackTrace();
	}}

public void getMostReimbursementMaker(HttpServletRequest request, HttpServletResponse response) {
	
	
	String person = rs.getMostReimbursementMaker();
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(person);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getCurrentManager(HttpServletRequest request, HttpServletResponse response) {
	
	
	Manager m = rs.getCurrentManager(Integer.parseInt((String)request.getSession().getAttribute("id")));
	
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(m);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getCurrentManagersEmployees(HttpServletRequest request, HttpServletResponse response) {
	Manager m = rs.getCurrentManager(Integer.parseInt((String)request.getSession().getAttribute("id")));
	List<Employee> employees = rs.getEmployeesfromManagerWorkForce(m);
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(employees);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void getAverageReimbursementAmount(HttpServletRequest request, HttpServletResponse response) {
	
	
	int avg = rs.getAverageReimbursementAmount();

	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(avg);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getApprovedReimbursementAmount(HttpServletRequest request, HttpServletResponse response) {
	
	
	int approvedAmount = rs.getApprovedReimbursementAmount();

	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(approvedAmount);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getDeniedReimbursementAmount(HttpServletRequest request, HttpServletResponse response) {
	
	
	int deniedAmount = rs.getDeniedReimbursementAmount();

	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(deniedAmount);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getMostReimbursementMakerforWorkForce(HttpServletRequest request, HttpServletResponse response) {
	
	
	String person = rs.getMostReimbursementMakerforWorkForce(Integer.parseInt((String)request.getSession().getAttribute("id")));
	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(person);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getAverageReimbursementAmountforWorkForce(HttpServletRequest request, HttpServletResponse response) {
	
	
	int avg = rs.getAverageReimbursementAmountforWorkForce(Integer.parseInt((String)request.getSession().getAttribute("id")));

	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(avg);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getApprovedReimbursementAmountforWorkForce(HttpServletRequest request, HttpServletResponse response) {
	
	
	int approvedAmount = rs.getApprovedReimbursementAmountforWorkForce(Integer.parseInt((String)request.getSession().getAttribute("id")));

	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(approvedAmount);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void getDeniedReimbursementAmountforWorkForce(HttpServletRequest request, HttpServletResponse response) {
	
	
	int deniedAmount = rs.getDeniedReimbursementAmountforWorkForce(Integer.parseInt((String)request.getSession().getAttribute("id")));

	try {
		Gson gson = new Gson();
		PrintWriter pw = response.getWriter();
		String json = gson.toJson(deniedAmount);
		pw.append(json);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
public void addReimbursement(HttpServletRequest request, HttpServletResponse response) {
	try {
		Gson gson = new Gson();
		String body = request.getReader().lines().reduce("", (accumulator,actual)->accumulator+actual);
		Employee e = us.loginEmployee((String)request.getSession().getAttribute("username"),(String) request.getSession().getAttribute("password"));

		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		reimbursement.setStatus("pending");
		reimbursement.setRequesterId(e.geteId());
		System.out.println(reimbursement);
		rs.createReimbursement(reimbursement);
		response.getWriter().append("Successfully added Reimbursement");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void deleteReimbursement(HttpServletRequest request, HttpServletResponse response) {
	try {
		Gson gson = new Gson();
		String body = request.getReader().lines().reduce("", (accumulator,actual)->accumulator+actual);
		
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		System.out.println("deleting this reimbursement: " +reimbursement);
		rs.deleteReimbursement(reimbursement);
		response.getWriter().append("Successfully deleted Reimbursement");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void approveReimbursement(HttpServletRequest request, HttpServletResponse response) {
	try {
		Gson gson = new Gson();
		String body = request.getReader().lines().reduce("", (accumulator,actual)->accumulator+actual);
		
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		rs.approveReimbursement(reimbursement);
		response.getWriter().append("Successfully approved reimbursement");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void denyReimbursement(HttpServletRequest request, HttpServletResponse response) {
	try {
		Gson gson = new Gson();
		String body = request.getReader().lines().reduce("", (accumulator,actual)->accumulator+actual);
		
		Reimbursement reimbursement = gson.fromJson(body, Reimbursement.class);
		rs.denyReimbursement(reimbursement);
		response.getWriter().append("Success");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
}
