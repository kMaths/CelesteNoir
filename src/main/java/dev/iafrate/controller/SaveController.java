package dev.iafrate.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import dev.iafrate.data.UserPostgres;
import dev.iafrate.model.Role;
import dev.iafrate.model.User;

public class SaveController {
	
	

	public void save(HttpServletRequest req, HttpServletResponse res) throws JsonParseException, JsonMappingException, IOException {
		
	}

}
