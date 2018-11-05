package com.sinochem.crude.trade.orderexecute.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.orderexecute.service.OrderContractFileService;


@Controller
public class OrderContractFileController  {

	@Autowired
	private OrderContractFileService orderContractFileService;
	
	Log log = LogFactory.getLog(OrderContractFileController.class);
	
	
	
}
