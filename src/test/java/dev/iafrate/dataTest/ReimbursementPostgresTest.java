package dev.iafrate.dataTest;


import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import dev.iafrate.data.ReimbursementPostgres;
import dev.iafrate.data.UserPostgres;
import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.ReimbursementType;
import dev.iafrate.model.Role;
import dev.iafrate.model.User;
import dev.iafrate.utils.ConnectionUtil;

public class ReimbursementPostgresTest {

	User user1;
	User user2;
	UserPostgres upM;
	Reimbursement r0;
	Reimbursement r1;
	Set<Reimbursement> r2;
	Set<Reimbursement> r3;
	ReimbursementPostgres rpg;
	ReimbursementPostgres rpgM;
	ReimbursementStatus rS0;
	ReimbursementType rT0;
	
	
	
	@BeforeClass
	public static void beforeAll() {
		ConnectionUtil cu = ConnectionUtil.getConnectionUtil();
		try(Connection conn = cu.getConnection()){
			conn.close();
		} catch (SQLException e) {
			System.exit(500);
		}
		
	}
	@Before
	public void setup() {
		
		user1 = new User(29, "kelsey3", "40c8a0b053ee4dbe9e579ef46c6ea878", "kelsey","iafrate",
				"kiafrate3@gmail.com", new Role(1,"employee"));
		user2 = new User(42, "kelsey", "6d6bb2434d390b1b0bfc84a151d118f6",  "kelsey","iafrate",
				"kiafrate2@gmail.com", new Role(2, "manager"));
		upM = Mockito.mock(UserPostgres.class);
		rS0 = new ReimbursementStatus(1, "pending");
		rT0 = new ReimbursementType(1, "lodging");
		rpg = new ReimbursementPostgres();
	}
	
	public void getReimbursementByUserTest() {
		r2 = rpg.getReimbursementByUser(user1);
		assertEquals(r2, null);
		
	}

	public void getAllReimbursementsTest() {
		// TODO Auto-generated method stub
		
	}

	public void getReimbursementsByStatusTest() {
		
	}

	public void updateReimbursementStatus() {

	}

	@Test
	public void getStatusByNameTest() {
		ReimbursementStatus rs1 = rpg.getStatusByName("pending");
		assertEquals(rS0, rs1);
	}
	
	@Test
	public void getStatusByIdTest() {
		ReimbursementStatus rs1 = rpg.getStatusById(1);
		assertEquals(rS0, rs1);
	}	
	@Test	
	public void getTypeByNameTest() {
		ReimbursementType rT1 = rpg.getTypeByName("lodging");
		assertEquals(rT0, rT1);
	}
	@Test
	public void getTypeByIdTest() {
		ReimbursementType rT1 = rpg.getTypeById(1);
		assertEquals(rT0, rT1);
	}


}