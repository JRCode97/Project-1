package dev.rivera.services;

import java.util.List;

import dev.rivera.entities.Reimbursement;

public interface ReimbursementService {
	public Reimbursement createReimbursement(Reimbursement r);
	
	public List<Reimbursement> getAllReimbursements();
	
	public Reimbursement submitReimbursement(Reimbursement reimbursement);
	
	public Reimbursement approveReimbursement(Reimbursement reimbursement);
	
	public Reimbursement denyReimbursement(Reimbursement reimbursement);
	
	
}
