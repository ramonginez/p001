package com.nahmens.p001.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
@Controller
public class LoginController {
 
	
 
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String login(ModelMap model) {
 
		Logger logger = Logger.getLogger(LoginController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting login");		
		
		return "login";
 
	}
 
	@RequestMapping(value="/loginfailed", method = RequestMethod.GET)
	public String loginerror(ModelMap model) {
 
		Logger logger = Logger.getLogger(LoginController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting loginfailed");		
		
		model.addAttribute("error", "true");
		
		return "login";
 
	}
 
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {
 

		Logger logger = Logger.getLogger(LoginController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting logout");		
		
		return "login";
 
	}
 
}