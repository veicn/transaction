package com.sinochem.crude.trade.transport.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.transport.service.ProductoilShipService;


@Controller
public class ProductoilShipController  {
	@Autowired
	private ProductoilShipService productoilShipService;
	 
	Log log = LogFactory.getLog(ProductoilShipController.class);
	
	
	
}
