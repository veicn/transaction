package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.util.ArrayUtil;
import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseContact;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.model.EnterpriseRegisterForm;
import com.sinochem.crude.trade.member.service.*;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.utils.StringUtil;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import com.sinochem.it.b2b.member.user.User;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 企业相关controller
 * @Author : jasonxu
 * @Date: 09/11/2017
 */
@Controller
public class CenterController {
    @Autowired
    EnterpriseService enterpriseService;

    @Autowired
    PersonService personService;

    @Autowired
    MemberCredentialsService memberCredentialsService;

    @Autowired
    EnterpriseContactService enterpriseContactService;

    @Autowired
    URLBroker systemServer;
    @Autowired
    URLBroker memberServer;
    @Autowired
    URLBroker appServerBroker;
    @Autowired
    MemberTagsService memberTagsService;

    private final static Logger logger = LoggerFactory.getLogger(CenterController.class);

    /**
     * 企业认证
     *
     * @param enterprise
     * @param user
     * @return
     */
    @RequestMapping(value = UrlMapping.CENTER_CERTIFICATION, method = RequestMethod.POST)
    @ResponseBody
    public Result certification(Enterprise enterprise, CurrentUser user) {
        Result result = new Result();
        // int re = enterpriseService.certificationSubmit(enterprise, user);
        // result.setData(re);
        return result;
    }

    /**
     * 企业快速注册
     *
     * @param bean
     * @param user
     * @return
     */
    @RequestMapping(value = UrlMapping.CENTER_FAST_REGISTER, method = RequestMethod.POST)
    @ResponseBody
    public Result fastRegister(EnterpriseRegisterForm bean, CurrentUser user,
                               HttpSession session) {
        Result result = new Result();

        bean.setLegalName("Developer");
        Enterprise enterprise = new Enterprise();
        BeanUtils.copyProperties(bean, enterprise);
        try {
            enterpriseService.add(enterprise);
        } catch (BizException e) {
            logger.error(e.getMessage(), e);
            result.setFail(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO15));
        }
        return result;
    }

    //企业详细信息
    @RequestMapping(UrlMapping.CENTER_MY)
    public String enterpersiceDetail(CurrentUser user, ModelMap modelMap) {
        if (user == null || user.getMemberId() == null) {
            return "redirect:" + systemServer.get("login.htm");
        } else if (user.getEpMemberId() == null) {
            return "redirect:" + systemServer + "/center/safety.htm";
        }

        return null;
    }

    //企业添加
    @RequestMapping(UrlMapping.CENTER_TOADD)
    public String toAdd(CurrentUser user, Enterprise enterprise) {

        Long epMemberId = user.getEpMemberId();
        enterprise.setMemberId(epMemberId);
        try {
            enterpriseService.add(enterprise);
        } catch (BizException e) {
            logger.error("", e);
        }

        return "redirect:" + appServerBroker + "my.htm";

    }

    //人员添加
    @RequestMapping(UrlMapping.CENTER_PERSONTOADD)
    public String toAdd(CurrentUser user, Person person) throws BizException {
        Long memberId = user.getMemberId();
        person.setMemberId(memberId);
        personService.add(person);
        return "redirect:" + appServerBroker + "my.htm";

    }


    /**
     * 前台列表信息接口
     *
     * @return
     */
    @RequestMapping(UrlMapping.CENTER_LISTMH)
    public String listEnter(String name, ModelMap modelMap) {
        System.out.println(name);
        List<Enterprise> list = enterpriseService.selectByNameLike(name);
        modelMap.put("list", list);
        return "om/enterprise/list";

    }

    //根据前台id回显的数据
    @RequestMapping(UrlMapping.CENTER_ENTERPRISEBYID)
    public String toupdate(Long id, ModelMap modelMap) {
        Enterprise enterprise = enterpriseService.enterpriseByMemberId(id);
        modelMap.put("enterprise", enterprise);
        return "om/enterprise/update";
    }

    /**
     * 企业，个人详情
     *
     * @param user
     * @return
     */
    @RequestMapping(UrlMapping.CENTER_INFO)
    public String info(CurrentUser user) {
        if (ArrayUtil.contains(user.getRoles(), "enterprise")) {
            return "redirect:" + memberServer.get("/center/member/enterpriseFill.htm");
        } else {
            return "redirect:" + memberServer.get("/center/member/personFill.htm");
        }
    }

    /**
     * 账户详情
     *
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(UrlMapping.CENTER_ACCOUNTDETAIL)
    public String accountDetail(CurrentUser user, ModelMap modelMap) {
        return "redirect:" + systemServer.get("center/safety.htm").toString();
    }

    /**
     * 历史数据
     *
     * @param user
     * @param statisticsYear
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTER_HISTORYDATASTATISTICS)
    public void historyDataStatistics(CurrentUser user, String statisticsYear, ModelMap modelMap) {
        modelMap.put("statisticsYear", statisticsYear);
        modelMap.put("layout", "");
    }

    @RequestMapping(value = UrlMapping.CENTER_ENTERPRISE_UPDATE, method = RequestMethod.GET)
    public void enterpriseUpdate(HttpSession session, ModelMap modelMap,Integer errorType) {
        User user = (User) session.getAttribute(CommonUtils.ATTRIBUTE_USER);
        Long memberId = user.getMemberId();
        Enterprise enterprise = enterpriseService.getByMemberId(user.isProxy() ? user.getpMemberId() : memberId);
        EnterpriseContact enterpriseContact = new EnterpriseContact();
        enterpriseContact.setMemberId(memberId);
        enterpriseContact.setEnterpriseId(enterprise.getId());
        enterpriseContact = enterpriseContactService.findContactWithMbIdAndEpId(enterpriseContact);
        enterprise.setContact(enterpriseContact);

        if(errorType != null && errorType ==1) modelMap.put("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO37));
        modelMap.put("enterprise", enterprise);
        modelMap.put("allCredentials", memberCredentialsService.getCredentials(true));
        modelMap.put("credentials", memberCredentialsService.getByMember(memberId));
    }

    @RequestMapping(value = UrlMapping.CENTER_ENTERPRISE_UPDATE, method = RequestMethod.POST)
    public String enterpriseSave(@ModelAttribute("enterprise") @Valid Enterprise enterprise,
                                 BindingResult bindingResult, CurrentUser user, ModelMap modelMap) throws BizException {
        Integer check = memberTagsService.checkEnterprise(user.getMemberId());
        if(check==0){
            modelMap.put("errorType",1);
            return "redirect:" + appServerBroker + "/center/enterprise_update.htm";
        }
        if (bindingResult.hasErrors()) {
            return "redirect:" + appServerBroker + "/center/enterprise_update.htm";
        }
        try {
             enterpriseService.register(enterprise, enterprise.getContact(), user.getMemberId(), new String[]{}, bindingResult);
            if(bindingResult.hasErrors()){
                return "/center/enterprise_update";
            }
            if(bindingResult.hasErrors()){
                return "/center/enterprise_update";
            }
        } catch (BizException be) {
            return "redirect:" + appServerBroker + "/center/enterprise_update.htm";
        }
        return "redirect:" + appServerBroker + "/center/my.htm";
    }
}
