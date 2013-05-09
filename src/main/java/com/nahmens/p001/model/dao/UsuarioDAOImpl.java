package com.nahmens.p001.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

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
				String sql = "select * from web_users";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				ResultSet myRs = myStatement.executeQuery();
				ResultSetMetaData rsmd = myRs.getMetaData();

				int i = rsmd.getColumnCount();
				for (int j = 1; j <= i; j++) {
					System.out.println(rsmd.getColumnName(j));
				}
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
				String sql = "insert into web_users (username, password) values (?, ?)";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				myStatement.setString(1, u.getString(JSON_KEY_USERNAME));
				myStatement.setString(2, u.getString(JSON_KEY_PASSWORD));
				result = myStatement.executeUpdate();

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

	public JSONObject findUser(String userId)
	{
		Connection myConn = null;
		JSONObject user = null;

		try
		{
			myConn = ds.getConnection();

			try
			{
				String sql = "select * from web_users where user_id = ?";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				myStatement.setString(1, userId);
				ResultSet myRs = myStatement.executeQuery();

				if (myRs.next())
				{
					user = new JSONObject();
					user.put(JSON_KEY_ID, myRs.getLong(1));
					user.put(JSON_KEY_USERNAME, myRs.getLong(2));
					user.put(JSON_KEY_PASSWORD, myRs.getLong(3));
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
		return user;
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

	public int deleteUser(String userId)
	{
		Connection myConn = null;
		int result = 0;
		try
		{
			myConn = ds.getConnection();

			try
			{
				String sql = "update web_users set enabled = '?' where user_id = ?";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				myStatement.setString(1, "false");
				myStatement.setString(2, userId);
				result = myStatement.executeUpdate();

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
