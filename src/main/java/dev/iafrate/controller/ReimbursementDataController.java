package dev.iafrate.controller;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.ReimbursementStatus;
import dev.iafrate.model.User;
import dev.iafrate.service.ReimbursementService;

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

	public void update(HttpServletRequest req, HttpServletResponse res) {
		// TODO Auto-generated method stub
		
	}

}
