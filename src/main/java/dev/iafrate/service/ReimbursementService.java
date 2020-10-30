package dev.iafrate.service;

import java.util.Set;

import dev.iafrate.data.ReimbursementDAO;
import dev.iafrate.data.ReimbursementPostgres;
import dev.iafrate.model.NewReimb;
import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.ReimbursementType;
import dev.iafrate.model.UpdateData;
import dev.iafrate.model.User;

public class ReimbursementService {
	
	private ReimbursementPostgres rd;

	public ReimbursementService() {
		this(new ReimbursementPostgres());
	}
	public ReimbursementService(ReimbursementPostgres rd) {
		super();
		this.rd = rd;
	}
	public Set<Reimbursement> getAll() {
		return rd.getAllReimbursements();
	}
	public Set<Reimbursement> getByUser(User user) {
		return rd.getReimbursementByUser(user);
	}
	public Set<Reimbursement> getAllByStatus(String statusName) {
		ReimbursementStatus status = rd.getStatusByName(statusName);
		return rd.getReimbursementsByStatus(status);
	}

	public boolean update(Reimbursement r, User u, String newStatus) {
		r.setResolver(u);
		ReimbursementStatus status = rd.getStatusByName(newStatus);
		return rd.updateReimbursementStatus(r, status);
	}
	public void newReimb(NewReimb r, User u) {
		Reimbursement reimb = new Reimbursement();
		reimb.setAmount(r.getAmount());
		reimb.setDescription(r.getDescription());
		String type = r.getType();
		ReimbursementType t = rd.getTypeByName(type);
		reimb.setType(t);
		reimb.setAuthor(u);
		rd.createReimbursement(reimb);
	}
	
	

}
