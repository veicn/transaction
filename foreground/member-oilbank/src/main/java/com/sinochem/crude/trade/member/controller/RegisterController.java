package com.sinochem.crude.trade.member.controller;

import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Enterprise;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.service.EnterpriseService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.MemberTagsService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RegisterController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private MemberCredentialsService memberCredentialsService;
    @Value("${sys.index.url:http://www8.1chemic.com}")
    private String indexUrl;
    @Autowired
    private MemberTagsService memberTagsService;

    /**
     * 企业信息补充页面
     *
     * @param user
     * @param modelMap
     * @return
     */
    @RequestMapping(value = UrlMapping.REGISTER_ENTERPRISE, method = RequestMethod.GET)
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
        modelMap.put("credentials", memberCredentialsService.getCredentials(true));


        modelMap.put("isRegisterToYihua", memberTagsService.isRegisterToYihuaByMemberId(user.getMemberId()));//标记页面是否显示“其他信息”
        modelMap.put("crude", new EnterpriseCrude());
    }


}
