package dev.rivera.services;

import dev.rivera.daos.*;
import dev.rivera.entities.*;

public class UserServiceimpl implements UserService {
	EmployeeDAO ed = new EmployeeDAOdb();
	ManagerDAO md = new ManagerDAOdb();
	@Override
	public Employee loginEmployee(String username,String password) {
		///Employee e = new Employee(0,"jr","pass","jesse");
		return ed.getEmployeeByLoginCredentials(username, password);
	}
	@Override
	public Manager loginManager(String username, String password) {
		return md.getManagerByLoginCredentials(username, password);
	}

}
