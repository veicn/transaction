package com.sinochem.crude.trade.xxl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.sinochem.crude.trade.info.service.SymbolPriceService;
import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHander;

@Component
@JobHander("symbolPrice")
public class SymbolPriceHandler extends  IJobHandler{
	@Value("${dataFeed.ticks.url}")  
	private String ticksUrl;  
	@Value("${dataFeed.sc.url}")  
	private String scUrl;  
	@Autowired
	private SymbolPriceService symbolPriceService;
	
    private static final Log log = LogFactory.getLog(SymbolPriceHandler.class);

	
	@Override
	public ReturnT<String> execute(String... params) throws Exception {
		log.error("symbolPrice  start ---->");
		symbolPriceService.symbolPriceHandler(ticksUrl,scUrl);
		log.error("symbolPrice  end ---->");
		return ReturnT.SUCCESS;
	}
	
	

	
}
