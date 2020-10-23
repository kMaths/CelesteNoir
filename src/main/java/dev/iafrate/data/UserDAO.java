package dev.iafrate.data;

import dev.iafrate.model.User;

public interface UserDAO {
	public User confirmCredentials(String username, String password);
	public int createUser(User user);
}
