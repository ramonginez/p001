package com.nahmens.p001.datacontroller;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.UUID;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MysqlDataController {

	public static final String INVENTARIO_PLACE_HOLDER = "_placeHolder_for_new_proyect";
	
	private DataSource ds;

	public MysqlDataController(){
		try {
			Context ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/vasa");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public JSONArray getProyectos() throws SQLException, JSONException{

		Connection  conn = null;

		JSONArray inventarios = new JSONArray();

		try{

			conn = ds.getConnection();

			Statement statement = null;

			ResultSet rs = null;

			statement = conn.createStatement();

			String query= "SELECT Distinct(`value`) from data where `key`='proyecto'";

			rs = statement.executeQuery(query);

			while (rs.next()) {

				JSONObject p = new JSONObject();
				p.put("name", rs.getString(1));
				inventarios.put(p);

			}

			rs.close();

			statement.close();

			return inventarios;

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}

	}

	public JSONObject getActivo(String id) throws SQLException, JSONException {

		Connection  conn = null;

		JSONObject inventario = new JSONObject();

		inventario.put("id", id);

		try{

			conn = ds.getConnection();

			Statement statement = null;

			ResultSet rs = null;

			statement = conn.createStatement();

			String query= "SELECT `key`, `value` from data  where `inventario`='"+id+"'";

			rs = statement.executeQuery(query);

			while (rs.next()) {

				inventario.put(rs.getString(1), rs.getString(2));	

			}

			rs.close();

			statement.close();

			return inventario;

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}
	}
	
	public JSONArray getActivos(String projectName) throws SQLException, JSONException {
	
		return getActivos(projectName,false);
	}

	public JSONArray getActivos(String projectName,boolean includeEmptyProject) throws SQLException, JSONException {

		Connection  conn = null;

		JSONArray inventarios = new JSONArray();

		try{

			conn = ds.getConnection();

			Statement statement = null;

			ResultSet rs = null;

			statement = conn.createStatement();

			String query= "Select Distinct(d.`inventario`),d.`value` from `data` d,(SELECT inventario FROM `data` WHERE `value`='"+projectName
					+"' and `key`='proyecto') as inv where d.`inventario`=inv.`inventario` and d.`key`='nombre'";

			rs = statement.executeQuery(query);

			while (rs.next()) {

				String id =rs.getString(1);

				String value=rs.getString(2);

				if(includeEmptyProject||!value.equals(INVENTARIO_PLACE_HOLDER)){
				
					JSONObject inventario = new JSONObject();

					inventario.put("id", id);
					inventario.put("name", value);
					inventarios.put(inventario);
				}
				
			}

			rs.close();

			statement.close();

			return inventarios;

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}

	}

	public JSONArray getMediaForInventario(String inventario, String type) throws Exception{

		Connection  conn = null;

		JSONArray mediaList = new JSONArray();

		try{

			conn = ds.getConnection();

			Statement statement = null;

			ResultSet rs = null;

			statement = conn.createStatement();

			String query="SELECT `key` FROM `media` WHERE `inventario`='"+inventario+"' and `type`='"+type+"'"; 

			rs = statement.executeQuery(query);

			while (rs.next()) {

				String key =rs.getString(1);

				mediaList.put(key);
			}

			rs.close();

			statement.close();

			return mediaList;

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}

	}
	public byte[] getMedia(String id) throws SQLException {

		Connection  conn = null;

		Blob media = null;

		byte[] mediaData = null ;

		try{

			conn = ds.getConnection();

			Statement statement = null;

			ResultSet rs = null;

			statement = conn.createStatement();

			String query= "SELECT value FROM `media` WHERE `key`='"+id+"'";

			rs = statement.executeQuery(query);

			if (rs.next()) {

				media = rs.getBlob(1);

				mediaData = media.getBytes(1,(int)media.length());

			}

			rs.close();

			statement.close();

			return mediaData;

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}

	}

	public void saveInventario(JSONObject activo, String inventario) throws SQLException, JSONException {

		Connection  conn = null;

		try{

			conn = ds.getConnection();

			Iterator<String> it = activo.keys();

			StringBuilder sb = new StringBuilder();
			
			while (it.hasNext()) {

				String key = it.next().toString();

				String value  = activo.getString(key);
				
				sb.append("('"+inventario+"','"+key+"','"+value+"'),");				

			}
			
			String listOfValues = sb.substring(0, sb.length()-1);
			
			String query = "INSERT INTO  `data` (`inventario`,`key`,`value`) VALUES " +
					"LIST_OF_VALUES on duplicate key update value=values(value)";
			
			String newQuery = query.replace("LIST_OF_VALUES", listOfValues);
			
			Statement s = conn.createStatement();

			s.executeUpdate (newQuery);

			/*String query = "UPDATE  `vasa`.`data` SET  `value` = '"+value+"' " +
					"where `data`.`inventario` = '"+inventario+"' and `data`.`key` = '"+key+"'";

			Statement s = conn.createStatement();

			int count = s.executeUpdate (query);

			if(count==0){

				query = "INSERT INTO  `vasa`.`data` (`inventario`,`key`,`value`) VALUES ('"+inventario+"','"+key+"','"+value+"')";

				s.executeUpdate(query);

			}*/



		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}		
	}


	public void renameProyecto(String name, String newName) throws SQLException, JSONException {

		Connection  conn = null;

		try{

			conn = ds.getConnection();


			String query = "UPDATE  `data` SET  `data`.`value` = '"+newName+"' " +
					"where `data`.`value` = '"+name+"' and `data`.`key` = 'proyecto'";

			Statement s = conn.createStatement();

			s.executeUpdate (query);



		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}		
	}



	public void removeActivo(String id) throws SQLException {
		Connection  conn = null;

		try{

			conn = ds.getConnection();


			String query = "DELETE FROM `data` WHERE `data`.`inventario` = '"+id+"'";

			Statement s = conn.createStatement();

			s.executeUpdate (query);



		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}		
	}

	public void createProyecto(String name) throws SQLException {

		Connection  conn = null;

		try{

			conn = ds.getConnection();

			Statement s = conn.createStatement();
		    
			UUID idOne = UUID.randomUUID();

			String query = "INSERT INTO  `data` (`inventario`,`key`,`value`) VALUES ('"+idOne+"','proyecto','"+name+"'),"
					+"('"+idOne+"','nombre','"+INVENTARIO_PLACE_HOLDER+"')";

			s.executeUpdate (query);

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}				
	}

	public JSONArray buscarProyectos(String value) {
		// TODO Auto-generated method stub
		return null;
	}

	public JSONArray buscarActivos(String name, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	public JSONArray getCheckins(long time) throws JSONException, SQLException {
		
		JSONArray checkin  = new JSONArray();
		
		Connection  conn = null;

		try{

			conn = ds.getConnection();

			Statement statement = null;

			ResultSet rs = null;

			statement = conn.createStatement();

			String query= "SELECT user, time, latitude, longitude FROM `checkin` WHERE `time`>='"+time+"'";

			rs = statement.executeQuery(query);

			while (rs.next()) {

				JSONObject c = new JSONObject();

				c.put("user", rs.getString(1));

				long ltime = Long.parseLong(rs.getString(2)) ;
				String srtTime = (new SimpleDateFormat("H:mm")).format(new Date(ltime));
				
				c.put("time", srtTime);
				c.put("lat", rs.getString(3));
				c.put("lon", rs.getString(4));
				checkin.put(c);

			}

			rs.close();

			statement.close();

			return checkin;

		}finally{

			try{

				conn.close();

			}catch(Exception e){

				//DO nothing
			}
		}

		/*JSONObject c1 = new JSONObject();
		c1.put("user", "Ramon");
		c1.put("time", "09:00");
		c1.put("lat", "10.49857504");
		c1.put("lon", "-66.88117657999999");
		checkin.put(c1);
		

		JSONObject c2 = new JSONObject();
		c2.put("user", "Ramon");
		c2.put("time", "09:00");
		c2.put("lat", "10.498405900000002");
		c2.put("lon", "-66.88127200000001");
		checkin.put(c2);
		

		JSONObject c3 = new JSONObject();
		c3.put("user", "Ramon");
		c3.put("time", "09:00");
		c3.put("lat", "10.4978775");
		c3.put("lon", "-66.8795185");
		checkin.put(c3);*/
		

	}



}
