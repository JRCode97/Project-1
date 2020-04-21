package dev.rivera.daos;

import java.util.List;

import dev.rivera.entities.*;

public interface ReimbursementDAO {
	public Reimbursement createReimbursement(Reimbursement r);
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getPendingReimbursements();
	public List<Reimbursement> getApprovedReimbursements();
	public List<Reimbursement> getDeniedReimbursements();
	public Reimbursement getReimbursementbyID(int id);
	public List<Reimbursement> getReimbursementByEmployee(Employee e);
	public String getMostReimbursementMaker();
	public int getAverageReimbursementAmount();
	public int getApprovedReimbursementAmount();
	public int getDeniedReimbursementAmount();

	public Reimbursement UpdateReimbursement(Reimbursement r);
	public boolean DeleteReimbursement(Reimbursement r);
}
