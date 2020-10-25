package dev.iafrate.dataTest;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;

import dev.iafrate.data.UserPostgres;
import dev.iafrate.model.Role;
import dev.iafrate.model.User;
import dev.iafrate.utils.ConnectionUtil;

public class UserPostgresTest {

	User user0;
	User user1;
	User user2;
	UserPostgres up1;
	UserPostgres upM;
	
	Role role;
	
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
		up1 = new UserPostgres();
		role = new Role(2, "manager");
		user0 = new User(3, "kelsey1", "882baec383c3991f46585cc4a2becbb4",
				"kelsey", "iafrate", "kiafrate1@email.com", role);
		upM = Mockito.mock(UserPostgres.class);
		user1 = new User(0, "kelsey", "password", "kelsey","iafrate","kiafrate@gmail.com",role);
		user2 = new User(0, "kelsey", "50f48c00e1534a29304709b4f136e50f",  "kelsey","iafrate","kiafrate@gmail.com",role);
	}
	
	
	
	@Test
	public void confirmCredentialsTest() {
		
		Mockito.when(upM.getRoleById(2)).thenReturn(role);
		User u = up1.confirmCredentials("kelsey1", "password");
		assertEquals(u,user0);	
	}
	
	@Test
	public void getRoleByIdTest() {
		
		Role r = up1.getRoleById(2);
		assertEquals(role, r);
	}
	
	@Test
	public void getRoleByNameTest() {
		
		Role r = up1.getRoleByName("manager");
		assertEquals(role, r);
	}
	
	@Test
	public void createUserTest() {
		User u = up1.createUser(user1);
		if(u.getUserId() !=0) {
			user2.setPassword(u.getPassword());
			u.setUserId(0);
			assertEquals(u, user2);
		} else {
			assertTrue(false);
		}
	}
	
	@Test
	public void deleteUserTest() {
		assertTrue(up1.deleteUser(user2));
	}
	
}

//
//UserService us;
//DaoInstance dao;
//
//@Before
//public void setup() {
//	dao = Mockito.mock(DaoInstance.class);
//	us = new UserService(dao);
//}
//
//@Test
//public void testCheckCreds() {
//	Mockito.when(dao.findByUsername("sarah")).thenReturn(new User("sarah","p4ssw0rd",0));
//	boolean b1 = us.checkCreds("sarah", "p4ssw0rd");
//	assertTrue(b1);
//}
//}