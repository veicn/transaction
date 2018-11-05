package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.util.StringUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.domain.MemberTags;
import com.sinochem.crude.trade.member.model.to.MSGEnterpriseMemberTO;
import com.sinochem.crude.trade.member.model.to.MSGEnterpriseTO;
import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;
import com.sinochem.crude.trade.member.service.*;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 企业相关controller
 * @Author : jasonxu
 * @Date: 09/11/2017
 */
@Controller
public class EnterpriseController {
	Logger logger = LoggerFactory.getLogger(EnterpriseController.class);

	@Autowired
	EnterpriseService enterpriseService;
	@Autowired
	URLBroker memberServer;
	@Autowired
	private URLBroker appServerBroker;

	@Autowired
	ThirdLoginService thirdLoginService;

	@Value("/sync/enterprise/add")
	private String postEnterprise;

	@Value("/sync/enterprise/member/add/")
	private String postEnterpriseM;

	@Autowired
	private URLBroker uploadServerBroker;

	@Autowired
	private MemberInfoService memberInfoService;
	@Autowired
	private MemberCredentialsService memberCredentialsService;

	/**
	 * 企业修改 get请求
	 * @param user
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ENTERPRISE_EDIT_GET,method = RequestMethod.GET)
	public String edit(CurrentUser user , ModelMap modelMap){
		Enterprise enterprise = enterpriseService.getByMemberId(user.getMemberId());
		modelMap.put("enterprise", enterprise == null ? new Enterprise() : enterprise);
		return "/center/member/enterpriseFill";
	}

	/**
	 * 企业修改 post请求
	 * @return
	 */
	@RequestMapping(value = UrlMapping.ENTERPRISE_EDIT_POST ,method = RequestMethod.POST)
	public void editPost(@ModelAttribute("item") Enterprise item) throws BizException {
		enterpriseService.update(item);
	}

	@Autowired
	private EnterpriseCrudeService enterpriseCrudeService;
	@Autowired
	private MemberTagsService memberTagsService;
	/**
	 * 注册企业
	 * @return
	 * @throws BizException
	 */
	@RequestMapping(value = UrlMapping.ENTERPRISE_REGISTER ,method = RequestMethod.POST)
	public String register(@ModelAttribute("enterprise") @Valid Enterprise enterprise ,BindingResult bindingResult,
						    String[] types, CurrentUser user, ModelMap modelMap) throws BizException {
		Integer check = memberTagsService.checkEnterprise(user.getMemberId());
		if(check==0){
			modelMap.put("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO37));
			return "/register/enterprise";
		}
		List<String> type = new ArrayList<String>();
		enterpriseService.register(enterprise,enterprise.getContact(), user.getMemberId(),types, bindingResult);
		if (bindingResult.hasErrors()) {
            modelMap.put("types",types);
			modelMap.put("credentials", memberCredentialsService.getCredentials(true));
			return "/register/enterprise";
		}
		EnterpriseCrude enterpriseCrude = enterprise.getEnterpriseCrude();
		//需要同步注册壹化网
		if(memberTagsService.isRegisterToYihuaByMemberId(user.getMemberId())){
			String message = "";
			if(enterprise.getEpType() == 1){
				message = "暂不支持境外企业注册壹化网！";
				modelMap.put("message",message);
				return "/register/enter_success_third";
			}else{
				MemberInfoVO memberInfoVO = memberInfoService.memberInfo(user.getMemberId());
				MSGEnterpriseTO msgEnterpriseTO = new MSGEnterpriseTO(enterprise,enterpriseCrude,uploadServerBroker,memberInfoVO.getMobile());
				enterpriseCrude.setEnterpriseId(enterprise.getId());
				//添加“企业的其他信息”
				if(types!=null) {
					CollectionUtils.addAll(type, types);
					if(type.contains("1")&&type.contains("2")){
						//工贸3
						msgEnterpriseTO.setEnterpriseType("3");
					}else if(type.contains("1")){
						msgEnterpriseTO.setEnterpriseType("1");
					}else if(type.contains("2")){
						// 贸2
						msgEnterpriseTO.setEnterpriseType("2");
					}else{
						message = "暂不支持您的企业类型注册壹化网！";
						modelMap.put("message",message);
						return "/register/enter_success_third";
					}
				}
				enterpriseCrudeService.add(enterpriseCrude);
				//调用壹化网企业接口
				String resultStr = thirdLoginService.post(msgEnterpriseTO,postEnterprise);
				if ("S".equals(resultStr)) {
					message = "";
				} else if ("R".equals(resultStr)) {
					message = "企业已在壹化网注册，无法重复注册！";
				} else if ("F".equals(resultStr)) {
					message = "注册失败，请前往壹化网进行注册！";
				}
				MSGEnterpriseMemberTO enterpriseMemberTO = new MSGEnterpriseMemberTO(memberInfoVO,enterprise);
				//调用壹化网企业人员关系接口
				resultStr = thirdLoginService.post(enterpriseMemberTO,postEnterpriseM);
				//TODO 这里返回的信息是否需要展示在页面，如何展示？？
				if("S".equals(resultStr)){

				}
				modelMap.put("message",message);
				return "/register/enter_success_third";
			}
		}
		return "redirect:"+appServerBroker+"/register/enter_success.htm";
	}

	@RequestMapping(value = UrlMapping.ENTERPRISE_FILL ,method = RequestMethod.POST)
	public String fillInfo(@ModelAttribute("enterprise") @Valid Enterprise enterprise ,
						   BindingResult bindingResult,String[] types, CurrentUser user,ModelMap modelMap) throws BizException {
		Integer check = memberTagsService.checkEnterprise(user.getMemberId());
		if(check==0){
			modelMap.put("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO37));
			return "/enterprise/enterprise";
		}
		if (bindingResult.hasErrors()) {
			return "/register/enterprise";
		}
		enterpriseService.fill(enterprise, user.getMemberId(), bindingResult);
		if (bindingResult.hasErrors()) {
			return "/register/enterprise";
		}
		return "redirect:" +  memberServer.get("loginEx.htm");
	}

	@WithoutAccess
	@RequestMapping(UrlMapping.ENTERPRISE_NAME)
	@ResponseBody
	public String name(Long epMemberId){
		String name = "";
		try {
			Enterprise enterprise = enterpriseService.getByMemberId(epMemberId);
			if(enterprise != null){
				if(StringUtil.isNotEmpty(enterprise.getFullName()) && StringUtil.isNotEmpty(enterprise.getEnglishName())){
					name = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh",enterprise.getFullName()},new String[]{"en",enterprise.getEnglishName()}});
				}else{
					if(StringUtil.isEmpty(enterprise.getFullName())){
						name = enterprise.getEnglishName();
					}else{
						name = enterprise.getFullName();
					}
				}
			}
		} catch (Exception e) {
			logger.error("",e);
			return "";
		}
		return name;
	}

}
