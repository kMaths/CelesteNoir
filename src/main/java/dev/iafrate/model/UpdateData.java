package dev.iafrate.model;

public class UpdateData {
	private Reimbursement reimb;
	private String newStatus;
	public Reimbursement getReimb() {
		return reimb;
	}
	public void setReimb(Reimbursement reimb) {
		this.reimb = reimb;
	}
	public String getNewStatus() {
		return newStatus;
	}
	public void setNewStatus(String newStatus) {
		this.newStatus = newStatus;
	}
	@Override
	public String toString() {
		return "UpdateData [reimb=" + reimb + ", newStatus=" + newStatus + "]";
	}
	public UpdateData(Reimbursement reimb, String newStatus) {
		super();
		this.reimb = reimb;
		this.newStatus = newStatus;
	}
	public UpdateData() {
		super();
	}	
}