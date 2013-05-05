package com.nahmens.p001.utils;

public interface Constants {




	public static final String REST_PATH_CHECKIN ="checkin";
	public static final String REST_PATH_LOGOUT ="logout";
	public static final String REST_PATH_SETTING ="#"; //TODO change when the controller is ready

	// View path
	public static final String VIEW_PROYECTO ="proyectos";
	public static final String VIEW_INVENTARIO ="inventarios";
	public static final String VIEW_ACTIVO ="activos";


	// Parameters keys
	public static final String  PARAMETER_KEY_PROYECTO_NAME ="name";
	public static final String  PARAMETER_KEY_PROYECTO_NEW_NAME ="newname";
	public static final String  PARAMETER_KEY_PROYECTO_SEARCH_KEY ="skey";
	public static final String  PARAMETER_KEY_INVENTARIO_SEARCH_KEY ="skey";
	public static final String  PARAMETER_KEY_PROYECTOS ="proyectos";
	public static final String  PARAMETER_KEY_PROYECTO_ID ="pid";
	public static final String  PARAMETER_KEY_INVENTARIO_ID ="invid";
	public static final String  PARAMETER_KEY_JSON_RESPONSE_OBJECT ="res";
	public static final String  PARAMETER_KEY_ACTIVOS ="activos";
	public static final String  PARAMETER_KEY_ID ="id";
	public static final String  PARAMETER_KEY_ACTIVO_ID ="aid";


	//JSON RESPONSE KEYS
	public static final String  JSON_RESPONSE_KEY_MSG ="msg";
	public static final String  JSON_RESPONSE_KEY_ERROR ="error";
	public static final String  JSON_RESPONSE_CODE_ERROR ="1";
	public static final String  JSON_RESPONSE_MSG_OK ="ok";


	// Controllers Rest path
	public static final String REST_PATH_LIST_PROYECTO		="proyecto_list";
	public static final String REST_PATH_SEARCH_PROYECTO 	="proyecto_search";
	public static final String REST_PATH_CREATE_PROYECTO 	="proyecto_create";
	public static final String REST_PATH_DELETE_PROYECTO 	="proyecto_delete";
	public static final String REST_PATH_EDIT_PROYECTO	 	="proyecto_edit";

	public static final String REST_PATH_LIST_INVENTARIO 	="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/inventario_list";
	public static final String REST_PATH_SEARCH_INVENTARIO 	="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/inventario_search";
	public static final String REST_PATH_DELETE_INVENTARIO 	="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/inventario_delete";
	public static final String REST_PATH_REPORT_INVENTARIO 	="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/reporte";
	public static final String REST_PATH_ACTIVO 			="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/{"+PARAMETER_KEY_ACTIVO_ID+"}";

	
	

}
