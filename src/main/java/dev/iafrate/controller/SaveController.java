package dev.iafrate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.iafrate.data.ReimbursementDAO;
import dev.iafrate.data.ReimbursementPostgres;
import dev.iafrate.data.UserPostgres;
import dev.iafrate.model.NewReimb;
import dev.iafrate.model.Reimbursement;
import dev.iafrate.model.Role;
import dev.iafrate.model.UpdateData;
import dev.iafrate.model.User;
import dev.iafrate.service.ReimbursementService;

public class SaveController {
	
	ObjectMapper om = new ObjectMapper();
	ReimbursementService rs = new ReimbursementService();

	public void save(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		
		User u = (User) req.getSession().getAttribute("user");
		NewReimb r = om.readValue(req.getInputStream(), NewReimb.class);
		rs.newReimb(r, u);
		res.setStatus(200);
		res.getWriter().println("The user was created.");
		
	}
	
	

}
