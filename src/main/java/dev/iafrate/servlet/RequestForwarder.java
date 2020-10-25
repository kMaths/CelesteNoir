package dev.iafrate.servlet;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.iafrate.controller.ReimbursementDataController;
import dev.iafrate.controller.SaveController;
import dev.iafrate.controller.UserController;

public class RequestForwarder {
	
	public String routes(HttpServletRequest req) {
		switch (req.getRequestURI()){
		case "/CelesteNoir/login.page":
			return new UserController().login(req);
		case "/CelesteNoir/register.page":
			return new UserController().register(req);
		case "/CelesteNoir/logout.page":
			return new UserController().logout(req);
		default: 
			return "html/landing.html"; //login page
		}
	}
	
	public void data(HttpServletRequest req, HttpServletResponse res) throws IOException {
		switch(req.getRequestURI()) {
		case "/CelesteNoir/all.json":
			new ReimbursementDataController().sendAllData(req, res);
			break;
		case "/CelesteNoir/new.json":
			new SaveController().save(req, res);
			break;
		case "/CelesteNoir/allByStatus.json":
			new ReimbursementDataController().sendAllDataByStatus(req, res);
			break;
		case "/CelesteNoir/allByUser.json":
			new ReimbursementDataController().sendAllDataByUser(req, res);
			break;
		case "/CelesteNoir/update.json":
			new ReimbursementDataController().update(req, res);
		default :
			new UserController().redirectUser(req, res);
		}
	}

}
