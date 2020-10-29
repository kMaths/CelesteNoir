package dev.iafrate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.iafrate.data.UserPostgres;

import dev.iafrate.model.User;
import dev.iafrate.service.UserService;

public class UserController {
	ObjectMapper om = new ObjectMapper();
	UserPostgres up = new UserPostgres();
	
	UserService us = new UserService();

	public String redirectUser(HttpServletRequest req) {
		User u = (User) req.getSession().getAttribute("user");
		if (u.getRole().getRole().equals("employee")) {
			return "html/new.html";
		} else {
			return "html/all.html";
		}
	}

	public String login(HttpServletRequest req) {
		
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		User u = us.validateUser(username, password);
		if(u == null ) {
			return "landing.page";
		} else {
			req.getSession().setAttribute("user", u);
			return redirectUser(req);
		}
		
	}

	public String logout(HttpServletRequest req) {
		// TODO Auto-generated method stub
		return null;
	}

	public String register(HttpServletRequest req) throws JsonParseException, JsonMappingException, IOException {

		User user = om.readValue(req.getInputStream(), User.class);
		user = us.createUser(user);
		return "landing.page";
	}

}
