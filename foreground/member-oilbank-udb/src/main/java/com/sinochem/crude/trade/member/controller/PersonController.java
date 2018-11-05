package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.web.url.URLBroker;
import com.github.pagehelper.PageInfo;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.Person;
import com.sinochem.crude.trade.member.service.MemberTagsService;
import com.sinochem.crude.trade.member.service.PersonService;
import com.sinochem.crude.trade.member.service.udbservice.TransferDbAtLoginUdbServiceImpl;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.page.PageInfoResult;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import com.sinochem.it.b2b.member.access.WithoutAccess;
import com.sinochem.it.b2b.member.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xyuser on 2017/11/11.
 */
@Controller
@RolesAccess("admin")


public class PersonController {

    @Autowired
    PersonService personService;
	@Autowired
	URLBroker appServerBroker;

	@Autowired
	URLBroker memberServer;

	@Autowired
	MemberTagsService memberTagsService;

	//@@测试表同步，之后删除
	@Autowired
	private TransferDbAtLoginUdbServiceImpl transferDbAtLoginUdbService;


  //个人详细信息
    @RequestMapping(UrlMapping.PERSON_PERSONDETAIL)
    public 	String enterpersiceDetail(Long memberId, ModelMap modelMap){
    	Person person =personService.personDetail(memberId);
    	Date date = person.getCreateTime();
    	Date date1 = person.getUpdateTime();
    	SimpleDateFormat sdf =   new SimpleDateFormat( " yyyy-MM-dd" );
    	String createTime = sdf.format(date);
    	String updateTime = sdf.format(date1);
    	modelMap.put("person",person);
    	modelMap.put("createTime",createTime);
    	modelMap.put("updateTime",updateTime);
		return "enterpriseDetail";
    }

	@RequestMapping(UrlMapping.PERSON_EDIT_GET)
	public String fillInfo(User user, ModelMap modelMap){
		modelMap.put("item",personService.getInfoByMemberId(user.getMemberId()));
		return "/center/member/personFill";
	}


	@RequestMapping(value = UrlMapping.PERSON_EDIT_POST ,method = RequestMethod.POST)
	public void editPost(@ModelAttribute("item") Person item) throws BizException {
		personService.update(item);
	}


	@RightAccess(4)
	@RequestMapping(value = UrlMapping.PERSON_MAIN_LIST)
	public void mainList(Long memberId,ModelMap modelMap){
		modelMap.put("list", personService.queryMList(memberId));
		modelMap.put("mMemberId", memberId);
	}

	@WithoutAccess
	@RequestMapping(value = UrlMapping.PERSON_FILL ,method = RequestMethod.POST)
	public String fillInfo(@ModelAttribute("item") @Valid Person person,
						   BindingResult bindingResult, CurrentUser user,ModelMap modelMap) throws BizException{
		Integer check = memberTagsService.checkEnterprise(user.getMemberId());
		if(check>0){
			modelMap.put("errorMessage",VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO38));
			return "/center/member/personFill";
		}
		if (bindingResult.hasErrors()) {
			return "/center/member/personFill";
		}
		try {
			//memberService.updateNickName(user.getMemberId(),person.getName());
			personService.fill(person, user.getMemberId());
		} catch (BizException e) {
			bindingResult.rejectValue("cardNo","", e.getMessage());
		}
		if (bindingResult.hasErrors()) {
			return "/center/member/personFill";
		}
		return "redirect:"+appServerBroker+"/loginEx.htm";
	}
}
