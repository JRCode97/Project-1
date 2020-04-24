package dev.rivera.services;

import java.util.List;

import dev.rivera.entities.Employee;
import dev.rivera.entities.Manager;
import dev.rivera.entities.Reimbursement;

public interface ReimbursementService {
	public Reimbursement createReimbursement(Reimbursement r);
	
	public List<Reimbursement> getAllReimbursements();
	
	public List<Reimbursement> getPendingReimbursements(int id);
	
	public List<Reimbursement> getDeniedReimbursements(int id);
	
	public List<Reimbursement> getApprovedReimbursements(int id);
	
	public List<Reimbursement> getSpecificEmployeeReimbursments(Employee e);
	
	public int getApprovedReimbursementAmount();
	
	public int getDeniedReimbursementAmount();
	
	public int getAverageReimbursementAmount();
	
	public String getMostReimbursementMaker();
	
	public int getApprovedReimbursementAmountforWorkForce(int id);
	
	public int getDeniedReimbursementAmountforWorkForce(int id);
	
	public int getAverageReimbursementAmountforWorkForce(int id);
	
	public String getMostReimbursementMakerforWorkForce(int id);
	
	public Manager getCurrentManager(int id);
	
	public List<Employee> getEmployeesfromManagerWorkForce(Manager m);
	
	public Reimbursement submitReimbursement(Reimbursement reimbursement);
	
	public boolean deleteReimbursement(Reimbursement reimbursement);
	
	public Reimbursement approveReimbursement(Reimbursement reimbursement);
	
	public Reimbursement denyReimbursement(Reimbursement reimbursement);
	
	
}
