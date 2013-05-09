package com.nahmens.p001.controller;

import java.sql.SQLException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.apache.log4j.Logger;

import com.nahmens.p001.datacontroller.MysqlDataController;
import com.nahmens.p001.utils.Constants;

@Controller
public class MediaController implements Constants {

	Logger _logger = Logger.getLogger(MediaController.class);


	/*
	 * GET AUDIO
	 */
	@RequestMapping(value="/"+REST_PATH_MEDIA_AUDIO, method = RequestMethod.GET)
	public String getAudioFile(@PathVariable(value="id")  String id, ModelMap model) throws SQLException {

		MysqlDataController msqlController = new MysqlDataController();

		byte[] media = msqlController.getMedia(id);

		model.addAttribute("media", media);

		return VIEW_AUDIO;


	}



	/*
	 * GET IMAGE
	 */
	@RequestMapping(value="/"+REST_PATH_MEDIA_IMG, method = RequestMethod.GET)
	public String getImageFile(@PathVariable(value="id")  String id, ModelMap model) throws SQLException {

		MysqlDataController msqlController = new MysqlDataController();

		byte[] media = msqlController.getMedia(id);

		model.addAttribute("media", media);
		
		return VIEW_PHOTO;


	}
}