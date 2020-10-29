package dev.iafrate.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.User;
import dev.iafrate.service.ReimbursementService;
import sun.security.provider.certpath.ResponderId;

public class ReimbursementDataController {

	private ObjectMapper om = new ObjectMapper();
	private ReimbursementService rs;
	
	
	public ReimbursementDataController() {
		this(new ReimbursementService());
	}

	public ReimbursementDataController(ReimbursementService rs) {
		super();
		this.rs = rs;
	}

	public void sendAllDataByUser(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		User user = (User) req.getSession().getAttribute("user");
		Set<Reimbursement> reimbs = rs.getByUser(user);
		res.getWriter().write(om.writeValueAsString(reimbs));
	}

	public void sendAllDataByStatus(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
				
		String status =  req.getParameter("statusName");
		Set<Reimbursement> reimbs = rs.getAllByStatus(status);
		res.getWriter().write(om.writeValueAsString(reimbs));
		
	}

	public void sendAllData(HttpServletRequest req, HttpServletResponse res) throws JsonProcessingException, IOException {
		Set<Reimbursement> reimbs = rs.getAll();
		res.getWriter().write(om.writeValueAsString(reimbs));
		
		
	}

	public void update(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		// asyncFetch("http://localhost:8080/CelesteNoir/update.json?reimb="+id+"&newStatus="+message);
		UpdateData data = om.readValue(req.getInputStream(), UpdateData.class);
		
		User u = (User) req.getSession().getAttribute("user");
		System.out.println(data.reimb);
		System.out.println(data.newStatus);
		boolean update = rs.update(data.reimb, u, data.newStatus);
		if (update) {
			res.setStatus(200);
		} else {
			res.sendError(500);
		}
		
	}
	public static class UpdateData {
		private Reimbursement reimb;
		private String newStatus;
		public Reimbursement getReimb() {
			return reimb;
		}
		public void setReimb(Reimbursement reimb) {
			this.reimb = reimb;
		}
		public String getNewStatus() {
			return newStatus;
		}
		public void setNewStatus(String newStatus) {
			this.newStatus = newStatus;
		}
		@Override
		public String toString() {
			return "UpdateData [reimb=" + reimb + ", newStatus=" + newStatus + "]";
		}
		public UpdateData(Reimbursement reimb, String newStatus) {
			super();
			this.reimb = reimb;
			this.newStatus = newStatus;
		}
		public UpdateData() {
			super();
		}
		
		
		
		
		
	}

}
