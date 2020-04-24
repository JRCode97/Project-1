import static org.junit.Assert.*;

import org.junit.Test;

import dev.rivera.daos.*;
import dev.rivera.entities.*;

public class ManagerDAOdbtest {
	ManagerDAO md = new ManagerDAOdb();

	@Test
	public void getManagerstest() {
		Manager m = md.getManagerByID(1);
		System.out.println(m);
	}
	@Test 
	public void updateManagertest() {
		Manager m = md.getManagerByID(1);
		System.out.println(m);
		m.setPassword("newpword");
		md.UpdateManager(m);
		m = md.getManagerByID(1);
		System.out.println(m);
	}
	@Test
	public void getManagerBudget() {
		Manager m = md.getManagerByID(1);
		int b = md.getManagerBudget(m);
		System.out.println(b);
	}
	@Test 
	public void deleteManager() {
		Manager m = md.getManagerByID(1);
		md.DeleteManager(m);
	}
	@Test 
	public void updateManagerBudget() {
		Manager m = md.getManagerByID(1);
		md.updateManagerBudget(4000000, m);
		int budget = md.getManagerBudget(m);
		System.out.println(budget);
	}
	@Test 
	public void getEmployeesinWorkforce()
	{
		Manager m = md.getManagerByID(2);
		System.out.println(md.getEmployeesfromManagerWorkForce(m));
	}

}
