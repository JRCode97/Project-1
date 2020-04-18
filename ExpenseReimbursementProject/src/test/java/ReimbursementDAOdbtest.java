import static org.junit.Assert.*;

import java.util.*;

import org.junit.Test;

import dev.rivera.entities.*;
import dev.rivera.daos.*;

public class ReimbursementDAOdbtest {
	ReimbursementDAO rd = new ReimbursementDAOdb();
	EmployeeDAO ed = new EmployeeDAOdb();
	@Test
	public void createReimbursement() {
		Reimbursement r = new Reimbursement(0,250,"Oracle Java Certification",2,"pending");
		System.out.println(r);
		r = rd.createReimbursement(r);
		System.out.println(r);
	}
	@Test
	public void getAllReimbursementstest() {
		List<Reimbursement> list = rd.getAllReimbursements();
		System.out.println(list);
	}
	@Test
	public void getReimbursementsbyUsertest() {
		
		Employee e = ed.getEmployeeByID(2);
		List<Reimbursement> list = rd.getReimbursementByEmployee(e);
		System.out.println(list);
	}
	@Test
	public void getReimbursementbyIDtest() {
		Reimbursement r = rd.getReimbursementbyID(1);
		System.out.println(r);
	}
	@Test
	public void updateReimbursementtest() {
		Reimbursement r = rd.getReimbursementbyID(1);
		System.out.println(r);
		r.setStatus("approved");
		r=rd.UpdateReimbursement(r);
		System.out.println(r);
	}
	@Test
	public void deleteReimbursementtest() {
		Reimbursement r = rd.getReimbursementbyID(2);
		rd.DeleteReimbursement(r);
	}

}
