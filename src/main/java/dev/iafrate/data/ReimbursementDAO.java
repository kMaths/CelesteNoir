package dev.iafrate.data;

import java.util.Set;

import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.User;

public interface ReimbursementDAO {
	
	public Reimbursement createReimbursement(Reimbursement r);
	public Set<Reimbursement> getReimbursementByUser(User user);
	public Set<Reimbursement> getAllReimbursements();
	public Set<Reimbursement> getReimbursementsByStatus(ReimbursementStatus status);
	public boolean updateReimbursementStatus(Reimbursement r, ReimbursementStatus status);
	
}
