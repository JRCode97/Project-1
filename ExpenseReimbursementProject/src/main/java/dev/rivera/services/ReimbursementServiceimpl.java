package dev.rivera.services;

import java.util.List;

import dev.rivera.daos.ReimbursementDAO;
import dev.rivera.daos.ReimbursementDAOdb;
import dev.rivera.entities.Employee;
import dev.rivera.entities.Reimbursement;

public class ReimbursementServiceimpl implements ReimbursementService {
	ReimbursementDAO rd = new ReimbursementDAOdb();
	@Override 
	public Reimbursement createReimbursement(Reimbursement r) {
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
		reimbursement.setStatus("approved");
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
	public List<Reimbursement> getPendingReimbursements() {
		
		return rd.getPendingReimbursements();
	}
	@Override
	public List<Reimbursement> getDeniedReimbursements() {
		return rd.getDeniedReimbursements();
	}
	@Override
	public List<Reimbursement> getApprovedReimbursements() {
		return rd.getApprovedReimbursements();
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

}
