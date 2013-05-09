package com.nahmens.p001.model.dao;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UsuarioDAO {
	public JSONArray findAllUsers();
	public int saveUser(JSONObject u);
	public JSONObject findUser(String userId);
	public int updateUser(JSONObject u);
	public int deleteUser(String userId);
}
