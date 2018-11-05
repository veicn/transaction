package com.sinochem.crude.trade.info.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.sinochem.crude.trade.info.service.InfoContentService;


@Controller
public class InfoContentController  {

	@Autowired
	private InfoContentService infoContentService;
	
	Log log = LogFactory.getLog(InfoContentController.class);
	
	
	
}
