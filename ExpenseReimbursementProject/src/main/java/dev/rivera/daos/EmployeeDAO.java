package dev.rivera.daos;

import dev.rivera.entities.Employee;
import dev.rivera.entities.Manager;

public interface EmployeeDAO {
	
	
	public Employee getEmployeeByID(int id);
	public int getWorkForceBudget(Employee e);
	public Employee getEmployeeByLoginCredentials(String username,String password);
	public Employee UpdateEmployee(Employee e);
	public boolean DeleteEmployee(Employee e);
}
