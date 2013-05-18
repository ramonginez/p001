package com.nahmens.p001.controller;

import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;

import com.nahmens.p001.datacontroller.MysqlDataController;
import com.nahmens.p001.model.dao.AutocompleteDAO;
import com.nahmens.p001.model.dao.AutocompleteDAOImpl;
import com.nahmens.p001.utils.Constants;

@Controller
public class ActivoController implements Constants
{
	private AutocompleteDAO autocompleteDAO;
	@Autowired
	public void setAutocompleteDAO(AutocompleteDAOImpl autocompleteDAO) {
		this.autocompleteDAO = autocompleteDAO;
	}


	Logger _logger = Logger.getLogger(ActivoController.class);

	public static final String NEW_ACTIVO_ID = "@newActivo@";


	/*
	 * GET ACTIVO 
	 */
	@RequestMapping(value="/"+REST_PATH_ACTIVO, method = RequestMethod.GET)
	public String inventariosReport(@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME)  String name,
			@PathVariable(value=PARAMETER_KEY_ACTIVO_ID)  String id ,
			ModelMap model) throws Exception {


		_logger.debug("Starting "+REST_PATH_REPORT_INVENTARIO);		


		MysqlDataController msqlController = new MysqlDataController();

		JSONObject activo = msqlController.getActivo(id);

		JSONArray photos = msqlController.getMediaForInventario(id,"img");

		JSONArray audios = msqlController.getMediaForInventario(id,"audio");

		model.addAttribute("activo", activo);

		model.addAttribute("photos", photos);

		model.addAttribute("audios", audios);

		model.addAttribute("id", id);

		return VIEW_ACTIVO; 

	}


	/*
	 * Show Create ACTIVO 
	 */
	@RequestMapping(value="/"+REST_PATH_CREATE_ACTIVO, method = RequestMethod.GET)
	public String showCreateActivo(@PathVariable(value=PARAMETER_KEY_PROYECTO_NAME)  String name,
			ModelMap model) throws Exception {


		_logger.debug("Starting "+REST_PATH_CREATE_ACTIVO);		

		String id = NEW_ACTIVO_ID;

		JSONObject activo = new JSONObject();

		activo.put("id", id);

		activo.put("proyecto", name);

		JSONArray photos = new JSONArray();

		JSONArray audios = new JSONArray();

		model.addAttribute("activo", activo);

		model.addAttribute("photos", photos);

		model.addAttribute("audios", audios);

		model.addAttribute("id", id);

		return VIEW_ACTIVO;


	}


	/*
	 * SAVE ACTIVO 
	 */


	@RequestMapping(value="/"+REST_PATH_SAVE_ACTIVO, method = RequestMethod.POST)
	public String getProyecto(@RequestParam(PARAMETER_KEY_DATA)  String strActivo, ModelMap model) throws Exception {

		JSONObject activo = new JSONObject(strActivo);

		String id = activo.getString("id");

		if(id.equals(NEW_ACTIVO_ID)){

			UUID u = UUID.randomUUID();

			id =  u.toString();

			activo.put("id", id);

		}

		MysqlDataController msqlController = new MysqlDataController();

		msqlController.saveInventario(activo,id);

		JSONObject savedActivo = msqlController.getActivo(id);

		String p = savedActivo.getString("proyecto");

		return "redirect:/"+REST_PATH_LIST_INVENTARIO.replace("{"+PARAMETER_KEY_PROYECTO_NAME+"}", p);

	}
	
	@RequestMapping(value="/autocomplete", method = RequestMethod.GET)
	public String getAssetsAndTypes(ModelMap model)
	{
		JSONArray values = autocompleteDAO.getAllValues();
		System.out.println(values);
		return VIEW_ACTIVO;
	}
}