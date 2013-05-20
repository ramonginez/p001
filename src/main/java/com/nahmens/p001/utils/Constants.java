package com.nahmens.p001.utils;

public interface Constants {


	public static final String ROLE_ADMIN = "ROLE_ADMIN";

	public static final String REST_PATH_CHECKIN ="checkin";
	public static final String REST_PATH_LOGOUT ="logout";
	public static final String REST_PATH_SETTING ="usuario"; 
	public static final String REST_PATH_ADMIN_SETTING ="admin/usuario_list";
	public static final String REST_PATH_ADMIN_SETTING_UPDATE ="admin/usuario_update";
	public static final String REST_PATH_ADMIN_USER_CREATE ="admin/usuario_create";	
	public static final String REST_PATH_ADMIN_USER_DELETE ="admin/usuario_delete";
	public static final String REST_PATH_ADMIN_USER_ACTIVATE ="admin/usuario_activate";

	// View path
	public static final String VIEW_PROYECTO ="proyectos";
	public static final String VIEW_INVENTARIO ="inventarios";
	public static final String VIEW_ACTIVO ="activos";
	public static final String VIEW_AUDIO ="audio";
	public static final String VIEW_PHOTO ="photo";
	public static final String VIEW_USUARIOS ="usuarios";
	public static final String VIEW_USUARIOS_ADMIN ="adminUsuarios";
	

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
	public static final String  PARAMETER_KEY_DATA ="data";
	public static final String  PARAMETER_KEY_USER_LIST ="ulist";
	public static final String  PARAMETER_KEY_USUARIO_ID ="uid";
	public static final String  PARAMETER_KEY_USUARIO_PWD ="pwd";
	public static final String  PARAMETER_KEY_USUARIO_OLD_PWD ="oldPwd";
	public static final String  PARAMETER_KEY_USUARIO_UNAME ="uname";
	public static final String  PARAMETER_KEY_ERROR ="error";

	public static final String  PARAMETER_KEY_CAMPOS_LIST ="campolist";

	public static final String  PARAMETER_KEY_ERROR_CODE_USUARIO_EXISTENTE ="1";
	public static final String  PARAMETER_KEY_ERROR_MSG_USUARIO_EXISTENTE ="Error al crear usuario: El nombre de usuario ha sido registrado en el sistema previamente";


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
	public static final String REST_PATH_CREATE_ACTIVO 		="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/activo_create";
	public static final String REST_PATH_SAVE_ACTIVO 		="proyecto/{"+PARAMETER_KEY_PROYECTO_NAME+"}/activo_save";

	public static final String REST_PATH_MEDIA_AUDIO 		="media/audio/{"+PARAMETER_KEY_ID+"}";
	public static final String REST_PATH_MEDIA_IMG			="media/image/{"+PARAMETER_KEY_ID+"}";

	public static final String REST_PATH_UPDATE_USUARIO		="usuario_update";


	public static final String USER_LIST_JSON_KEY_ID		="id";
	public static final String USER_LIST_JSON_KEY_UNAME		="uname";

	
	
	
}
