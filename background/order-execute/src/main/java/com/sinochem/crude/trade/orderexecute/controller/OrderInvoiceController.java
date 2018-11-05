package com.sinochem.crude.trade.orderexecute.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.orderexecute.service.OrderInvoiceService;


@Controller
public class OrderInvoiceController  {

	@Autowired
	private OrderInvoiceService orderInvoiceService;
	
	Log log = LogFactory.getLog(OrderInvoiceController.class);
	
	
	
}
