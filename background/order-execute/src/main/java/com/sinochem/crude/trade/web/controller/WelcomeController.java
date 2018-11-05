package com.sinochem.crude.trade.web.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class WelcomeController {
	public static final Log logger = LogFactory.getLog(WelcomeController.class);

	@RequestMapping("/om/welcome")
	@WithoutAccess
	public void welcome(CurrentUser user, ModelMap modelMap) {
		modelMap.put("user", user);
	}
}
