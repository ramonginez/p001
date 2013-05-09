package com.nahmens.p001.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsuarioDAOImpl implements UsuarioDAO
{
	private DataSource ds;

	public UsuarioDAOImpl(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/vasa");
		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	public JSONArray findAllUsers()
	{
		Connection myConn = null;

		JSONArray usersList = new JSONArray();

		try
		{
			myConn = ds.getConnection();

			try
			{
				String sql = "select * from web_users where ENABLED=1";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				ResultSet myRs = myStatement.executeQuery();

				while (myRs.next())
				{
					JSONObject user = new JSONObject();
					user.put(JSON_KEY_ID, myRs.getLong(1));
					user.put(JSON_KEY_USERNAME, myRs.getString(2));
					//user.put(JSON_KEY_PASSWORD, myRs.getString(3));

					usersList.put(user);
				}
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return usersList;
	}

	public int saveUser(JSONObject u)
	{
		Connection myConn = null;
		int result = 0;
		try
		{
			myConn = ds.getConnection();

			try
			{
				
				Statement s = myConn.createStatement();
							    
				String query = "insert into web_users (username, password, enabled) " +
						"values ('"+u.getString(JSON_KEY_USERNAME)+"','"+u.getString(JSON_KEY_PASSWORD)+"',1)";
					
				s.executeUpdate(query);
				
				ResultSet rs = s.getGeneratedKeys();

				if (rs.next()) {
					
					int id =rs.getInt(1);
					
					String queryRole = "insert into user_roles (USER_ID, AUTHORITY) " +
							"values ("+id+",'ROLE_USER')";
				
					s.executeUpdate(queryRole);

					if (rs != null) {
						rs.close(); // close result set
					}

					
				}
				
				if (s != null) {
					s.close(); // close statement
				}
					
				s.close();

				
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public JSONObject findUserByUserName(String uname)
	{
		Connection myConn = null;

		try
		{
			myConn = ds.getConnection();

			try
			{
				String sql = "select * from web_users where username='"+uname+"'";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				ResultSet myRs = myStatement.executeQuery();
				
				if(myRs.next())
				{
					JSONObject user = new JSONObject();
					user.put(JSON_KEY_ID, myRs.getLong(1));
					user.put(JSON_KEY_USERNAME, myRs.getString(2));
					//user.put(JSON_KEY_PASSWORD, myRs.getString(3));

					return user;
				}
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;

	}

	public int updateUser(JSONObject u)
	{
		Connection myConn = null;
		int result = 0;
		try
		{
			myConn = ds.getConnection();

			try
			{
				
				String query = "UPDATE  web_users set `password` = '"+u.getString(JSON_KEY_PASSWORD)+"'  " +
						"where username = '"+u.getString(JSON_KEY_USERNAME)+"' ";

				Statement s = myConn.createStatement();

				s.executeUpdate (query);

				
			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int setUserAvailability(String userId,int status)
	{
		Connection myConn = null;
		int result = 0;
		try
		{
			myConn = ds.getConnection();

			try
			{
				

				String query = "UPDATE  web_users set `enabled` = "+ status +
						" where user_id = '"+userId+"' ";

				Statement s = myConn.createStatement();

				s.executeUpdate (query);
				
				

			} catch (SQLException sqlE) {
				sqlE.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				myConn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	

}
