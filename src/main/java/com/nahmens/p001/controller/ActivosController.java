package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

@Controller
public class ActivosController {

	@RequestMapping(value="/proyectos/{hashProyecto}/inventarios/{hashInventario}/activos", method = RequestMethod.GET)
	public String activosList(@PathVariable long hashProyecto,
							  @PathVariable long hashInventario,
							  				ModelMap model) throws SQLException {

		Logger logger = Logger.getLogger(ActivosController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting proyectoList");		

		return "activos";
	}

}