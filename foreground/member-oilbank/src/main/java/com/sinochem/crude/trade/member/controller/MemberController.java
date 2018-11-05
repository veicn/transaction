package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户相关使用的controller
 *
 * @Author : jasonxu
 * @Date: 01/11/2017
 */
@Controller
public class MemberController {

    @Autowired
    private PersonService personService;

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private URLBroker appServerBroker;


    @RequestMapping(UrlMapping.CENTER_MEMBER_ACCOUNTDETAIL)
    public String accountDetail(CurrentUser user, ModelMap modelMap) {
        Long memberId = user.getMemberId();
        //Member member = memberService.getMemberByMemberId(memberId);
        //modelMap.put("member", member);
        Enterprise enterprise = enterpriseService.getByMemberId(user.getMemberId());
        if (enterprise != null) {
            modelMap.put("enterprise", enterprise);
            return "center/member/enter_accountDetail";
        } else {
            modelMap.put("person", personService.getInfoByMemberId(memberId));
        }
        return null;
    }

    /**
     * 企业信息补充页面
     *
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = UrlMapping.CENTER_MEMBER_ENTERPRISEFILL, method = RequestMethod.GET)
    public void enterpriseFill(CurrentUser user, ModelMap modelMap) {
        if (user != null) {
            modelMap.put("user", user);
            Enterprise enterprise = enterpriseService.getByMemberId(user.getMemberId());
            if (enterprise == null){
                enterprise = new Enterprise();
            }
            modelMap.put("enterprise", enterprise);
        } else {
            modelMap.put("enterprise", new Enterprise());
        }
    }

    /**
     * 企业信息补充页面
     *
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = UrlMapping.CENTER_MEMBER_ENTERPRISEINFO)
    public void enterpriseInfo(CurrentUser user, ModelMap modelMap) {
        Enterprise enterprise = null;
        if (user != null) {
            enterprise = enterpriseService.getByMemberId(user.getMemberId());
        }
        if (enterprise == null) {
            enterprise = new Enterprise();
        }
        modelMap.put("enterprise", enterprise);
    }

    /**
     * 个人信息补充页面
     *
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = UrlMapping.CENTER_MEMBER_PERSONFILL, method = RequestMethod.GET)
    public void personFill(CurrentUser user, ModelMap modelMap) {
        if (user != null) {
            modelMap.put("user", user);
            Person person = personService.getInfoByMemberId(user.getMemberId());
            modelMap.put("item", person == null ? new Person() : person);
        } else {
            modelMap.put("item", new Person());
        }
    }

    @RequestMapping(UrlMapping.CENTER_MEMBER_PROTOCOL)
    @WithoutAccess
    public String protocal(Integer type ){
        String protocol = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh","protocol.html"},new String[]{"en","protocol_en.html"}});
        String enterprise_protocol = VisitorLocale.getByCurrentLanguage(new String[][]{new String[]{"zh","enterprise_protocol.html"},new String[]{"en","enterprise_protocol_en.html"}});
        String[] protocols = new String[]{protocol,enterprise_protocol};
        if(type == null || type>protocols.length-1){
            type=0;
        }
        return "redirect:"+ appServerBroker.get("/member/" +protocols[type]);
    }

    @RequestMapping(UrlMapping.CENTER_MEMBER_ENTERPRISEPROTOCOL)
    @WithoutAccess
    public String enterpriseProtocol() {
        return "redirect:"+appServerBroker+"enterprise_protocol.html";
    }
}
