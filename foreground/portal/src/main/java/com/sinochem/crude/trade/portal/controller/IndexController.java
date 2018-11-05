package com.sinochem.crude.trade.portal.controller;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.user.CurrentUser;

import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.utils.VisitorLocale;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.UnsupportedEncodingException;

@Controller
public class IndexController {

	@Autowired
	private URLBroker appServerBroker;

	@Value("${oauth.callback.url}")
	private String oauthCallbackUrl;

	@Value("${OILbank.url}")
	private String OILbankUrl;

	@RequestMapping("index")
	public void index(CurrentUser user, ModelMap modelMap) {
		modelMap.put("user", user);
		modelMap.put("oauthCallbackUrl", oauthCallbackUrl);
		modelMap.put("OILbankUrl", OILbankUrl);
	}

	@RequestMapping("/contain/nav.htm")
	public void nav(
			@RequestParam(value = "order", defaultValue = "1") int order,
			Model model) {
		model.addAttribute("order", order);
	}

	@RequestMapping("front_stay_tuned")
	public void frontStayTuned(CurrentUser user,ModelMap modelMap, String order){
		modelMap.addAttribute("user",user);
		modelMap.addAttribute("order",order);
	}

	@RequestMapping(value="/commonContain/headline.htm")
	public void headerGetUser(CurrentUser user,Integer order, ModelMap modelMap) {
		modelMap.put("user", user);
		modelMap.put("order", order);
	}
}
