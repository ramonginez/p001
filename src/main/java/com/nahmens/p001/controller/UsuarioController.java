package com.nahmens.p001.controller;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

import com.nahmens.p001.utils.Constants;

@Controller
public class UsuarioController implements Constants {

	@RequestMapping(value="/"+REST_PATH_SETTING, method = RequestMethod.GET)
	public String usuariosShow(ModelMap model) throws SQLException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("usuariosShow");		

		
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      
	    Collection<GrantedAuthority> authority = auth.getAuthorities();
	    
	    for (GrantedAuthority granted : authority) {

            if (granted.getAuthority().equalsIgnoreCase(ROLE_ADMIN)) {
            	
            	
        		logger.debug("Admin Request");
        		
        		return "redirect:/"+REST_PATH_ADMIN_SETTING;


            }
        
	    }
		
	 		
		return VIEW_USUARIOS;
	}

	@RequestMapping(value="/"+REST_PATH_UPDATE_USUARIO, method = RequestMethod.POST)
	public String usuariosUpdate(ModelMap model) throws SQLException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("usuariosUpdate");		

		return "redirect:/"+REST_PATH_SETTING;
	}

	
	@RequestMapping(value="/"+REST_PATH_ADMIN_SETTING, method = RequestMethod.GET)
	public String adminUsuariosShow(ModelMap model) throws SQLException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("adminUsuariosShow");		
	   
	 		
		return VIEW_USUARIOS_ADMIN;
	}
}