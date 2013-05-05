package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;

import com.nahmens.p001.datacontroller.MysqlDataController;
import com.nahmens.p001.utils.Constants;

@Controller
public class InventarioController implements Constants {

	Logger _logger = Logger.getLogger(ProyectoController.class);
	
	
	/*
	 * LISTING ALL THE PROJECTS
	 */
	@RequestMapping(value="/"+REST_PATH_LIST_INVENTARIO, method = RequestMethod.GET)
	public String inventariosList(@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME)  String name ,
							  				ModelMap model) throws Exception {

		_logger.debug("Starting "+REST_PATH_LIST_INVENTARIO);		

		MysqlDataController msqlController = new MysqlDataController();
		
		JSONArray inventarios = msqlController.getActivos(name);
		
		model.addAttribute(PARAMETER_KEY_ACTIVOS, inventarios);
		
		model.addAttribute(PARAMETER_KEY_PROYECTO_NAME, name);
		
		return VIEW_INVENTARIO;
 
	}
	
	/*
	 * SEARCH Inventario TODO: THE SEARCHS ARE RETURNING NULL
	 */	
	@RequestMapping(value="/"+REST_PATH_SEARCH_INVENTARIO, method = RequestMethod.GET)
	public String searchProyecto(@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME)  String name, @RequestParam(PARAMETER_KEY_PROYECTO_SEARCH_KEY)  String value,ModelMap model) throws SQLException, JSONException {


		_logger.debug("Starting "+ REST_PATH_SEARCH_INVENTARIO);	
		
		if( value==null|| value.length()==0){

			
			return "redirect:/"+REST_PATH_LIST_INVENTARIO.replace("{"+PARAMETER_KEY_PROYECTO_NAME+"}", name);

		}

		MysqlDataController msqlController = new MysqlDataController();
		
		JSONArray inventarios = msqlController.buscarActivos(name,value);
		
		model.addAttribute(PARAMETER_KEY_ACTIVOS, inventarios);
		
		model.addAttribute(PARAMETER_KEY_PROYECTO_NAME, name);
		
		return VIEW_INVENTARIO;
		
	}
}