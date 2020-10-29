package dev.iafrate.data;

import dev.iafrate.model.Role;
import dev.iafrate.model.User;

public interface UserDAO {
	public User confirmCredentials(String username, String password);
	public User createUser(User user);
	public Role getRoleByName(String string);
}
