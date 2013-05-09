package com.nahmens.p001.controller;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.nahmens.p001.datacontroller.MysqlDataController;



@Controller
@RequestMapping("/checkin")
public class CheckinController {
 
	@RequestMapping(method = RequestMethod.GET)
	public JSONArray getProyectos(@RequestParam(value = "datepicker", required=false)  String datepicker,ModelMap model) throws SQLException, JSONException {
 
		//msqlController.getProyectos();

		JSONArray checkin  = new JSONArray();
		
		try{
		
			if(datepicker!=null){

				Date date = new SimpleDateFormat("MM/dd/yy", Locale.ENGLISH).parse(datepicker);	
				
				MysqlDataController msqlController = new MysqlDataController();

				checkin = msqlController.getCheckins(date.getTime());

			}

		}catch(Exception e){}
		
		
		model.addAttribute("checkin", checkin);
		
		return checkin;
 
	}
 
}