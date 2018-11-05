package com.sinochem.crude.trade.member.controller;

import com.eyeieye.melody.util.StringUtil;
import com.sinochem.crude.trade.common.i18n.VisitorLocale;
import com.sinochem.crude.trade.member.contact.MsgConstant;
import com.sinochem.crude.trade.member.contact.UrlMapping;
import com.sinochem.crude.trade.member.domain.EnterpriseCrude;
import com.sinochem.crude.trade.member.domain.TMemberAccount;
import com.sinochem.crude.trade.member.model.MemberAccountResut;
import com.sinochem.crude.trade.member.model.MemberAccountVO;
import com.sinochem.crude.trade.member.model.MemberResut;
import com.sinochem.crude.trade.member.service.AsoSsoConfigService;
import com.sinochem.crude.trade.member.service.ExosystemAccService;
import com.sinochem.crude.trade.member.service.MemberCredentialsService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.common.exception.BizException;
import com.sinochem.it.b2b.common.result.Result;
import com.sinochem.it.b2b.member.access.RightAccess;
import com.sinochem.it.b2b.member.access.RolesAccess;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RolesAccess("admin")
public class ExosystemAccController {
	Logger logger = LoggerFactory.getLogger(ExosystemAccController.class);

	@Autowired
	private ExosystemAccService exosystemAccService;

	@Autowired
	private MemberCredentialsService memberCredentialsService;

	/**
	 * 前台列表信息接口
	 * @return
	 */
	@RightAccess(101)
	@RequestMapping(UrlMapping.OM_EXOSYSTEMACC_LIST)
	public String list(CurrentUser user, ModelMap modelMap) throws BizException {
		if(user == null || user.getMemberId() == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
		}
		try {
			List<MemberAccountResut> list = exosystemAccService.getList();
			modelMap.put("list",list);
		} catch (Exception e) {
			logger.error("获取列表异常：" , e);
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO7));
		}
		return "om/exosystemAcc/list";
	}

	/**
	 * 前台列表信息接口
	 * @return
	 */
	@RightAccess(102)
	@RequestMapping(UrlMapping.OM_EXOSYSTEMACC_EDIT)
	public void edit(CurrentUser user,Long id, ModelMap modelMap) throws BizException {
		if(user == null || user.getMemberId() == null){
			throw new BizException(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
		}
		MemberAccountResut memberAccount = exosystemAccService.findMemberAccountResutById(id);
		modelMap.put("memberAccount",memberAccount);

	}

	/**
	 * 前台列表信息接口
	 * @return
	 */
	@RightAccess(103)
	@RequestMapping(UrlMapping.OM_EXOSYSTEMACC_SAVE)
	@ResponseBody
	public Result save(CurrentUser user, MemberAccountVO memberAccountVO){
		Result result = new Result();
		if(user == null || user.getMemberId() == null){
			result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
			result.setStatus(Result.ERROR);
			return result;
		}
		try {
			MemberResut memberResut = exosystemAccService.findMemberByVO(memberAccountVO);
			if(memberResut == null || memberResut.getId() == null){
				result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO9));
				result.setStatus(Result.ERROR);
				return result;
			}
			exosystemAccService.save(memberResut.getId(),memberAccountVO.getSysCode(),memberAccountVO.getAccount());
		} catch (Exception e) {
			logger.error("保存数据异常" );
			result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO10));
			result.setStatus(Result.ERROR);
			return result;
		}
		result.setStatus(Result.SUCCESS);
		return result;
	}

	/**
	 * 前台列表信息接口
	 * @return
	 */
	@RightAccess(104)
	@RequestMapping(UrlMapping.OM_EXOSYSTEMACC_DELETE)
	@ResponseBody
	public Result delete(CurrentUser user,Long id) throws BizException {
		Result result = new Result();
		if(user == null || user.getMemberId() == null){
			result.setMessage(VisitorLocale.getByCurrentLanguage(MsgConstant.MEMBEROO8));
			result.setStatus(Result.ERROR);
			return result;
		}
		exosystemAccService.delete(id);
		result.setStatus(Result.SUCCESS);
		return result;
	}

}
