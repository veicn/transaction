package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.util.ArrayUtil;
import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.CredentialsDetail;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.service.udbservice.TransferDbAtLoginUdbServiceImpl;
import com.sinochem.it.b2b.common.CommonUtils;

import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.NoLoginException;
import com.sinochem.it.b2b.member.user.SuperUser;
import com.sinochem.it.b2b.member.user.User;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.json.MappingJacksonJsonView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class LoginController {
	final static String ENTERPRISE_ROLE = "enter_master";

	@Autowired
	private MemberCredentialsService memberCredentialsService;
	@Autowired
    private EnterpriseService enterpriseService;
	@Autowired
	private PersonService personService;

	private Map<String, Set<String>> credentials = new HashMap<String, Set<String>>();

	@Autowired
	URLBroker systemServer;
	@Autowired
	URLBroker appServerBroker;
	@Autowired
	private TransferDbAtLoginUdbServiceImpl transferDbAtLoginUdbService;

	/**
	 * 登陆后回调跳转的接收页面，主要用于写user扩展信息
	 * 
	 */
	@RequestMapping(UrlMapping.LOGINEX)
	public ModelAndView loginReturnUrl(HttpSession httpSession, String returnUrl,Integer reg,Integer rego, HttpServletRequest request) {
//		if (credentials.size() == 0) {
////			List<String> allRoles = memberCredentialsService.getAllRoles();
////			for (String role : allRoles) {
////				credentials.put(role, new HashSet<String>());
////			}
////
////			List<CredentialsDetail> list = memberCredentialsService
////					.getAllCredentialsDetails();
////			for (CredentialsDetail credentialsDetail : list) {
////				for (String role : credentialsDetail.getRoles()) {
////					// 如果一个资质是全局生效，则对每个角色都加上资质限制
////					if (CredentialsDetail.ALL.equals(role)) {
////						for (String key : credentials.keySet()) {
////							credentials.get(key).add(
////									credentialsDetail.getCode());
////						}
////					} else {// 如果只是针对特定的角色，就设置在特定的角色上
////						credentials.get(role).add(credentialsDetail.getCode());
////					}
////				}
////			}
////		}

		User user = (User) httpSession.getAttribute(CommonUtils.ATTRIBUTE_USER);
		SuperUser superUser = new SuperUser(user);


		if (user == null) {
			// 如果没有用户,则可能是直接进入,也可能是刷新页面
			throw new NoLoginException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO26));
		}

		if (user.isPlatformUser()) {

			httpSession.setAttribute(CommonUtils.ATTRIBUTE_USER,
					superUser.getUser());
			// 如果是平台用户,则直接跳到om界面

			ModelAndView modelAndView = new ModelAndView();
			buildReturn(returnUrl,this.appServerBroker.get("om/welcome.htm").toString(),request, null, modelAndView);
			return modelAndView;
		}

		// 用户资质
		List<String> mcs = null;

		// 如果是代理用户取p
		if (user.isProxy()) {
			mcs = memberCredentialsService.getByMember(user.getpMemberId());
		} else {
			mcs = memberCredentialsService.getByMember(user.getMemberId());
		}

		Enterprise enterprise = enterpriseService.enterpriseByMemberId(user.getMemberId());
		Person person = personService.getInfoByMemberId(user.getMemberId());


		int type = 0;
		//如果是企业用户,为企业用户增加企业权限所有权限,然后会由系统过滤掉其中的权限
		if ( enterprise != null ) {

			try {
				transferDbAtLoginUdbService.transferDbforEnterprise(user);
			} catch (BizException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


//
//			List<String> roles = memberCredentialsService.getAllRoles();
//			roles.add(CredentialsDetail.ALL);
//			superUser.setName(enterprise.getName()==null?enterprise.getEnglishName():enterprise.getName());
//			superUser.setRoles(roles.toArray(new String[0]));
//			superUser.setType(EnumUserType.ENTERPRISE.getType());
//			user.setType(EnumUserType.ENTERPRISE.getType());
		} else if (person != null) {

			try {
				transferDbAtLoginUdbService.transferDbforPerson(user);
			} catch (BizException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}


			superUser.setName(person.getName());
//			superUser.setType(EnumUserType.PERSON.getType());
//			user.setType(EnumUserType.PERSON.getType());
			//如果是企业负责人,赋予他拥有企业同等权限
			if(ArrayUtils.contains(user.getRoles(),ENTERPRISE_ROLE)){
//				List<String> roles = memberCredentialsService.getAllRoles();
//				roles.add(CredentialsDetail.ALL);
//				superUser.setRoles(roles.toArray(new String[0]));
			}


		}
		// 最终的角色
		Set<String> finalRoles = new HashSet<String>();

		finalRoles.addAll(Arrays.asList(user.getRoles()));
//		// 角色清洗
//		String[] roles = user.getRoles();
//		if (ArrayUtils.isNotEmpty(roles)) {
//			for (String role : roles) {
//				if (credentials.containsKey(role)) {
//					if (ArrayUtil.isEmpty(credentials.get(role)) == false) {
//						for (String credential : credentials.get(role)) {
//							if (ArrayUtil.isEmpty(mcs) == false) {
//								for (String mc : mcs) {
//									if (mc.equals(credential)) {
//										finalRoles.add(role);
//									}
//								}
//							} else {
//								continue;
//							}
//						}
//					} else {
//						finalRoles.add(role);
//					}
//				}
//			}
//		}
		if(enterprise!= null){
			finalRoles.add("enterprise");
		}
		superUser.setRoles(finalRoles.toArray(new String[] {}));
		httpSession.setAttribute(CommonUtils.ATTRIBUTE_USER,
				superUser.getUser());

        ResultDatas<Map> result = new ResultDatas<Map>();
        Map userInfo = new HashMap();
        userInfo.put("roles", user.getRoles());
        userInfo.put("memberId", user.getMemberId());
        userInfo.put("epMemberId", user.getpMemberId());
        userInfo.put("key", user.getMemberId());
        userInfo.put("name", user.getName());
        userInfo.put("type", type );
        if (enterprise != null) {
            userInfo.put("epMemberId", user.getMemberId());
            userInfo.put("enterpriseName", enterprise.getFullName()==null?enterprise.getEnglishName():enterprise.getFullName());
            userInfo.put("logo", enterprise.getEpLogo());
        }else if(user.getpMemberId() != null) {
            enterprise = enterpriseService.enterpriseByMemberId(user.getpMemberId());
            if(enterprise != null) {
                userInfo.put("enterpriseName", enterprise.getFullName()==null?enterprise.getEnglishName():enterprise.getFullName());
                userInfo.put("logo", enterprise.getEpLogo());
            }
        }
        if (person != null) {
            userInfo.put("logo", person.getHeadImg());

        }

        result.setDatas(userInfo);


        ModelAndView modelAndView = new ModelAndView();

        String target = appServerBroker.get("center/my.htm").toString();
		boolean flag = false;

		for (Cookie cookie : request.getCookies()) {
			if (cookie.getName().equals("_theme")
					) {
				if (cookie.getValue().equals("product")) {
					flag = true;
					/*if(!modelAndView.getViewName().contains("product")){
						modelAndView.setViewName("/"+cookie.getValue() + "/"+ modelAndView.getViewName());
					}*/
				}
			}
		}
		if (person == null && enterprise == null){//新注册用户,,默认把用户导向到用户注册的页面

			if( (reg != null && reg ==1 )||(rego != null && rego ==1)){

				finalRoles.add("enterprise_first");
				//企业注册,转向企业信息填充页面
				if(flag){
					target =  appServerBroker+"/product/register/enterprise.htm";
				}else{
					target =  appServerBroker+"/register/enterprise.htm";
				}

			}else{
				if(flag){
					target = appServerBroker.get("/product/center/member/personFill.htm").toString();
				}else{
					target = appServerBroker.get("center/member/personFill.htm").toString();
				}
			}

		}else {
			if (reg != null && reg == 1) {
				if (flag){
					target = appServerBroker + "/product/center/my.htm";
				}else{
					target = appServerBroker + "/center/my.htm";
				}
				//企业账号

				finalRoles.add("enterprise");
			} else {
				//个人账号
				target = systemServer + "/center/safety.htm";

			}
		}

        superUser.setRoles(finalRoles.toArray(new String[] {}));
        httpSession.setAttribute(CommonUtils.ATTRIBUTE_USER,
                superUser.getUser());

		buildReturn(returnUrl,target, request, result, modelAndView);
		return modelAndView;
	}

	private void buildReturn(String returnUrl,String targetUrl, HttpServletRequest request, ResultDatas result ,ModelAndView modelAndView) {
		if (((List<?>) request.getAttribute("MediaTypes")).contains(MediaType.APPLICATION_JSON)){
			modelAndView.addAllObjects((Map<String, ?>) result.toMap());
			modelAndView.setView(new MappingJacksonJsonView());
		}else{
			modelAndView.setView(new RedirectView(!StringUtil.isEmpty(returnUrl) ? returnUrl : targetUrl));
		}
	}


}
