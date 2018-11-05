package com.sinochem.crude.trade.member.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.*;
import com.sinochem.crude.trade.member.service.*;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RightAccess;
import org.apache.commons.beanutils.BeanUtils;

import com.sinochem.crude.trade.member.model.vo.MemberInfoVO;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BasicClientCookie2;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSONObject;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.member.domain.query.EnterpriseQuery;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.http.HttpConnectionManager;
import com.sinochem.it.b2b.member.access.RolesAccess;

@Controller
@RolesAccess("admin")
public class OmEnterpriseController {

    private final static Logger logger  = LoggerFactory.getLogger(OmEnterpriseController.class);

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private EnterpriseCrudeService enterpriseCrudeService;
    @Autowired
    private URLBroker appServerBroker;

    @Autowired
    private MemberCredentialsService memberCredentialsService;

    @Autowired
    private URLBroker memberServer;

    @Autowired
    private MemberInfoService memberInfoService;

    @Autowired
    private HttpConnectionManager httpConnectionManager;

    @Autowired
    private EnterpriseContactService enterpriseContactService;

    @Autowired
    PersonService personService;

    /**
     * 前台列表信息接口
     * @return
     */
    @RequestMapping(UrlMapping.OM_ENTERPRISE_LISTMH)
    public String listEnter(String name, ModelMap modelMap){
    	System.out.println(name);
    	List<Enterprise> list =  enterpriseService.selectByNameLike(name);
    	modelMap.put("list",list);
		return "om/enterprise/list";

    }

    /**
     * 列表信息
     * @return
     */
    @RequestMapping(UrlMapping.OM_ENTERPRISE_LIST)
    public String list(String name, ModelMap modelMap){
    	List<Enterprise> list =  enterpriseService.selectEnter(name);
    	modelMap.put("list",list);
		return "om/enterprise/list";

    }

  //刪除
    @RequestMapping(UrlMapping.OM_ENTERPRISE_DELETEENTERPRISE)
    public String  delete(Long id){
    	enterpriseService.deleteEnterprise(id);
    	return "redirect:"+appServerBroker+"list.htm";
    }

    //修改回显
    @RequestMapping(UrlMapping.OM_ENTERPRISE_TOUPDATE)
    public 	String toupdate(Long id, ModelMap modelMap){
    	Enterprise enterprise =enterpriseService.queryById(id);
    	modelMap.put("enterprise",enterprise);
		return "om/enterprise/update";
    }

    //修改
    @RequestMapping(UrlMapping.OM_ENTERPRISE_UPDATE)
    public String update(Enterprise enterprise) throws BizException{
    	enterpriseService.update(enterprise);
		return "redirect:"+appServerBroker+"list.htm";

    }

    /**
     * 企业补充页面
     * <p>
     *     企业只修改
     * </p>
     * @param modelMap
     * @throws BizException
     */
    @RequestMapping(value = UrlMapping.OM_ENTERPRISE_UPDATECRUDEINIT, method = RequestMethod.GET)
    public void updateCrudeInit(Long id, ModelMap modelMap) throws BizException{
        getEnterprise(id, modelMap);
        getEnterpriseCrude(id, modelMap);
    }




    /**
     * 企业补充信息列表
     * @return
     */
    @RightAccess(105)
    @RequestMapping(UrlMapping.OM_ENTERPRISE_CRUDELIST)
    public void crudeList(PageInfo pageInfo,EnterpriseQuery query, ModelMap modelMap){
        //ds:EnterpriseInfoQuery 换成 EnterpriseQuery
        modelMap.put("page", new PageInfoResult(enterpriseService.selectWithCrude(query, pageInfo)));
        List<Credentials> credentialsList = memberCredentialsService.getCredentials(true);
        modelMap.put("credentials",credentialsList);
        modelMap.put("enterprise", query);
    }

    /**
     * 企业修改页面
     * @param modelMap
     * @throws BizException
     */
    @RightAccess(105)
    @RequestMapping(value = UrlMapping.OM_ENTERPRISE_UPDATECRUDE, method = RequestMethod.GET)
    public String updateCrude(Long id,Long check,Long memberId,Long disable , ModelMap modelMap) throws BizException{
        Enterprise enterprise;
        if(id!=null){
            enterprise = enterpriseService.enterpriseById(id);
        }else{
            enterprise = enterpriseService.enterpriseByMemberId(memberId);
        }
    	AgentRegister agentRegister = new AgentRegister();
    	if(enterprise != null) {
    		modelMap.put("types", memberCredentialsService.getByMember(enterprise.getMemberId()));
    		
    		try {
    			BeanUtils.copyProperties(agentRegister, enterprise);
    		} catch (Exception e) {
    			logger.error("",e);
    		}
        	
        	// 数据转换
            if(agentRegister.getUseSocialCreditCert() != null && agentRegister.getUseSocialCreditCert() == true) {
            	agentRegister.setUseSocialCreditCertInt(1);
            } else {
            	agentRegister.setUseSocialCreditCertInt(0);
            }
            
            // 取得联系人
            EnterpriseContact contact = new EnterpriseContact();
            contact.setMemberId(agentRegister.getMemberId());
            contact.setEnterpriseId(agentRegister.getId());
            contact = enterpriseContactService.findContactWithMbIdAndEpId(contact);
            if(contact != null) {
            	agentRegister.setContact(contact);
            } else {
            	agentRegister.setContact(new EnterpriseContact());
            }
    	} else {
            MemberInfoVO memberInfoVO = memberInfoService.memberInfo(memberId);
            agentRegister.setUserName(memberInfoVO.getUsername());
            agentRegister.setMemberId(memberInfoVO.getMemberId());
            modelMap.put("agentRegister", agentRegister);
            modelMap.put("add", 1);
            modelMap.put("credentials", memberCredentialsService.getCredentials(true));
    	    return "/om/enterprise/agentRegister";
    	}
    	modelMap.put("check",check);
    	modelMap.put("disable",disable );
        modelMap.put("agentRegister", agentRegister);
        modelMap.put("credentials", memberCredentialsService.getCredentials(true));
        return "/om/enterprise/updateCrude";
    }

    /**
     * 保存企业信息
     * @param agentRegister
     * @param bindingResult
     * @param modelMap
     * @return
     */
    @RightAccess(106)
    @RequestMapping(value = UrlMapping.OM_ENTERPRISE_SAVEENTERPRISE, method = RequestMethod.POST)
    public String saveEnterprise(AgentRegister agentRegister, BindingResult bindingResult, ModelMap modelMap){
        modelMap.put("enterprise", agentRegister);

        try {
			// 类型转换
			if(agentRegister.getUseSocialCreditCertInt() == 1) {
				agentRegister.setUseSocialCreditCert(true);
			} else {
				agentRegister.setUseSocialCreditCert(false);
			}
        	
            enterpriseService.updateByPrimaryKeySelective(agentRegister,bindingResult);
            if(bindingResult.hasErrors()){
                modelMap.put("agentRegister", agentRegister);
                modelMap.put("types", memberCredentialsService.getByMember(agentRegister.getMemberId()));
                modelMap.put("credentials", memberCredentialsService.getCredentials(true));
                return "om/enterprise/updateCrude";
            }
            if(agentRegister.getContact().getId() == null) {
            	agentRegister.getContact().setEnterpriseId(agentRegister.getId());
            	agentRegister.getContact().setMemberId(agentRegister.getMemberId());
            	enterpriseContactService.insert(agentRegister.getContact());
            } else {
            	enterpriseContactService.updateByPrimaryKey(agentRegister.getContact());
            }

        } catch (Exception e) {
            logger.error("企业信息保存失败！", e);
            modelMap.put("agentRegister", agentRegister);
            modelMap.put("types", memberCredentialsService.getByMember(agentRegister.getMemberId()));
            modelMap.put("credentials", memberCredentialsService.getCredentials(true));
            
            return "om/enterprise/updateCrude";
        }
        return "redirect:"+appServerBroker+"/om/enterprise/crudeList.htm";
    }

    /**
     * 保存企业补充信息
     * @param enterpriseCrude
     * @param bindingResult
     * @param modelMap
     * @return
     */
    @RightAccess(107)
    @RequestMapping(value = UrlMapping.OM_ENTERPRISE_SAVECRUDE, method = RequestMethod.POST)
    public String saveCrude(EnterpriseCrude enterpriseCrude, BindingResult bindingResult, ModelMap modelMap){
        modelMap.put("enterpriseCrude", enterpriseCrude);
        if(bindingResult.hasErrors()) {
            getEnterprise(enterpriseCrude.getEnterpriseId(), modelMap);
            return "om/enterprise/updateCrude";
        }
        try {
            enterpriseCrudeService.saveOrUpdate(enterpriseCrude);
        } catch (BizException e) {
            logger.error("企业补充信息保存失败！", e);
            modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO16));
            getEnterprise(enterpriseCrude.getEnterpriseId(), modelMap);
            return "om/enterprise/updateCrude";
        }
        return "redirect:"+appServerBroker+"/om/enterprise/crudeList.htm";
    }

    private void getEnterprise(Long id, ModelMap modelMap) {
        if (id == null) {
            modelMap.put("enterprise", new Enterprise());
        }
        Enterprise enterprise = enterpriseService.enterpriseById(id);
        if (enterprise != null) {
            modelMap.put("enterprise", enterprise);
        } else {
            modelMap.put("enterprise", new Enterprise());
        }
    }

    private void getEnterpriseCrude(Long enterpriseId, ModelMap modelMap) {
        if (enterpriseId == null) {
            modelMap.put("enterpriseCrude", new EnterpriseCrude());
        }
        EnterpriseCrude enterpriseCrude = enterpriseCrudeService.selectByEnterpriseId(enterpriseId);
        if (enterpriseCrude != null) {
            modelMap.put("enterpriseCrude", enterpriseCrude);
            modelMap.put("enterpriseId", enterpriseId);
        } else {
            modelMap.put("enterpriseCrude", new EnterpriseCrude());
        }
    }
    
    /**
     * 企业代注册页面
     * @param modelMap
     * @throws BizException
     */
    @RightAccess(108)
    @RequestMapping(value = UrlMapping.OM_ENTERPRISE_AGENTREGISTER, method = RequestMethod.GET)
    public void agentRegister(Long memberId,Long add,ModelMap modelMap) throws BizException{
        MemberInfoVO memberInfoVO = memberInfoService.memberInfo(memberId);
        AgentRegister agentRegister = new AgentRegister();
        agentRegister.setUserName(memberInfoVO.getUsername());
        agentRegister.setMemberId(memberInfoVO.getMemberId());
//    	modelMap.put("enterprise", agentRegister);
    	modelMap.put("agentRegister", agentRegister);
    	modelMap.put("add", add);
    	modelMap.put("credentials", memberCredentialsService.getCredentials(true));
    }
    
    /**
     * 企业代注册保存
     * @param modelMap
     * @throws BizException
     */
    @RightAccess(109)
	@RequestMapping(value = UrlMapping.OM_ENTERPRISE_SAVEAGENTREGISTER ,method = RequestMethod.POST)
	public String saveAgentRegister(AgentRegister agentRegister,
							BindingResult bindingResult,
							ModelMap modelMap,
							CurrentUser user,
							HttpServletRequest request) throws BizException {
		List<String> type = new ArrayList<>();
        if(agentRegister.getTypes() != null) {
            CollectionUtils.addAll(type, agentRegister.getTypes());
        }

        Long memberId = agentRegister.getMemberId();

		try {
			if(memberId == null) {
				bindingResult.rejectValue("userName", null, "用户不存在");

				modelMap.put("errors", bindingResult.getAllErrors());
				modelMap.put("enterprise", agentRegister);
	        	modelMap.put("types", type);
	        	modelMap.put("add", 1);
	        	modelMap.put("credentials", memberCredentialsService.getCredentials(true));
	        	return "/om/enterprise/agentRegister";
			}
	
			// 类型转换
			if(agentRegister.getUseSocialCreditCertInt() == 1) {
				agentRegister.setUseSocialCreditCert(true);
			} else {
				agentRegister.setUseSocialCreditCert(false);
			}

            enterpriseService.omRegister(agentRegister, agentRegister.getContact(), memberId, agentRegister.getTypes(), bindingResult);
			if(bindingResult.hasErrors()){
//                modelMap.put("enterprise", agentRegister);
                modelMap.put("types", type);
                modelMap.put("add", 1);
                modelMap.put("credentials", memberCredentialsService.getCredentials(true));
                return "/om/enterprise/agentRegister";
            }
		} catch(Exception e) {
			logger.error("注册失败",e);
			modelMap.put("errorMessage", VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO5));
			modelMap.put("enterprise", agentRegister);
        	modelMap.put("types", type);
            modelMap.put("add", 1);
        	modelMap.put("credentials", memberCredentialsService.getCredentials(true));
        	return "/om/enterprise/agentRegister";
		}
        return "redirect:" + appServerBroker.get("/om/enterprise/crudeList.htm");
	}
}
