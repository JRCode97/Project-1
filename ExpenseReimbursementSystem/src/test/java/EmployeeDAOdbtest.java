import static org.junit.Assert.*;

import org.junit.Test;

import dev.rivera.daos.*;
import dev.rivera.entities.*;

public class EmployeeDAOdbtest {
	EmployeeDAO ed = new EmployeeDAOdb();
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
		Employee e = ed.getEmployeeByID(1);
		ed.DeleteEmployee(e);
	}

}
