package com.sinochem.crude.trade.member.controller;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import javax.servlet.http.HttpSession;

import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.service.AsoSsoConfigService;
import com.sinochem.crude.trade.member.service.ExosystemAccService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * sso 单点登录
 */
@Controller
public class AsoSsoController {
	Logger logger = LoggerFactory.getLogger(AsoSsoController.class);

	private String mySys = "CRUDE";

	@Autowired
	private AsoSsoConfigService asoSsoConfigService;

	@Autowired
	private ExosystemAccService exosystemAccService;
	/**
	 * tgs 前端请求路径 需要传入请求系统的编码
	 */
	@RolesAccess("optimization")
	@RequestMapping(value = UrlMapping.SSO_ASO_REDIRECTTGS, method = GET)
	public String redirectTgs(CurrentUser user, HttpSession httpSession, String Sys) throws BizException {
		String tgsUrl = asoSsoConfigService.getTgs(Sys);
		String returnUrl = asoSsoConfigService.getReturnUrl();
		String params = asoSsoConfigService.encryption(mySys, String.valueOf(getSecondTimestamp()), returnUrl);
		return new StringBuffer("redirect:").append(tgsUrl)
				.append("?Parm=").append(params)
				.toString();
	}

	/**
	 * tvs
	 */
	@RequestMapping(value = UrlMapping.SSO_ASO_TVS, method = GET)
	public String tvs(CurrentUser user, HttpSession httpSession, String Parm) throws BizException {
		logger.info("tvs.htm: Parm= " + Parm);
		String retStr = asoSsoConfigService.decrypt(Parm);
		String[] pStrings = retStr.split(";");
		String Sys = pStrings[0];
		String time = pStrings[1];
		String account = "";
		if(user != null && user.getMemberId()!=null){
			account = exosystemAccService.getBindingAccount(user.getMemberId(), Sys);
		}
		if(StringUtils.isNotEmpty(time)){
			if((getSecondTimestamp()-Integer.valueOf(time))/60 > 5){
				logger.error("跳转超时！");
				throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO27));
			}
		}
		logger.info("tvs.htm: account= " + account);
		String tvsUrl = asoSsoConfigService.getTvs(Sys);
		String params = asoSsoConfigService.encryption(mySys, String.valueOf(getSecondTimestamp()), account);
		return new StringBuffer("redirect:").append(tvsUrl)
				.append("?Parm=").append(params)
				.toString();
	}

	public static int getSecondTimestamp(){
		String timestamp = String.valueOf(System.currentTimeMillis()/1000);
		return Integer.valueOf(timestamp);
	}
}
