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

public class ProyectoDAOImpl implements ProyectoDAO
{
	private DataSource ds;
	
	public ProyectoDAOImpl() {
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/vasa");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public JSONArray buscarProyectos(String value)
	{
		Connection  conn = null;

		JSONArray inventarios = new JSONArray();
		
		if ( value.length() > 0 )
		{
			try
			{
				conn = ds.getConnection();
				Statement statement = conn.createStatement();

				String query =	"SELECT Distinct(`value`) " +
								"FROM data " +
								"WHERE `key`='proyecto' " +
								"AND LOWER(value) like '%" + value + "%'";
				ResultSet rs = statement.executeQuery(query);

				while (rs.next())
				{
					JSONObject p = new JSONObject();
					p.put("name", rs.getString(1));
					inventarios.put(p);
				}
				rs.close();

				statement.close();

				return inventarios;
			}
			catch (SQLException e)
			{
				e.printStackTrace();
			}
			catch (JSONException e)
			{
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
					e.printStackTrace();
				}
			}			
		}
		return null;
	}

}
