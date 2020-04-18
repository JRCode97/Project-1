package dev.rivera.entities;

public class Reimbursement {
	private int rId;
	private int amount;
	private String description;
	private int requesterId;
	private String status;
	public Reimbursement(int rId, int amount, String description, int requesterId, String status) {
		super();
		this.rId = rId;
		this.amount = amount;
		this.description = description;
		this.requesterId = requesterId;
		this.status = status;
	}
	public Reimbursement() {
		super();
	}
	@Override
	public String toString() {
		return "Reimbursement [rId=" + rId + ", amount=" + amount + ", description=" + description + ", requesterId="
				+ requesterId + ", status=" + status + "]";
	}
	public int getrId() {
		return rId;
	}
	public void setrId(int rId) {
		this.rId = rId;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRequesterId() {
		return requesterId;
	}
	public void setRequesterId(int requesterId) {
		this.requesterId = requesterId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
