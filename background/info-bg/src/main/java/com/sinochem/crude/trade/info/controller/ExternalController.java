package com.sinochem.crude.trade.info.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.model.ExternalIn;
import com.sinochem.crude.trade.info.model.ExternalOut;
import com.sinochem.crude.trade.info.service.ExternalService;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
@WithoutAccess
public class ExternalController {
	
	@Autowired
	private ExternalService externalService;
	
	public static final Log log = LogFactory.getLog(ExternalController.class);
	
	@ResponseBody
	@RequestMapping(value=URLMapping.EXTERNAL_INTER, method = RequestMethod.POST)
	public ExternalOut process(@RequestBody ExternalIn in) {
		log.info("接收到的参数串--->" + JSONObject.toJSONString(in));
		ExternalOut out;
		try {
			out = externalService.process(in);
		} catch (Exception e) {
			out = new ExternalOut();
			out.setSysCode(in.getSysCode());
			out.setCode(Constants.CODE_20);
			out.setMsg(Constants.MSG_20);
			log.error("", e);
		}
		return out;
	}
	
	@ResponseBody
	@RequestMapping(value=URLMapping.EXTERNAL_USE, method = RequestMethod.POST)
	public Map<String, Object> useAll() {
		Map<String, Object>  result = new HashMap<>();
		try {
			externalService.useAll();
			result.put("success", true);
			result.put("message", "使用成功");
		} catch (Exception e) {
			log.error("", e);
			result.put("success", false);
			result.put("message", "使用失败" + e.getMessage());
		}
		return result;
	}

}
