package dev.iafrate.data;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dev.iafrate.model.Role;
import dev.iafrate.model.User;
import dev.iafrate.utils.ConnectionUtil;

public class UserPostgres implements UserDAO {
	
	private ConnectionUtil cu;
	
	
	public UserPostgres(ConnectionUtil cu) {
		super();
		this.cu = cu.getConnectionUtil();
	}

	@Override
	public User confirmCredentials(String username, String password) {
		String sql = "{select validateUser(?,?)}";
		User u = null;
		
		try(Connection conn = cu.getConnection()){
			
			CallableStatement cs = conn.prepareCall(sql);
			cs.setString(1, password);
			cs.setString(2, username);
			
			ResultSet rs = cs.executeQuery();
			if(rs.next()) {
				Role r = getRoleById(rs.getInt(7));
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), r);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int createUser(User user) {
		String sql = "insert into ers_users values (default,?,?,?,?,?,?)";
		String[] keys = {"ers_users_id"};
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement pst = conn.prepareStatement(sql, keys);
			
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPassword());
			pst.setString(3, user.getFirstName());
			pst.setString(4, user.getLastName());
			pst.setString(5, user.getEmail());
			pst.setInt(6, user.getRole().getRoleId());
			
			pst.executeUpdate();
			
			ResultSet rs = pst.getGeneratedKeys();
			
			if(rs.next()) {
				return rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public Role getRoleById(int id) {
		return null;
	}
	
	public Role getRoleByName(String name) {
		return null;
	}

}
