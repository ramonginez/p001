package com.nahmens.p001.model.dao;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UsuarioDAO {

	public static final String JSON_KEY_ID ="userid";
	public static final String JSON_KEY_USERNAME ="username";
	public static final String JSON_KEY_PASSWORD ="password";
	
	public JSONArray findAllUsers();
	public int saveUser(JSONObject u);
	public JSONObject findUserByUserName(String uname);
	public int updateUser(JSONObject u);
	public int setUserAvailability(String userId, int status);
	

}
