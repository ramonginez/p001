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

import com.nahmens.p001.utils.Constants;

public class ActivoDAOImpl implements ActivoDAO
{
	private DataSource ds;
	
	public ActivoDAOImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/vasa");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public JSONArray buscarActivos(String projectName, String criteria)
	{
		Connection  conn = null;
		JSONArray activos = new JSONArray();
		try
		{
			conn = ds.getConnection();
			String query =	"SELECT DISTINCT(d.`inventario`), d.`value` " +
							"FROM `data` d, (SELECT inventario " +
											"FROM `data` " +
											"WHERE `value`='"+projectName+"' " +
											"AND `key`='proyecto') as inv " +
							"WHERE d.`inventario`=inv.`inventario` " +
							"AND d.`key`='nombre' " +
							"AND d.value LIKE  '%" + criteria + "%'";
			Statement statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next())
			{
				String id =rs.getString(1);
				String value=rs.getString(2);
				if(!value.equals(Constants.INVENTARIO_PLACE_HOLDER))
				{
					JSONObject inventario = new JSONObject();
					inventario.put("id", id);
					inventario.put("name", value);
					activos.put(inventario);
				}
			}
			rs.close();
			statement.close();
			return activos;
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			}
			catch(Exception e)
			{
				//DO nothing
			}
		}
		return null;
	}

}
