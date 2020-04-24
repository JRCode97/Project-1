package dev.rivera.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dev.rivera.daos.*;
import dev.rivera.entities.*;
import dev.rivera.services.*;

public class UserController {
	UserService us = new UserServiceimpl();
public boolean loginEmployee(HttpServletRequest request, HttpServletResponse response) {
		
	String username = (String) request.getSession().getAttribute("username");
	String password = (String)request.getSession().getAttribute("password");
		System.out.println("this is the session"+ request.getSession().getId()+" "+username);
		System.out.println("this is the session"+ request.getSession().getId()+" "+password);
		//String password = (String) request.getAttribute("password");
		Employee e = us.loginEmployee(username, password);
		if(e!=null) {
		System.out.println(e);
		request.getSession().setAttribute("name", e.getName());
		request.getSession().setAttribute("id",Integer.toString(e.geteId()));
		try {
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(e);
			pw.append(json);
			return true;
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}}
		return false;}
public boolean loginManager(HttpServletRequest request, HttpServletResponse response) {
	
	String username = (String) request.getSession().getAttribute("username");
	String password = (String)request.getSession().getAttribute("password");
		System.out.println("this is the session"+ request.getSession().getId()+" "+username);
		System.out.println("this is the session"+ request.getSession().getId()+" "+password);
		//String password = (String) request.getAttribute("password");
		Manager m = us.loginManager(username, password);
		if(m!=null) {
			request.getSession().setAttribute("id", Integer.toString(m.getmId()));
		System.out.println("this is the session still"+ request.getSession().getId()+m);
		try {
			Gson gson = new Gson();
			PrintWriter pw = response.getWriter();
			String json = gson.toJson(m);
			pw.append(json);
			return true;
		} catch (IOException ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}}
		return false;}
}
