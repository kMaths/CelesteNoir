package dev.iafrate.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.ReimbursementType;
import dev.iafrate.model.User;
import dev.iafrate.utils.ConnectionUtil;

public class ReimbursementPostgres implements ReimbursementDAO {
	
	ConnectionUtil cu;
	UserPostgres up = new UserPostgres();
	
	
	
	public ReimbursementPostgres() {
		super();
		this.cu = ConnectionUtil.getConnectionUtil();
	}

	@Override
	public Reimbursement createReimbursement(Reimbursement r) {
		String sql = "insert into ers_reimbursement (reimb_id , reimb_amount , reimb_submitted , reimb_author , reimb_status_id , reimb_type_id , reimb_description) values"
				+ " (default, ?, current_timestamp, ?, ?, ?, ?)";
		String[] keys = {"reimb_id"};
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement pst = conn.prepareStatement(sql, keys);
			pst.setDouble(1, r.getAmount());
			pst.setInt(2, r.getAuthor().getUserId());
			pst.setInt(3, r.getStatus().getStatusId());
			pst.setInt(4, r.getType().getTypeId());
			pst.setString(5, r.getDescription());
			
			ResultSet rs = pst.getGeneratedKeys();
			if(rs.next()) {
				r.setReimbursementId(rs.getInt(1));
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	@Override
	public Set<Reimbursement> getReimbursementByUser(User user) {
		String sql = "select * from allReimbursments where a_id = ?;";
		Set<Reimbursement> reimbs = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, user.getUserId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				reimbs.add(makeReimbursement(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public Set<Reimbursement> getAllReimbursements() {
		String sql = "select * from allReimbursments;";
		Set<Reimbursement> reimbs = new HashSet<>();
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement pst = conn.prepareStatement(sql);
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				reimbs.add(makeReimbursement(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public Set<Reimbursement> getReimbursementsByStatus(ReimbursementStatus status) {
		String sql = "select * from allReimbursments where status_id = ?;";
		
		Set<Reimbursement> reimbs = new HashSet<>();
		
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, status.getStatusId());
			
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				reimbs.add(makeReimbursement(rs));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return reimbs;
	}

	@Override
	public boolean updateReimbursementStatus(Reimbursement r, ReimbursementStatus status) {
		String sql ="update ers_reimbursement set reimb_status_id = ?, reimb_resolved = current_timestamp, reimb_resolver = ? where reimb_id =?;";
		
		try(Connection conn = cu.getConnection()){
			PreparedStatement pst = conn.prepareStatement(sql);
			
			pst.setInt(1, status.getStatusId());
			pst.setInt(2, r.getResolver().getUserId());
			pst.setInt(3, r.getReimbursementId());
			
			int rs = pst.executeUpdate();
			if (rs == 1) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public ReimbursementStatus getStatusByName(String status) {
		String sql = "select * from ers_reimbursment_status where reimb_status = ?";
		ReimbursementStatus r= new ReimbursementStatus();;
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				r.setStatus(status);
				r.setStatusId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public ReimbursementStatus getStatusById(int id) {
		String sql = "select * from ers_reimbursment_status where reimb_status_id = ?";
		ReimbursementStatus r= new ReimbursementStatus();;
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				r.setStatus(rs.getString(2));
				r.setStatusId(id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	public ReimbursementType getTypeByName(String name) {
		String sql = "select * from ers_reimbursment_type where reimb_type = ?";
		ReimbursementType r= new ReimbursementType();;
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				r.setType(name);
				r.setTypeId(rs.getInt(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	public ReimbursementType getTypeById(int id) {
		String sql = "select * from ers_reimbursment_type where reimb_type_id = ?";
		ReimbursementType r= new ReimbursementType();;
		
		try(Connection conn = cu.getConnection()){
			
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, id);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) {
				r.setTypeId(id);
				r.setType(rs.getString(2));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return r;
	}
	
	public Reimbursement makeReimbursement(ResultSet rs) throws SQLException{
		ReimbursementStatus status = new ReimbursementStatus(rs.getInt("status_id"), rs.getString("status_name"));
		ReimbursementType type = new ReimbursementType(rs.getInt("type_id"), rs.getString("type_name"));
		User author = new User(rs.getInt("a_id"), rs.getString("a_username"), null, null, null, rs.getString("a_email"), null);
		User resolver = new User(rs.getInt("m_id"), rs.getString("m_username"), null, null, null, rs.getString("m_email"), null);
		Reimbursement r = new Reimbursement(rs.getInt("id"), rs.getDouble("amount"), rs.getDate("submitted"), 
				rs.getDate("resolved"), rs.getString("description"), rs.getString("receipt"), author, resolver, status, type);
		return r;
	}

}
