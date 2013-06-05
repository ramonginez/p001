package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;

import com.nahmens.p001.datacontroller.MysqlDataController;
import com.nahmens.p001.model.dao.ProyectoDAO;
import com.nahmens.p001.model.dao.ProyectoDAOImpl;
import com.nahmens.p001.utils.Constants;

@Controller
public class ProyectoController implements Constants
{
	private ProyectoDAO proyectoDAO;
	@Autowired
	public void setProyectoDAO(ProyectoDAOImpl proyectoDAO) {
		this.proyectoDAO = proyectoDAO;
	}

	Logger _logger = Logger.getLogger(ProyectoController.class);

	/*
	 * LISTING ALL THE PROJECTS
	 */
	@RequestMapping(value="/"+REST_PATH_LIST_PROYECTO, method = RequestMethod.GET)
	public String listProyectos(ModelMap model) throws SQLException, JSONException {


		_logger.debug("Starting "+REST_PATH_LIST_PROYECTO);		

		MysqlDataController msqlController = new MysqlDataController();

		JSONArray proyectos = msqlController.getProyectos();

		model.addAttribute(PARAMETER_KEY_PROYECTOS, proyectos);

		return VIEW_PROYECTO;

	}

	/*
	 * CREATE A NEW PROJECT
	 */
	@RequestMapping(value="/"+REST_PATH_CREATE_PROYECTO, method = RequestMethod.POST)
	public String createProyecto(@RequestParam(PARAMETER_KEY_PROYECTO_NAME)  String name,ModelMap model) throws  Exception {


		_logger.debug("Starting "+ REST_PATH_CREATE_PROYECTO);		

		MysqlDataController msqlController = new MysqlDataController();

		JSONArray inventarios = msqlController.getActivos(name,true);

		if(inventarios==null||inventarios.length()==0){

			msqlController.createProyecto(name);

		}

		return "redirect:"+REST_PATH_LIST_PROYECTO;

	}

	/*
	 * SEARCH PROJECTS
	 */	
	@RequestMapping(value="/"+REST_PATH_SEARCH_PROYECTO, method = RequestMethod.GET)
	public String searchProyecto(@RequestParam(PARAMETER_KEY_PROYECTO_SEARCH_KEY)  String value, ModelMap model)
	{
		_logger.debug("Starting "+ REST_PATH_SEARCH_PROYECTO);		

		if( value==null|| value.length()==0)
		{
			return "redirect:"+REST_PATH_LIST_PROYECTO;
		}
		JSONArray proyectos = proyectoDAO.buscarProyectos(value);
		model.addAttribute(PARAMETER_KEY_PROYECTOS, proyectos);
		return VIEW_PROYECTO;
	}

	/*
	 * DELETE PROJECT
	 */
	@RequestMapping(value="/"+REST_PATH_DELETE_PROYECTO, method = RequestMethod.POST)
	public String deleteProyecto(@RequestParam(PARAMETER_KEY_PROYECTO_ID)  String value,ModelMap model) throws SQLException, JSONException {

		_logger.debug("Starting "+ REST_PATH_DELETE_PROYECTO);		

		MysqlDataController msqlController = new MysqlDataController();

		JSONArray proyectos = msqlController.getActivos(value,true);

		if(proyectos!=null){

			for(int i = 0; i < proyectos.length(); i++){

				String id = proyectos.getJSONObject(i).getString("id");

				msqlController.removeActivo(id);
			}

		}

		return "redirect:"+REST_PATH_LIST_PROYECTO;

	}

	/*
	 * EDIT PROJECT
	 */
	@RequestMapping(value="/"+REST_PATH_EDIT_PROYECTO, method = RequestMethod.POST)
	public String editProyecto(@RequestParam(PARAMETER_KEY_PROYECTO_NAME)  String name,
			@RequestParam(PARAMETER_KEY_PROYECTO_NEW_NAME)  String newName,ModelMap model) throws SQLException, JSONException {

		_logger.debug("Starting "+ REST_PATH_EDIT_PROYECTO);		

		JSONObject result = new JSONObject();
		
		try{
			
			MysqlDataController msqlController = new MysqlDataController();
			
			JSONArray proyecto = msqlController.getActivos(name,true);
			
			if(proyecto==null||proyecto.length()==0){
				
				throw new Exception("Proyecto invalido");
			}
			
			msqlController.renameProyecto(name, newName);
			
			result.put(JSON_RESPONSE_KEY_MSG, JSON_RESPONSE_MSG_OK);
			
			
			
		}catch(Exception e){
		
			result.put(JSON_RESPONSE_KEY_ERROR, JSON_RESPONSE_CODE_ERROR);
			result.put(JSON_RESPONSE_KEY_MSG, e.getMessage());

		}
		
		model.addAttribute(PARAMETER_KEY_JSON_RESPONSE_OBJECT, result);

		return "redirect:"+REST_PATH_LIST_PROYECTO;

	}

}