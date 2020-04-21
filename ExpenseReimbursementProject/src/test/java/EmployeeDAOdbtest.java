import static org.junit.Assert.*;

import org.junit.Test;

import dev.rivera.daos.*;
import dev.rivera.entities.*;
import dev.rivera.services.*;

public class EmployeeDAOdbtest {
	EmployeeDAO ed = new EmployeeDAOdb();
	UserService us = new UserServiceimpl();
	@Test
	public void getEmployeestest() {
		Employee e = ed.getEmployeeByID(1);
		System.out.println(e);
	}
	@Test 
	public void updateEmployeetest() {
		Employee e = ed.getEmployeeByID(1);
		System.out.println(e);
		e.setName("Jesse Giovanni Rivera");
		ed.UpdateEmployee(e);
		e = ed.getEmployeeByID(1);
		System.out.println(e);
	}
	@Test 
	public void deleteEmployee() {
		Employee e = ed.getEmployeeByID(4);
		ed.DeleteEmployee(e);
	}
	@Test
	public void getEmployeewCredentials() {
		System.out.println(ed.getEmployeeByLoginCredentials("Jrivera97", "password"));
	}
	@Test
	public void login() {
		System.out.println(us.loginEmployee("Jrivera97", "password"));
	}
}
