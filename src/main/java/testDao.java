import java.util.Set;

import dev.iafrate.data.ReimbursementPostgres;
import dev.iafrate.data.UserPostgres;
import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.Role;
import dev.iafrate.model.User;

public class testDao {

	public static void main(String[] args) {
		UserPostgres up = new UserPostgres();
		ReimbursementPostgres rp = new ReimbursementPostgres();
		User user1 = new User(29, "kelsey3", "40c8a0b053ee4dbe9e579ef46c6ea878", "kelsey","iafrate",
				"kiafrate3@gmail.com", new Role(1,"employee"));
		
		Set<Reimbursement> r = rp.getAllReimbursements();
		printSet(r);
		Set<Reimbursement> r1 = rp.getReimbursementByUser(user1);
		printSet(r1);
		ReimbursementStatus rS0 = new ReimbursementStatus(1, "pending");
		Set<Reimbursement> r2 = rp.getReimbursementsByStatus(rS0);
		printSet(r2);
		
		System.out.println(up.confirmCredentials("kelsey3", "password"));

	}
	
	public static void printSet(Set<Reimbursement> r) {
		for(Reimbursement r1 : r) {
			System.out.println(r1);
		}
	}
}

