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
		this.cu = ConnectionUtil.getConnectionUtil();
	}
	
	public UserPostgres() {
		cu = ConnectionUtil.getConnectionUtil();
	}
	


	@Override
	public User confirmCredentials(String username, String password) {
		String sql = "{call validateUser(?,?)}";
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
		return u;
	}

	@Override
	public User createUser(User user) {
		String sql = "insert into ers_users values (default,?,?,?,?,?,?)";
		String[] keys = {"ers_users_id","ers_password"};
		
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
				user.setUserId(rs.getInt(1));
				if(rs.next()) {
					user.setPassword(rs.getString(2));
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public Role getRoleByName(String name) {
		String sql = "select * from ers_user_roles where user_role = ?";
		Role r= new Role();;
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				r.setRole(name);
				r.setRoleId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public Role getRoleById(int id) {
		String sql = "select * from ers_user_roles where ers_user_role_id = ?";
		Role r= new Role();;
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				r.setRoleId(id);
				r.setRole(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public boolean deleteUser(User user) {
		String sql = "delete from ers_users eu where eu.ers_password = ?;";
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, user.getPassword());
			
			int rs = pst.executeUpdate();
			if (rs == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
