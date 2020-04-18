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
	public void deleteEmployee() {
		Manager m = md.getManagerByID(1);
		md.DeleteManager(m);
	}

}
