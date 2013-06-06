package com.nahmens.p001.model.dao;

import org.json.JSONArray;

public interface ActivoDAO
{
	public JSONArray buscarActivos(String projectName, String criteria);
}
