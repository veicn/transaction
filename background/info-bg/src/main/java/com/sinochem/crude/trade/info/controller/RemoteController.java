package com.sinochem.crude.trade.info.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinochem.crude.trade.info.job.impl.RemoteJobServiceImpl;
import com.sinochem.it.b2b.member.access.WithoutAccess;

/**
 * 测试拉取远程数据
 * @author x
 */

@Controller
@WithoutAccess
public class RemoteController {

	@Autowired(required=true)
	RemoteJobServiceImpl remoteJobService;
	
	private static final Log log = LogFactory.getLog(RemoteController.class);
	
	@RequestMapping(value="/remote/shipPoint.json")
	public void shipPoint(@RequestParam Integer days){
		remoteJobService.saveShipPoints(days);
	}
	
	@RequestMapping(value="/remote/crudeBasePrice.json")
	public void crudeBasePrices(@RequestParam Integer days){
		remoteJobService.saveCrudeBasePrices(days);
	}
	
	@RequestMapping(value="/remote/futures.json")
	public void futures(@RequestParam Integer days){
		remoteJobService.saveFutures(days);
	}
	
	@RequestMapping(value="/remote/agios.json")
	public void agios(@RequestParam Integer days){
		remoteJobService.saveAgios(days);
	}
	
	@RequestMapping(value="/remote/info.json")
	public void info(@RequestParam Integer days){
		remoteJobService.saveInfos(days);
	}
}
