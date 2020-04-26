package dev.rivera.services;

import java.util.List;

import dev.rivera.daos.*;

import dev.rivera.entities.*;

public class ReimbursementServiceimpl implements ReimbursementService {
	ReimbursementDAO rd = new ReimbursementDAOdb();
	EmployeeDAO ed = new EmployeeDAOdb();
	ManagerDAO md = new ManagerDAOdb();
	WorkForceDAO wfd = new WorkForceDAOdb();
	@Override 
	public Reimbursement createReimbursement(Reimbursement r) {
		Employee e =ed.getEmployeeByID(r.getRequesterId());
		int budget = ed.getWorkForceBudget(e);
		int leftOver = budget - r.getAmount();

		if(leftOver < 0) 
			r.setStatus("denied");
		return rd.createReimbursement(r);
	}
	@Override
	public List<Reimbursement> getAllReimbursements(){
		return rd.getAllReimbursements();
	}
	@Override
	public Reimbursement submitReimbursement(Reimbursement reimbursement) {
		reimbursement = rd.createReimbursement(reimbursement);
		return reimbursement;
	}

	@Override
	public Reimbursement approveReimbursement(Reimbursement reimbursement) {
		Employee e =ed.getEmployeeByID(reimbursement.getRequesterId());
		int budget = ed.getWorkForceBudget(ed.getEmployeeByID(reimbursement.getRequesterId()));
		int leftOver = budget - reimbursement.getAmount();
		if(leftOver > 0){
			WorkForce wf = new WorkForce(e.getWorkForce(), leftOver);
			wfd.updateWorkForce(wf);
			reimbursement.setStatus("approved");}
			
		else 
			reimbursement.setStatus("denied");
		reimbursement = rd.UpdateReimbursement(reimbursement);
		return reimbursement;
	}

	@Override
	public Reimbursement denyReimbursement(Reimbursement reimbursement) {
		reimbursement.setStatus("denied");
		reimbursement = rd.UpdateReimbursement(reimbursement);
		return reimbursement;
	}
	@Override
	public List<Reimbursement> getSpecificEmployeeReimbursments(Employee e) {
		return rd.getReimbursementByEmployee(e);
	}
	@Override
	public boolean deleteReimbursement(Reimbursement reimbursement) {
		
		return rd.DeleteReimbursement(reimbursement);
	}
	@Override
	public List<Reimbursement> getPendingReimbursements(int id) {
		
		return rd.getPendingReimbursements(id);
	}
	@Override
	public List<Reimbursement> getDeniedReimbursements(int id) {
		return rd.getDeniedReimbursements(id);
	}
	@Override
	public List<Reimbursement> getApprovedReimbursements(int id) {
		return rd.getApprovedReimbursements(id);
	}
	@Override
	public int getApprovedReimbursementAmount() {
		return rd.getApprovedReimbursementAmount();
	}
	@Override
	public int getDeniedReimbursementAmount() {
		return rd.getDeniedReimbursementAmount();
	}
	@Override
	public int getAverageReimbursementAmount() {
		return rd.getAverageReimbursementAmount();
	}
	@Override
	public String getMostReimbursementMaker() {
		return rd.getMostReimbursementMaker();
	}
	@Override
	public int getApprovedReimbursementAmountforWorkForce(int id) {

		return rd.getApprovedReimbursementAmountforWorkForce(id);
	}
	@Override
	public int getDeniedReimbursementAmountforWorkForce(int id) {
		return rd.getDeniedReimbursementAmountforWorkForce(id);
	}
	@Override
	public int getAverageReimbursementAmountforWorkForce(int id) {
		return rd.getAverageReimbursementAmountforWorkForce(id);
	}
	@Override
	public String getMostReimbursementMakerforWorkForce(int id) {
		return rd.getMostReimbursementMakerforWorkForce(id);
	}
	@Override
	public List<Employee> getEmployeesfromManagerWorkForce(Manager m) {
		return md.getEmployeesfromManagerWorkForce(m);
	}
	@Override
	public Manager getCurrentManager(int id) {
		
		return md.getManagerByID(id);
	}

}
