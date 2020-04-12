package dev.rivera.daos;

import java.util.List;

import dev.rivera.entities.*;

public interface ReimbursementDAO {
	public Reimbursement createReimbursement(Reimbursement r);
	public List<Reimbursement> getAllReimbursements();
	public Reimbursement getReimbursementbyID(int id);
	public List<Reimbursement> getReimbursementByEmployee(Employee e);
	public Reimbursement UpdateReimbursement(Reimbursement r);
	public boolean DeleteReimbursement(Reimbursement r);
}
