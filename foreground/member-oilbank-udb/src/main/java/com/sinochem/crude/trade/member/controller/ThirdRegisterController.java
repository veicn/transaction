package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.domain.Member;
import com.sinochem.crude.trade.member.domain.MemberTags;
import com.sinochem.crude.trade.member.model.EnterpriseRegisterForm;
import com.sinochem.crude.trade.member.model.to.MSGEnterpriseMemberTO;
import com.sinochem.crude.trade.member.model.to.MSGEnterpriseTO;
import com.sinochem.crude.trade.member.model.to.MSGMemberTO;
import com.sinochem.crude.trade.member.service.MemberTagsService;
import com.sinochem.crude.trade.member.service.ThirdLoginService;
import com.sinochem.crude.trade.member.util.MessageUtils;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/yichemic")
public class ThirdRegisterController {

    @Value("/sync/member/add")
    private String postMember;

    @Value("/sync/enterprise/add")
    private String postEnterprise;

    @Value("//sync/enterprise/member/add/")
    private String postEnterpriseM;

    @Autowired
    private ThirdLoginService thirdLoginService;

    @Autowired
    private MemberTagsService memberTagsService;

    @Autowired
    private URLBroker appServerBroker;
    @Autowired
    private URLBroker systemServer;

    @RequestMapping(value = "/register_member", method = RequestMethod.POST)
    @ResponseBody
    @WithoutAccess
    private Result doPostRegisterM(Member member){
        Result result = new Result();
        MSGMemberTO memberTo = new MSGMemberTO(member);
        try {
            String resultStr = thirdLoginService.post(memberTo, postMember);
            if ("S".equals(resultStr)) {
                resultStr = "恭喜您注册成功";
                MemberTags memberTags = new MemberTags();
                memberTags.setMemberId(member.getId());
                memberTags.setCode(1);
                memberTags.setOwner("rego");
                memberTagsService.insert(memberTags);
            } else if ("R".equals(resultStr)) {
                resultStr = "注册失败,注册账号重复";
            } else if ("F".equals(resultStr)) {
                resultStr = "注册失败";
            }
            result.setStatus(Result.SUCCESS);
            result.setMessage("成品油网:恭喜您注册成功&nbsp;&nbsp;&nbsp;&nbsp;壹化网:" + resultStr);
        } catch (BizException e) {
            e.printStackTrace();
            result.setStatus(Result.SUCCESS);
            result.setMessage("成品油网:恭喜您注册成功&nbsp;&nbsp;&nbsp;&nbsp;壹化网:" + e.getMessage());
        }
        return result;
    }



}
