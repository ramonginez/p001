package com.nahmens.p001.model.dao;

import org.json.JSONArray;
import org.json.JSONObject;

public interface AutocompleteDAO
{
	public static final String  JSON_KEY_ASSET_ID = "asset_id";
	public static final String  JSON_KEY_ASSET_NAME = "asset_name";
	public static final String  JSON_KEY_TYPE_ID = "type_id";
	public static final String  JSON_KEY_TYPE_NAME = "type_name";
	public static final String  JSON_KEY_TYPES_ARRAY = "types";
	
	public JSONArray getAllValues();
	public int saveAsset(String assetName);
	public int updateAsset(JSONObject a);
	public int deleteAsset(int assetId);
	public int saveType(JSONObject t);
	public int updateType(JSONObject t);
	public int deleteType(int typeId);
}
