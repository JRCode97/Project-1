package dev.rivera.daos;

import dev.rivera.entities.Employee;

public interface EmployeeDAO {
	
	
	public Employee getEmployeeByID(int id);
	public Employee getEmployeeByLoginCredentials(String username,String password);
	public Employee UpdateEmployee(Employee e);
	public boolean DeleteEmployee(Employee e);
}
