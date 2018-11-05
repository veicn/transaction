package com.sinochem.crude.trade.orderexecute.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.orderexecute.service.OrderMsgRemindingService;


@Controller
public class OrderMsgRemindingController  {

	@Autowired
	private OrderMsgRemindingService orderMsgRemindingService;
	
	Log log = LogFactory.getLog(OrderMsgRemindingController.class);
	
	
	
}
