package com.sinochem.crude.trade.member.controller;

import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Partner;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.service.PartnerService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.user.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RolesAccess("admin")
public class OmPartnerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(OmPartnerController.class);

    @Autowired
    private PartnerService partnerService;

    @Autowired
    private MemberCredentialsService memberCredentialsService;

    @RightAccess(125)
    @RequestMapping(value = UrlMapping.OM_PARTNER_LIST)
    public void list(Partner partner, PageInfo pageInfo, ModelMap modelMap) {
        modelMap.put("page", new PageInfoResult(this.partnerService.getPartnerList(partner, pageInfo)));
        modelMap.put("credentials", this.memberCredentialsService.getCredentials(true));
        modelMap.put("partner", partner);
    }

    @RightAccess(126)
    @RequestMapping(value = UrlMapping.OM_PARTNER_UPDATE, method = RequestMethod.GET)
    public void toUpdate(Long id, ModelMap modelMap) {
        modelMap.put("credentials", this.memberCredentialsService.getCredentials(true));
        try {
            modelMap.put("partner", this.partnerService.selectByPrimaryKey(id));
        } catch (BizException be) {
        }
    }

    @RightAccess(127)
    @RequestMapping(value = UrlMapping.OM_PARTNER_SAVE, method = RequestMethod.POST)
    @ResponseBody
    public Result update(Partner partner, CurrentUser user) {
        Result result = new Result();
        try {
            if (partner.getId() == null)
                this.partnerService.insert(partner, user.getMemberId());
            else
                this.partnerService.update(partner, user.getMemberId());
        } catch (BizException be) {
            LOGGER.error(be.toString());
            result.setFail(be.getMessage());
        }
        return result;
    }

    @RightAccess(128)
    @RequestMapping(value = UrlMapping.OM_PARTNER_DELETE)
    @ResponseBody
    public Result delete(Long id, CurrentUser user) {
        Result res = new Result();
        try {
            Partner partner = new Partner();
            partner.setId(id);
            partner.setDelFlg(true);
            this.partnerService.update(partner, user.getMemberId());
        } catch (BizException be) {
            LOGGER.error(be.toString());
            res.setFail(be.getMessage());
        }

        return res;
    }
}
