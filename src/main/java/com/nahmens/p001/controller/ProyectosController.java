package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

import com.nahmens.p001.datacontroller.MysqlDataController;


@Controller
public class ProyectosController
{
	@RequestMapping(value="/proyectos", method = RequestMethod.GET)
	public String proyectosList(ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(ProyectosController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("Starting proyectoList");		
		
		MysqlDataController msqlController = new MysqlDataController();
		
		JSONArray proyectos = msqlController.getProyectos();
		
		model.addAttribute("proyectos", proyectos);
		
		return "proyectos";
 
	}

}