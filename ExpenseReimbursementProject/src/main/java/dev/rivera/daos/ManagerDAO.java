package dev.rivera.daos;

import java.util.List;

import dev.rivera.entities.*;

public interface ManagerDAO {
	
	public Manager getManagerByID(int id);
	public Manager getManagerByLoginCredentials(String username,String password);
	public int getManagerBudget(Manager m);
	public List<Employee> getEmployeesfromManagerWorkForce(Manager m);
	public boolean updateManagerBudget(int b,Manager m);
	public Manager UpdateManager(Manager m);
	public boolean DeleteManager(Manager m);
}
