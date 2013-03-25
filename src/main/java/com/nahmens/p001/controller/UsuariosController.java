package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

@Controller
public class UsuariosController {

	@RequestMapping(value="/usuarios", method = RequestMethod.GET)
	public String usuariosList(ModelMap model) throws SQLException {

		Logger logger = Logger.getLogger(UsuariosController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting proyectoList");		

		return "usuarios";
	}

}