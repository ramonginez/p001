package com.nahmens.p001.model.dao;

import org.json.JSONArray;

public interface AutocompleteDAO
{
	public JSONArray getAllValues();
	public int saveAsset(String assetName);
	public int updateAsset(int assetId, String assetName);
	public int deleteAsset(int assetId);
	public int saveType(String typeName, int assetId);
	public int updateType(int typeId, String typeName);
	public int deleteType(int typeId);
}
