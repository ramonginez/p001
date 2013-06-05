package com.nahmens.p001.controller;

import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.Collection;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.log4j.Logger;

import com.nahmens.p001.model.dao.AutocompleteDAO;
import com.nahmens.p001.model.dao.UsuarioDAO;
import com.nahmens.p001.model.dao.UsuarioDAOImpl;
import com.nahmens.p001.utils.Constants;

@Controller
public class UsuarioController implements Constants
{
	private UsuarioDAO userDAO;
	private AutocompleteDAO autocomplteDAO;
	@Autowired
	public void setUserDAO(UsuarioDAOImpl userDAO, AutocompleteDAO autocomplteDAO)
	{
		this.userDAO = userDAO;
		this.autocomplteDAO = autocomplteDAO;
	}


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
	public String usuariosUpdate(@RequestParam(PARAMETER_KEY_USUARIO_OLD_PWD)  String oldPwd, @RequestParam(PARAMETER_KEY_USUARIO_PWD)  String pwd,ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("usuariosUpdate");		

		User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		String currentPwd = user.getPassword();

		if((currentPwd==null)
				||(!currentPwd.equals(oldPwd))){
			
			model.addAttribute(PARAMETER_KEY_ERROR, "La clave actual es invalida");

			return VIEW_USUARIOS;

		}
		
		JSONObject jUser = new JSONObject();

		jUser.put(UsuarioDAO.JSON_KEY_USERNAME, user.getUsername());
		
		jUser.put(UsuarioDAO.JSON_KEY_PASSWORD, pwd);
		
		this.userDAO.updateUser(jUser);
				
		return "redirect:/"+REST_PATH_LOGOUT;
	}


	@RequestMapping(value="/"+REST_PATH_ADMIN_SETTING, method = RequestMethod.GET)
	public String adminUsuariosShow(@RequestParam(value = PARAMETER_KEY_ERROR, required=false)  String err, ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("adminUsuariosShow");		

		
		if(err !=null){
			
			model.addAttribute(PARAMETER_KEY_ERROR, err);

		}

		JSONArray userList = this.userDAO.findAllUsers();
		
		model.addAttribute(PARAMETER_KEY_USER_LIST, userList);


		JSONArray jCampos = this.autocomplteDAO.getAllValues();

		model.addAttribute(PARAMETER_KEY_CAMPOS_LIST, jCampos);


		return VIEW_USUARIOS_ADMIN;
	}


	@RequestMapping(value="/"+REST_PATH_ADMIN_SETTING_UPDATE, method = RequestMethod.POST)
	public String adminUsuariosUpdate(@RequestParam(PARAMETER_KEY_USUARIO_UNAME)  String name, @RequestParam(PARAMETER_KEY_USUARIO_PWD)  String pwd,ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		// This request is disabled, because DEBUG < INFO.
		logger.debug("adminUsuariosUpdate");	

		logger.debug("updating user...");

		JSONObject user = new JSONObject();

		user.put(UsuarioDAO.JSON_KEY_USERNAME, name);

		user.put(UsuarioDAO.JSON_KEY_PASSWORD, pwd);

		this.userDAO.updateUser(user);

		logger.debug("user updated...");

		return "redirect:/"+REST_PATH_ADMIN_SETTING;

	}


	@RequestMapping(value="/"+REST_PATH_ADMIN_USER_CREATE, method = RequestMethod.POST)
	public String adminUsuariosCreate(@RequestParam(PARAMETER_KEY_USUARIO_UNAME)  String name, @RequestParam(PARAMETER_KEY_USUARIO_PWD)  String pwd,ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		logger.debug("adminUsuariosCreate");	

		logger.debug("saving user...");

		JSONObject user = this.userDAO.findUserByUserName(name);

		if(user !=null){
			
			model.addAttribute(PARAMETER_KEY_ERROR, PARAMETER_KEY_ERROR_CODE_USUARIO_EXISTENTE);

			return "redirect:/"+REST_PATH_ADMIN_SETTING;

		}
		
		user = new JSONObject();
		
		user.put(UsuarioDAO.JSON_KEY_USERNAME, name);

		user.put(UsuarioDAO.JSON_KEY_PASSWORD, pwd);
		
		this.userDAO.saveUser(user);

		logger.debug("saving done");

		return "redirect:/"+REST_PATH_ADMIN_SETTING;

	}

	@RequestMapping(value="/"+REST_PATH_ADMIN_USER_DELETE, method = RequestMethod.POST)
	public String adminUsuariosDelete(@RequestParam(PARAMETER_KEY_USUARIO_ID)  String id, ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		logger.debug("adminUsuariosDelete");	

		logger.debug("deleting user...");

		this.userDAO.setUserAvailability(id,0);

		logger.debug("user deleted...");

		return "redirect:/"+REST_PATH_ADMIN_SETTING;

	}


	@RequestMapping(value="/"+REST_PATH_ADMIN_USER_ACTIVATE, method = RequestMethod.POST)
	public String adminUsuariosActivate(@RequestParam(PARAMETER_KEY_USUARIO_ID)  String id, ModelMap model) throws SQLException, JSONException {

		Logger logger = Logger.getLogger(UsuarioController.class);

		logger.debug("adminUsuariosActivate");	

		logger.debug("activate user...");

		this.userDAO.setUserAvailability(id,1);

		return "redirect:/"+REST_PATH_ADMIN_SETTING;

	}
	
	@RequestMapping(value="/"+REST_PATH_ADMIN_CAMPOS_SAVE, method = RequestMethod.POST)
	public String adminCampoSave(@RequestParam(PARAMETER_KEY_CAMPOS_LIST)  String campoList, ModelMap model) throws Exception{

		Logger logger = Logger.getLogger(UsuarioController.class);

		logger.debug("adminCampoSave");	

		String strDecoded = URLDecoder.decode(campoList, "UTF-8");
		
		JSONArray jCampos = new JSONArray(strDecoded);
		
		String a= jCampos.toString();
		
		logger.debug("value: "+a);
		
		//TODO Save real type
		this.autocomplteDAO.saveTypeMultiple(jCampos);

		return "redirect:/"+REST_PATH_ADMIN_SETTING;

	}


	
}