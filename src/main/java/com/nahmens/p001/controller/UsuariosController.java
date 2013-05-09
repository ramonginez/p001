package com.nahmens.p001.controller;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

import com.nahmens.p001.model.dao.UsuarioDAO;
import com.nahmens.p001.model.dao.UsuarioDAOImpl;

@Controller
public class UsuariosController
{
	private UsuarioDAO userDAO;
	@Autowired
	public void setUserDAO(UsuarioDAOImpl userDAO)
	{
		this.userDAO = userDAO;
	}

	@RequestMapping(value="/usuarios", method = RequestMethod.GET)
	public String listAllUsers(ModelMap model)
	{
		Logger logger = Logger.getLogger(UsuariosController.class);
		logger.debug("listing all users...");
		
		JSONArray usersList = this.userDAO.findAllUsers();
		
		model.addAttribute("usersList", usersList);
		
		logger.debug("all users listed...");
		
		return "usuarios";
	}

	@RequestMapping(value="/usuarios", method = RequestMethod.POST)
	public String newUser(JSONObject user, ModelMap model)
	{
		Logger logger = Logger.getLogger(UsuariosController.class);
		logger.debug("saving user...");
		
		int result = this.userDAO.saveUser(user);
		
		logger.debug("user saved...");
		
		return "usuarios";
	}

	@RequestMapping(value="/usuarios/{hashUser}", method = RequestMethod.GET)
	public String getUser(@PathVariable String hashUser, ModelMap model)
	{
		Logger logger = Logger.getLogger(UsuariosController.class);
		logger.debug("finding user...");
		
		JSONObject user = this.userDAO.findUser(hashUser);
		
		model.addAttribute("user", user);
		
		logger.debug("user found...");
		
		return "usuarios";
	}

	@RequestMapping(value="/usuarios/update", method = RequestMethod.POST)
	public String updateUser(JSONObject user, ModelMap model)
	{
		Logger logger = Logger.getLogger(UsuariosController.class);
		logger.debug("updating user...");
		
		int result = this.userDAO.updateUser(user);
		
		logger.debug("user updated...");
		
		return "usuarios";
	}

	@RequestMapping(value="/usuarios/delete/{hashUser}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable String hashUser, ModelMap model)
	{
		Logger logger = Logger.getLogger(UsuariosController.class);
		logger.debug("deleting user...");
		
		int result = this.userDAO.deleteUser(hashUser);
		
		logger.debug("user deleted...");
		
		return "usuarios";
	}

}