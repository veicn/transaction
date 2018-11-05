package com.sinochem.crude.trade.orderexecute.controller.openapi;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.sinochem.crude.trade.orderexecute.service.FeeSubjectService;


@Controller
public class OutputController  {

	@Autowired
	private FeeSubjectService feeSubjectService;
	
	Log log = LogFactory.getLog(OutputController.class);
	
	
	
}
