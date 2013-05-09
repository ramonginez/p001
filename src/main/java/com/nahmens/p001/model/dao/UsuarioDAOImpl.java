package com.nahmens.p001.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.json.JSONArray;
import org.json.JSONObject;

public class UsuarioDAOImpl implements UsuarioDAO
{
	public JSONArray findAllUsers()
	{
		Connection myConn = null;
		JSONArray usersList = new JSONArray();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			myConn =  DriverManager.getConnection("jdbc:mysql://vasa.zentyal.me:3306/vasa", "VasaMobile", "VasaMobile123");
			
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
					user.put("userid", myRs.getLong(1));
					user.put("username", myRs.getString(2));
					user.put("password", myRs.getString(3));
					
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
			Class.forName("com.mysql.jdbc.Driver");
			myConn =  DriverManager.getConnection("jdbc:mysql://vasa.zentyal.me:3306/vasa", "VasaMobile", "VasaMobile123");
			
			try
			{
				String sql = "insert into web_users (username, password) values (?, ?)";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				myStatement.setString(1, u.getString("username"));
				myStatement.setString(2, u.getString("password"));
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
			Class.forName("com.mysql.jdbc.Driver");
			myConn =  DriverManager.getConnection("jdbc:mysql://vasa.zentyal.me:3306/vasa", "VasaMobile", "VasaMobile123");
			
			try
			{
				String sql = "select * from web_users where user_id = ?";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				myStatement.setString(1, userId);
				ResultSet myRs = myStatement.executeQuery();
				
				if (myRs.next())
				{
					user = new JSONObject();
					user.put("userid", myRs.getLong(1));
					user.put("username", myRs.getLong(2));
					user.put("password", myRs.getLong(3));
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
			Class.forName("com.mysql.jdbc.Driver");
			myConn =  DriverManager.getConnection("jdbc:mysql://vasa.zentyal.me:3306/vasa", "VasaMobile", "VasaMobile123");
			
			try
			{
				String sql = "update web_users set username = '?', password = '?' where user_id = ?";
				PreparedStatement myStatement = myConn.prepareStatement(sql);
				myStatement.setString(1, u.getString("username"));
				myStatement.setString(2, u.getString("password"));
				myStatement.setString(3, u.getString("userid"));
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

	public int deleteUser(String userId)
	{
		Connection myConn = null;
		int result = 0;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			myConn =  DriverManager.getConnection("jdbc:mysql://vasa.zentyal.me:3306/vasa", "VasaMobile", "VasaMobile123");
			
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
