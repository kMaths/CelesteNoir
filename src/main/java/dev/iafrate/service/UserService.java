package dev.iafrate.service;

import dev.iafrate.data.UserDAO;
import dev.iafrate.data.UserPostgres;
import dev.iafrate.model.Role;
import dev.iafrate.model.User;

public class UserService {
	
	private UserDAO ud;
	
	
	public UserService() {
		this.ud = new UserPostgres();
	}

	public User validateUser(String username, String password) {
		return ud.confirmCredentials(username, password);
	}

	public User createUser(User user) {
		Role role = ud.getRoleByName("employee");
		user.setRole(role);
		return ud.createUser(user);
	}


	


}
