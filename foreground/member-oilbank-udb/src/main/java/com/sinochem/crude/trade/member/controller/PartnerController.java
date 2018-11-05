package com.sinochem.crude.trade.member.controller;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Partner;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.PartnerService;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 *  合作伙伴控制层
 * Created by AlterEgo on 2018/4/20.
 */
@Controller
@WithoutAccess
public class PartnerController {


    @Autowired
    private PartnerService partnerService;

    @Autowired
    private MemberCredentialsService memberCredentialsService;

    @RequestMapping(value = UrlMapping.PARTNER_LIST)
    public void list(Partner partner, PageInfo pageInfo,ModelMap modelMap) {
        pageInfo.setPageSize(8);
        modelMap.put("page", new PageInfoResult(this.partnerService.getPartnerList(partner, pageInfo)));
        modelMap.put("credentials", this.memberCredentialsService.getCredentials(true));
        modelMap.put("partner", partner);
    }
}
