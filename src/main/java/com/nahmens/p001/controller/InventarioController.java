package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;

import com.nahmens.p001.datacontroller.MysqlDataController;
import com.nahmens.p001.model.dao.ActivoDAO;
import com.nahmens.p001.model.dao.ActivoDAOImpl;
import com.nahmens.p001.utils.Constants;

@Controller
public class InventarioController implements Constants
{
	private ActivoDAO activoDAO;
	@Autowired
	public void setActivoDAO(ActivoDAOImpl activoDAO) {
		this.activoDAO = activoDAO;
	}

	Logger _logger = Logger.getLogger(InventarioController.class);
	
	/*
	 * LISTING ALL THE INVENTARIOS
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
	 * SEARCH INVENTARIO
	 */	
	@RequestMapping(value="/"+REST_PATH_SEARCH_INVENTARIO, method = RequestMethod.GET)
	public String searchInventario(	@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME) String name,
									@RequestParam(PARAMETER_KEY_PROYECTO_SEARCH_KEY)String value,
									ModelMap model) throws SQLException, JSONException
	{
		_logger.debug("Starting "+ REST_PATH_SEARCH_INVENTARIO);	
		
		if( value == null || value.length() == 0)
		{
			return "redirect:/"+REST_PATH_LIST_INVENTARIO.replace("{"+PARAMETER_KEY_PROYECTO_NAME+"}", name);
		}
		
		JSONArray inventarios = activoDAO.buscarActivos(name,value);
		
		model.addAttribute(PARAMETER_KEY_ACTIVOS, inventarios);
		model.addAttribute(PARAMETER_KEY_PROYECTO_NAME, name);
		
		return VIEW_INVENTARIO;
	}
	
	
	/*
	 * DELETE INVENTARIO
	 */
	@RequestMapping(value="/"+REST_PATH_DELETE_INVENTARIO, method = RequestMethod.POST)
	public String deleteInventario(@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME)  String name,
			@RequestParam(PARAMETER_KEY_INVENTARIO_ID)  String value,ModelMap model) throws SQLException, JSONException {

		_logger.debug("Starting "+ REST_PATH_DELETE_INVENTARIO);		

		MysqlDataController msqlController = new MysqlDataController();
		
		msqlController.removeActivo(value);
		
		return "redirect:/"+REST_PATH_LIST_INVENTARIO.replace("{"+PARAMETER_KEY_PROYECTO_NAME+"}", name);

	}
	

	/*
	 * GET INVENTARIO REPORT
	 */
	@RequestMapping(value="/"+REST_PATH_REPORT_INVENTARIO, method = RequestMethod.GET)
	public String inventariosReport(@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME)  String name,ModelMap model) throws Exception {
		
		
		_logger.debug("Starting "+REST_PATH_REPORT_INVENTARIO);		

		MysqlDataController msqlController = new MysqlDataController();
		
		JSONArray inventarios = msqlController.getActivos(name);

		
		JSONArray inventariosFull = new JSONArray();
		
		for(int i = 0; i < inventarios.length(); i++){
		
			String aid = (inventarios.getJSONObject(i)).getString("id");
			
			org.json.JSONObject activo = msqlController.getActivo(aid);
			
			inventariosFull.put(activo);
		}
		
		
		model.addAttribute(PARAMETER_KEY_ACTIVOS, inventariosFull);

		model.addAttribute(PARAMETER_KEY_ID, name);

		return "reporte";
 
 
	}
	
}