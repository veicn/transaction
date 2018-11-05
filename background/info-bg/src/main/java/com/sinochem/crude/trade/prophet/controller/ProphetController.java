package com.sinochem.crude.trade.prophet.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.common.utils.HttpUtils;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class ProphetController {
	Log log = LogFactory.getLog(ProphetController.class);
	
	@Value("${prophet.api1}")
	private String api1;
	
	@Value("${prophet.api3}")
	private String api3;

	/**
	 * 中化价格预测模型1
	 * 
	 * @param vo
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = "/prophet/api/1/{market}/{cycle}", method = RequestMethod.GET)
	public String api1(@PathVariable("market") String market, @PathVariable("cycle") String cycle, CurrentUser user) {
		StringBuilder sb = new StringBuilder(api1);
		sb.append("/" + market);
		sb.append("/" + cycle);
		String result = "";
		try {
			log.info("/prophet.api1======" + sb);
			result = HttpUtils.prophetGet(sb.toString());
		} catch (Exception e) {
			log.error(e);
		}
		return result;
	}
	
	/**
	 * 中化价格预测模型3
	 * 
	 * @param vo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/prophet/api/3/{market}/{cycle}", method = RequestMethod.GET)
	public String api3(@PathVariable("market") String market, @PathVariable("cycle") String cycle,
			@RequestParam("var") String var, CurrentUser user) {
		StringBuilder sb = new StringBuilder(api3);
		sb.append("/" + market);
		sb.append("/" + cycle);
		sb.append("?var=" + var);
		sb.append("&memberId=" + user.getMemberId());
		
		String result = "";
		try {
			log.info("/prophet.api3======" + sb);
			result = HttpUtils.prophetGet(sb.toString());
		} catch (Exception e) {
			log.error(e);
		}
		
		return result;
	}
	
	/**
	 * 跳转价格预测页面
	 */
	@RequestMapping(value="/priceForecast/chart2.htm")
	public void chart2(){
	}
	
	@RequestMapping(value="/priceForecast/chart1.htm")
	public void chart1(){
	}

}
