package dev.iafrate.data;

import java.util.Set;

import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.User;

public class ReimbursementPostgres implements ReimbursementDAO {

	@Override
	public int createReimbursement(Reimbursement r) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Set<Reimbursement> getReimbursementByUser(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Reimbursement> getAllReimbursements() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Reimbursement> getReimbursementsByStatus() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateReimbursementStatus(Reimbursement r, String status) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ReimbursementStatus getStatusByName(String status) {
		// TODO Auto-generated method stub
		return null;
	}

}
