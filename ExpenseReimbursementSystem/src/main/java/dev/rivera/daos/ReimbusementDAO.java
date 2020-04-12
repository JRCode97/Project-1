package dev.rivera.daos;

import java.util.List;

import dev.rivera.entities.*;

public interface ReimbusementDAO {
	public List<Reimbursement> getAllReimbursements();
	public Reimbursement getReimbursementbyID(int id);
	public Reimbursement getReimbursementByEmployee(Employee e);
	public Reimbursement UpdateReimbursement(Reimbursement r);
	public boolean DeleteReimbursement(Reimbursement r);
}
