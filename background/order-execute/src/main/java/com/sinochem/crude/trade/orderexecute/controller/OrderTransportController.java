package com.sinochem.crude.trade.orderexecute.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.orderexecute.service.OrderTransportService;


@Controller
public class OrderTransportController  {

	@Autowired
	private OrderTransportService orderTransportService;
	
	Log log = LogFactory.getLog(OrderTransportController.class);
	
	
	
}
