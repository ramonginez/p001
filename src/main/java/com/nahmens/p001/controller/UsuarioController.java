package com.nahmens.p001.controller;

import java.sql.SQLException;

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

		return "usuarios";
	}

	@RequestMapping(value="/"+REST_PATH_UPDATE_USUARIO, method = RequestMethod.POST)
	public String usuariosUpdate(ModelMap model) throws SQLException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("usuariosUpdate");		

		return "redirect:/"+REST_PATH_SETTING;
	}

}