package com.sinochem.crude.trade.web.controller;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.eyeieye.melody.web.locale.VisitorLocale;
import com.sinochem.crude.trade.common.result.ResultDatas;
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
	
	/**
	 * 获取登录信息
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/current_user/get.json")
	@WithoutAccess
	@ResponseBody
	public ResultDatas<Object> getCurrentUser(CurrentUser user) {
		ResultDatas<Object> result = new ResultDatas<Object>();
		if (user == null) {
			result.setFail("未登录", 2);
		} else {
			result.setDatas(user);
		}
		
		Locale locale = VisitorLocale.getCurrent();
		if (locale != null) {
			result.setLangVer(locale.getLanguage());
		}
		return result;
	}
}
