package com.sinochem.crude.trade.info.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.info.domain.InfoTiming;
import com.sinochem.crude.trade.info.service.InfoService;
import com.sinochem.crude.trade.info.service.InfoTimingService;
import com.sinochem.crude.trade.member.user.CurrentUser;


@Controller
public class InfoTimingController  {

	@Autowired
	private InfoTimingService infoTimingService;
	@Autowired
	private InfoService infoService;
	
	public static final Log log = LogFactory.getLog(InfoTimingController.class);
	
	/**
	 * 定时发布
	 */
	@RequestMapping(value="/infoTiming/timingInfo.json")
	public Result timingInfo(@RequestParam(value="uuid") String uuid,@RequestParam(value="time") String time,CurrentUser user) {
		Result res = new Result();
		res.setMessage("定时发布设置成功");
		InfoTiming it = new InfoTiming();
		it.setTimingDate(DateTimeUtils.toDate(time,"yyyy-MM-dd HH:mm:ss"));
		it.setCreateUser(user.getName()); 
		try {
			it.setInfoId(infoService.findByUuid(uuid).getId());
			if(!infoTimingService.insertTimingInfo(it)) {
				res.setFail("定时发布设置失败");
			}
		}catch(Exception e) {
			log.error("定时发布失败" + e);
			res.setFail("定时发布设置失败");
		}
		return res;
	}
	
}
