package com.sinochem.crude.trade.orderexecute.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.orderexecute.service.OrderGoodsFileService;


@Controller
public class OrderGoodsFileController  {

	@Autowired
	private OrderGoodsFileService orderGoodsFileService;
	
	Log log = LogFactory.getLog(OrderGoodsFileController.class);
	
	
	
}
