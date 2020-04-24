package dev.rivera.daos;

import java.util.List;

import dev.rivera.entities.*;

public interface ReimbursementDAO {
	public Reimbursement createReimbursement(Reimbursement r);
	public List<Reimbursement> getAllReimbursements();
	public List<Reimbursement> getPendingReimbursements(int id);
	public List<Reimbursement> getApprovedReimbursements(int id);
	public List<Reimbursement> getDeniedReimbursements(int id);
	public Reimbursement getReimbursementbyID(int id);
	public List<Reimbursement> getReimbursementByEmployee(Employee e);
	public String getMostReimbursementMaker();
	public int getAverageReimbursementAmount();
	public int getApprovedReimbursementAmount();
	public int getDeniedReimbursementAmount();
	public String getMostReimbursementMakerforWorkForce(int id);
	public int getAverageReimbursementAmountforWorkForce(int id);
	public int getApprovedReimbursementAmountforWorkForce(int id);
	public int getDeniedReimbursementAmountforWorkForce(int id);
	public Reimbursement UpdateReimbursement(Reimbursement r);
	public boolean DeleteReimbursement(Reimbursement r);
}
