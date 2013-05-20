package com.nahmens.p001.model.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AutocompleteDAOImpl implements AutocompleteDAO
{
	private DataSource ds;

	public AutocompleteDAOImpl(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/vasa");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
/*
[  {"asset_name": "BOMBA",
	"asset_id": 1,
	"types": [	{"type_id": 1, "type_name": "CENTRIFUGA"},
				{"type_id": 2, "type_name": "CARCAZA PARTIDA"},
				{"type_id": 3, "type_name": "TORNILLO"},
				{"type_id": 4, "type_name": "CAVIDAD PROGRESIVA"},
				{"type_id": 5, "type_name": "ELECTROMAGNETICA"},
				{"type_id": 6, "type_name": "SUMERGIBLE"}]
	},
   {"asset_name": "COMPRESOR DE AIRE",
	"asset_id": 2,
	"types": [	{"type_id": 7, "type_name": "RECIPROCANTE"},
				{"type_id": 8, "type_name": "TORNILLO HELICOIDAL"},
				{"type_id": 9, "type_name": "CENTRIFUGO"},
				{"type_id": 10, "type_name": "TRANSPORTABLE"}]
	}
] */
	public JSONArray getAllValues()
	{
		JSONArray values = null;
		Connection myConn = null;
		
		try
		{
			myConn = ds.getConnection();

			String sql =	"SELECT a.asset_id, a.asset_name, at.type_id, at.type_name " +
							"FROM assets a, asset_types at " +
							"WHERE a.asset_id = at.asset_id";
			Statement s = myConn.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			values = new JSONArray();
			JSONObject asset = null;
			JSONArray types = null;
			int currentAssetId = -1;
			try
			{
				while (rs.next())
				{
					// Si el id del activo actual es distinto al siguiente de la consulta
					if (currentAssetId != rs.getInt(1))
					{
						asset = new JSONObject();
						asset.put(JSON_KEY_ASSET_ID, rs.getInt(1));
						asset.put(JSON_KEY_ASSET_NAME, rs.getString(2));
						// y se le crea un nuevo arreglo de tipos
						types = new JSONArray();
						// agrego el arreglo de tipos al activo
						asset.put(JSON_KEY_TYPES_ARRAY, types);
						// agrego el activo al arreglo que contiene todo
						values.put(asset);
						// actualizo el activo actual
						currentAssetId = rs.getInt(1);
					}
					// Guardo id y nombre del tipo actual en un objeto
					JSONObject type = new JSONObject();
					type.put(JSON_KEY_TYPE_ID, rs.getInt(3));
					type.put(JSON_KEY_TYPE_NAME, rs.getString(4));
					// Lo agrego al arreglo de tipos del activo actual
					types.put(type);
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return values;
	}

	public int saveAsset(String assetName)
	{
		int value = 0;
		if (assetName != null && assetName.length() > 0)
		{
			Connection myConn = null;
			try
			{
				myConn = ds.getConnection();
				Statement s = myConn.createStatement();
				
				String sql = "INSERT INTO assets (asset_name) VALUES ('" + assetName + "')";
				value = s.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return value;
	}

	public int updateAsset(JSONObject a)
	{
		int value = 0;
		if (!(a.isNull(JSON_KEY_ASSET_ID) || a.isNull(JSON_KEY_ASSET_NAME)))
		{
			Connection myConn = null;
			try
			{
				myConn = ds.getConnection();
				Statement s = myConn.createStatement();
				String sql = null;
				try {
					sql = "UPDATE assets SET asset_name = '" + a.getString(JSON_KEY_ASSET_NAME) + "' " +
						  "WHERE asset_id = " + a.getInt(JSON_KEY_ASSET_ID);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				value = s.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return value;
	}

	public int deleteAsset(int assetId)
	{
		int value = 0;
		if (assetId != 0)
		{
			Connection myConn = null;
			try
			{
				myConn = ds.getConnection();
				Statement s = myConn.createStatement();
				String sql = "DELETE FROM assets " +
							 "WHERE asset_id = " + assetId;
				value = s.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return value;
	}

	public int saveType(JSONObject t)
	{
		int value = 0;
		if (!(t.isNull(JSON_KEY_TYPE_NAME) || t.isNull(JSON_KEY_ASSET_ID)))
		{
			Connection myConn = null;
			try
			{
				myConn = ds.getConnection();
				Statement s = myConn.createStatement();
				String sql = null;
				try {
					sql = "INSERT INTO asset_types (type_name, asset_id) " +
						  "VALUES ('" + t.getString(JSON_KEY_TYPE_NAME) + "', " + t.getInt(JSON_KEY_ASSET_ID) + ")";
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				value = s.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return value;
	}

	public int updateType(JSONObject t)
	{
		int value = 0;
		if (!(t.isNull(JSON_KEY_TYPE_ID) || t.isNull(JSON_KEY_TYPE_NAME)))
		{
			Connection myConn = null;
			try
			{
				myConn = ds.getConnection();
				Statement s = myConn.createStatement();
				String sql = null;
				try {
					sql = "UPDATE asset_types SET type_name = '" + t.getString(JSON_KEY_TYPE_NAME) + "' " +
						  "WHERE type_id = " + t.getInt(JSON_KEY_TYPE_ID);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				
				value = s.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return value;
	}

	public int deleteType(int typeId)
	{
		int value = 0;
		if (typeId != 0)
		{
			Connection myConn = null;
			try
			{
				myConn = ds.getConnection();
				Statement s = myConn.createStatement();
				String sql = "DELETE FROM asset_types " +
							 "WHERE type_id = " + typeId;
				value = s.executeUpdate(sql);
				
			} catch (SQLException e) {
				e.printStackTrace();
			}	
		}
		return value;
	}

}
