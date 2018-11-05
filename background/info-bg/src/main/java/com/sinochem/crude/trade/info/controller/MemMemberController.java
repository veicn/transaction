package com.sinochem.crude.trade.info.controller;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sinochem.crude.trade.URLMapping;
import com.sinochem.crude.trade.common.result.Result;
import com.sinochem.crude.trade.common.result.ResultDatas;
import com.sinochem.crude.trade.common.utils.DateTimeUtils;
import com.sinochem.crude.trade.common.utils.KeyGenUtils;
import com.sinochem.crude.trade.info.constants.Constants;
import com.sinochem.crude.trade.info.domain.MemMember;
import com.sinochem.crude.trade.info.model.MemMemberVO;
import com.sinochem.crude.trade.info.service.MemMemberService;
import com.sinochem.crude.trade.member.user.CurrentUser;
import com.sinochem.it.b2b.member.access.WithoutAccess;

@Controller
public class MemMemberController {
	@Autowired
	private MemMemberService memMemberService;
	
	Log log = LogFactory.getLog(ChannelSubController.class);
	
	/**
	 * 获取登录会员信息
	 * @param memberId
	 * @param user
	 * @return
	 */
	@WithoutAccess
	@ResponseBody
	@RequestMapping(value = URLMapping.MEMBER_MYMEMBER_LIST)
	public ResultDatas<MemMember> getMyMemberList(String memberId, CurrentUser user){
		ResultDatas<MemMember> result = new ResultDatas<>();
		try {
			String memMemberId = "";
			if(StringUtils.isNotEmpty(memberId)){
				memMemberId = memberId;
			}else{
				memMemberId = ObjectUtils.toString(user.getMemberId());
			}
			MemMember memMember = memMemberService.findMemberByMemberId(memMemberId);
			result.setStatus(Result.SUCCESS);
			result.setMessage("查询成功");
			result.setDatas(memMember);
		} catch (Exception e) {
			log.error("", e);
			result.setStatus(Result.SUCCESS);
			result.setMessage("查询失败");
		}
		return result;
	}
	
	/**
	 * 会员资料编辑
	 * @param vo
	 * @param user
	 * @return
	 */
	@SuppressWarnings("unused")
	@ResponseBody
	@RequestMapping(value = URLMapping.MEMBER_MYMEMBER_AUDIT, method = RequestMethod.POST)
	public Result memberAudit(@RequestBody MemMemberVO vo, CurrentUser user){
		Result result = new Result();
		try {
			if(user == null){
				result.setStatus(Result.EEROR);
				result.setCode("Invalid Access");
				result.setMessage("请登录");
			}
			
			if(StringUtils.isEmpty(vo.getImgurl())){
				result.setFail("头像不能为空");
				return result;
			}
			if(StringUtils.isEmpty(vo.getNickName())){
				result.setFail("昵称不能为空");
				return result;
			}
			if(StringUtils.isEmpty(vo.getPersonalNote())){
				result.setFail("签名不能为空");
				return result;
			}
			String memberId = ObjectUtils.toString(user.getMemberId());
			MemMember memberObject = memMemberService.findMemberByMemberId(memberId);
			MemMember member = new MemMember();
			member.setMemberId(ObjectUtils.toString(user.getMemberId()));
			if(user.getEpMemberId() != null){
				member.setEpMemberId(user.getEpMemberId().toString());
			}else{
				member.setEpMemberId(null);
			}
			member.setMemberName(user.getName());
			member.setImgurl(vo.getImgurl());
			member.setNickName(vo.getNickName());
			member.setPersonalNote(vo.getPersonalNote());
			member.setAliveFlag(Constants.ALIEVE_FLAG);
			member.setLangVer("zh");
			member.setCreateDate(DateTimeUtils.currentDate());
			member.setUpdateDate(DateTimeUtils.currentDate());
			member.setCreateUser(user.getName());
			member.setUpdateUser(user.getName());
			if(memberObject == null){
				member.setId(KeyGenUtils.newUuid());
				memMemberService.insertRecord(member);
			}else{
				member.setId(memberObject.getId());
				memMemberService.updateRecord(member);
			}
			result.setStatus(Result.SUCCESS);
			result.setMessage("成功");
		} catch (Exception e) {
			log.error(" ",e);
			result.setCode("");
			result.setStatus(Result.EEROR);
			result.setMessage("编辑失败");
		}
		return result;
	}
	
	
}
