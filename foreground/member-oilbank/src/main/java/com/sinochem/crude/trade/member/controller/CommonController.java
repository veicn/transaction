package com.sinochem.crude.trade.member.controller;

import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.enums.EnumEpType;
import com.sinochem.crude.trade.member.model.EnterpriseDetail;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.CommonUtils;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.common.result.ResultDatas;
import com.sinochem.it.b2b.member.access.ApiSafeAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import com.sinochem.it.b2b.member.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class CommonController {

    @Value("${sys.index.url:http://www8.1chemic.com}")
    private String indexUrl;

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private PersonService personService;

    private String ATTRIBUTE_THEME = "";

    /**
     * 个人中心头部
     * @param modelMap
     * @param session
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.MOSKEN_CENTER_HEAD)
    public void centerContainHead(ModelMap modelMap, HttpSession session){
        modelMap.put("theme",session.getAttribute(ATTRIBUTE_THEME));
        modelMap.put("indexUrl",indexUrl);
    }

    /**
     * 个人中心左侧菜单
     * @param user
     * @param modelMap
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.MOSKEN_CENTER_MENUS)
    public void menu(CurrentUser user ,ModelMap modelMap){
        Enterprise enterprise = enterpriseService.enterpriseByMemberId(user.getMemberId());
        if (enterprise != null) {//企业用户
            modelMap.put("type", 1);
            modelMap.put("logo", enterprise.getEpLogo());
            modelMap.put("englishName", enterprise.getEnglishName());
            modelMap.put("epType",enterprise.getEpType());
        } else {//个人用户
            modelMap.put("type", 0);
            Person person = personService.getInfoByMemberId(user.getMemberId());
            if (person != null) {
                modelMap.put("englishName", user.getName());
                modelMap.put("logo", person.getHeadImg());
            }
        }
        modelMap.put("user",user);
    }

    /**
     * 获取企业列表
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CENTERCONTAIN_ENTERPRISELIST)
    public void enterpriseList(ModelMap modelMap){
        modelMap.put("list", enterpriseService.selectAll());
    }

    /**
     * 获取企业列表
     * @param response
     * @return
     */
    @RequestMapping(UrlMapping.CENTERCONTAIN_ENTERPRISELIST_JSON)
    @ResponseBody
    public Result enterpriseList(HttpServletResponse response){
        response.setHeader("Access-Control-Allow-Origin","*");
        ResultDatas resultDatas = new ResultDatas<EnterpriseDetail>();
        List<Map> mapList = new ArrayList<>();
        for(EnterpriseDetail enterpriseDetail : enterpriseService.selectAll() ){
            mapList.add(enterpriseDetail.toMap());
        }
        resultDatas.setDatas(mapList);
        return resultDatas;
    }

    /**
     * 按名称模糊查询企业
     * @param response
     * @param name
     * @return
     */
    @ApiSafeAccess
    @RequestMapping(UrlMapping.CENTERCONTAIN_EPLISTBYNAME_JSON)
    @ResponseBody
    public Result epListByName(HttpServletResponse response, String name){
        response.setHeader("Access-Control-Allow-Origin","*");
        ResultDatas resultDatas = new ResultDatas<EnterpriseDetail>();
        List<Map> mapList = new ArrayList<>();
        for(EnterpriseDetail enterpriseDetail : enterpriseService.selectAllByName(name) ){
            mapList.add(enterpriseDetail.toMap());
        }
        resultDatas.setDatas(mapList);
        return resultDatas;
    }

    /**
     * 获取个人信息
     * @param session
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CONTAIN_PERSONINFO)
    public void personInfo(HttpSession session, ModelMap modelMap){
        User user = (User) session.getAttribute(CommonUtils.ATTRIBUTE_USER);
        Long memberId = user.getMemberId();
        modelMap.put("person",personService.getInfoByMemberId(memberId));
        modelMap.put("member",personService.getMemberById(memberId));

    }

    /**
     * 获取企业信息
     * @param session
     * @param modelMap
     */
    @RequestMapping(UrlMapping.CONTAIN_ENTERPRISEINFO)
    public void enterpriseInfo(HttpSession session, ModelMap modelMap){
        User user = (User) session.getAttribute(CommonUtils.ATTRIBUTE_USER);
        Long memberId = user.getMemberId();
        modelMap.put("enterprise",enterpriseService.getByMemberId(user.isProxy()?user.getpMemberId():memberId));
    }

    /**
     * 修改主题
     * @param session
     * @param request
     * @param theme
     * @param returnUrl
     * @return
     */
    @RequestMapping(UrlMapping.CENTER_CHANGETHEME)
    public String changeTheme(HttpSession session, HttpServletRequest request,
                              String theme, String returnUrl) {
        if (returnUrl == null) {
            returnUrl = request.getHeader("referer");
        }
        session.setAttribute(ATTRIBUTE_THEME, theme);
        return "redirect:" + returnUrl;
    }

    /**
     * 找回密码头部
     * @param modelMap
     * @param user
     * @param session
     */
    @WithoutAccess
    @RequestMapping(UrlMapping.MOSKEN_FINDPWD_HTMLHEAD)
    public void findpwdContainHead(ModelMap modelMap,CurrentUser user, HttpSession session){
        modelMap.put("user",user);
        modelMap.put("indexUrl",indexUrl);
    }
}
